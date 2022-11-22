package it.prova.myebay.web.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AcquistoDTO;
import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.dto.CategoriaDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.AcquistoService;
import it.prova.myebay.service.AnnuncioService;
import it.prova.myebay.service.CategoriaService;
import it.prova.myebay.service.RuoloService;
import it.prova.myebay.service.UtenteService;

@Controller
public class AcquistoController {

	
	@Autowired
	private AnnuncioService annuncioService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private RuoloService ruoloService;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private AcquistoService acquistoService;
	
	
	@GetMapping("/acquisto/list")
	public String insertAnnuncio(HttpServletRequest request,
			Model model) {
		UtenteDTO utenteInSessione = (UtenteDTO) request.getSession().getAttribute("userInfo");
		Long idUtenteSessione = utenteInSessione.getId();
		
			model.addAttribute("acquisto_list_attribute",
					AcquistoDTO.createAcquistoDTOListFromModelList(acquistoService.FindAllAcquistiById(idUtenteSessione), true));
			
		return "acquisto/list";
	}
	
	@PostMapping("/confermaAcquisto")
	public String confermaAcquisto(@RequestParam(name = "idAnnuncio") Long idAnnuncio
			,@RequestParam(name = "utenteId") Long utenteId
			,Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {
		Utente utente = utenteService.caricaSingoloUtente(utenteId);
		Annuncio annuncio = annuncioService.caricaSingoloElemento(idAnnuncio);
		AcquistoDTO acquistoDTO = new AcquistoDTO(annuncio.getTestoAnnuncio(), annuncio.getData(), annuncio.getPrezzo());
		
		if (utente.getCreditoResiduo() < annuncio.getPrezzo()) {
			
			redirectAttrs.addFlashAttribute("errorMessage", "Credito esaurito");
			return "redirect:/home";
		}
		
		if(annuncio.getUtenteInserimento().getId()==utenteId) {
            redirectAttrs.addFlashAttribute("errorMessage", "Non puoi acquistare un tuo annuncio");
            return "redirect:/home";
        }
		
		Integer creditoAggiornato = utente.getCreditoResiduo() - annuncio.getPrezzo();
		utente.setCreditoResiduo(creditoAggiornato);
		utenteService.aggiorna(utente);
		
		acquistoDTO.setData(new Date());
		acquistoDTO.setUtenteAcquirente(UtenteDTO.buildUtenteDTOFromModel
				(utenteService.caricaSingoloUtente(utenteId), true));
		acquistoService.inserisciNuovo(acquistoDTO.buildAcquistoModel());
		annuncio.setAperto(false);
		annuncioService.aggiorna(annuncio);
		
		Utente utenteFromDb = utenteService.caricaSingoloUtente(utenteId);
		UtenteDTO utenteParziale = new UtenteDTO();
		utenteParziale.setNome(utenteFromDb.getNome());
		utenteParziale.setCognome(utenteFromDb.getCognome());
		utenteParziale.setId(utenteFromDb.getId());
		utenteParziale.setCreditoResiduo(utenteFromDb.getCreditoResiduo());
		request.getSession().setAttribute("userInfo", utenteParziale);
		
		
		return "acquisto/list";
	}
	
	@GetMapping("/loginAcquisto")
	public String loginAcquisto(@RequestParam(required = true) Long idAnnuncioWithNoAuth,
			Model model, RedirectAttributes redirectAttrs,HttpServletRequest request, Principal principal) {
		System.out.println("maledetto   "+idAnnuncioWithNoAuth); 
		
		RequestCache requestCache = new HttpSessionRequestCache();
		
		
		model.addAttribute("idAnnuncioWithNoAuth", idAnnuncioWithNoAuth);
		return "/login";
	}
	
	/*
	@GetMapping("/acquistaWithoutAuth")
	public String acquistaWithoutAuth(@RequestParam(required = true) Long idAnnuncioWithNoAuth,
			Model model, RedirectAttributes redirectAttrs,HttpServletRequest request, Principal principal) {
		System.out.println("maledetto   "+idAnnuncioWithNoAuth);
		if (principal != null) {
			return this.acquisto(idAnnuncioWithNoAuth, model, redirectAttrs, request);
		}
		model.addAttribute("idAnnuncioWithNoAuth", idAnnuncioWithNoAuth);
		return "/login";
	}*/
}
