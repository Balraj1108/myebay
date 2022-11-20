package it.prova.myebay.web.controller;

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
	
	
	@PostMapping("/acquisto/list")
	public String insertAnnuncio(@RequestParam(name = "utenteId") Long utenteId
			,Model model) {
		//model.addAttribute("categorie_totali_attr", CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAll()));
		//model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
		//model.addAttribute("insert_annuncio_attr", new AnnuncioDTO());
		//Utente utenteModel = utenteService.caricaSingoloUtenteConRuoli();
		//model.addAttribute("edit_utente_attr", UtenteDTO.buildUtenteDTOFromModel(utenteModel,true));
		
			model.addAttribute("acquisto_list_attribute",
					AcquistoDTO.createAcquistoDTOListFromModelList(acquistoService.FindAllAcquistiById(utenteId), true));
			
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
	
	@PostMapping("/loginAcquisto")
	public String loginAcquisto(HttpServletRequest request, HttpServletResponse response) {
		
		RequestCache requestCache = new HttpSessionRequestCache();
	    requestCache.saveRequest(request,response);
	    return "redirect:/login";
	}
}
