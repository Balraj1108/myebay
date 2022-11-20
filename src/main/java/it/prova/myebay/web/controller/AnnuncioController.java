package it.prova.myebay.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AcquistoDTO;
import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.dto.CategoriaDTO;
import it.prova.myebay.dto.RuoloDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.AnnuncioService;
import it.prova.myebay.service.CategoriaService;
import it.prova.myebay.service.RuoloService;
import it.prova.myebay.service.UtenteService;
import it.prova.myebay.validation.ValidationNoPassword;

@Controller
public class AnnuncioController {

	@Autowired
	private AnnuncioService annuncioService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private RuoloService ruoloService;
	
	@Autowired
	private UtenteService utenteService;
	
	@PostMapping("/listAnnuncio")
	public String listAnnunci(Annuncio utenteExample, ModelMap model) {
		model.addAttribute("annuncio_list_attribute",
				AnnuncioDTO.createAnnuncioDTOListFromModelList(annuncioService.findByExample(utenteExample), true, true));
		return "listAnnuncio";
	}
	
	@GetMapping("/annuncio/insert")
	public String insertAnnuncio(Model model) {
		model.addAttribute("categorie_totali_attr", CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAll()));
		//model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
		model.addAttribute("insert_annuncio_attr", new AnnuncioDTO());
		//Utente utenteModel = utenteService.caricaSingoloUtenteConRuoli();
		//model.addAttribute("edit_utente_attr", UtenteDTO.buildUtenteDTOFromModel(utenteModel,true));
		return "annuncio/insert";
	}
	
	@PostMapping("/annuncio/save")
	public String saveAnnuncio(
			@Validated @ModelAttribute("insert_annuncio_attr") AnnuncioDTO annuncioDTO,
			@RequestParam(name = "utenteId") Long utenteId,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		

		if (result.hasErrors()) {
			model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
			return "annuncio/insert";
		}
		
		annuncioDTO.setData(new Date());
		annuncioDTO.setAperto(true);
		annuncioDTO.setUtenteInserimento(UtenteDTO.buildUtenteDTOFromModel
				(utenteService.caricaSingoloUtente(utenteId), true));
		annuncioService.inserisciNuovo(annuncioDTO.buildAnnuncioModel(true));

		redirectAttrs.addFlashAttribute("successMessage", "Annuncio inserito correttamente");
		return "redirect:/home";
	}
	
	@GetMapping("/showAnnuncio/{idAnnuncio}")
	public String showFilm(@PathVariable(required = true) Long idAnnuncio, Model model) {
		model.addAttribute("show_annuncio_attr",
				AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioService.caricaSingoloElementoEager(idAnnuncio), true, true));
		return "showAnnuncio";
	}
	
	@RequestMapping("/annuncio/list")
	public String insertAnnuncio(@RequestParam(name = "utenteId") Long utenteId
			,Model model) {
		//model.addAttribute("categorie_totali_attr", CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAll()));
		//model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
		//model.addAttribute("insert_annuncio_attr", new AnnuncioDTO());
		//Utente utenteModel = utenteService.caricaSingoloUtenteConRuoli();
		//model.addAttribute("edit_utente_attr", UtenteDTO.buildUtenteDTOFromModel(utenteModel,true));
		
			model.addAttribute("annuncio_list_attribute",
					AnnuncioDTO.createAnnuncioDTOListFromModelList(annuncioService.FindAllAnnunciById(utenteId), true, true));
			
		return "annuncio/list";
	}
	
	@GetMapping("/annuncio/show/{idAnnuncio}")
	public String showAnnuncio(@PathVariable(required = true) Long idAnnuncio, Model model) {
		model.addAttribute("show_annuncio_attr",
				AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioService.caricaSingoloElementoEager(idAnnuncio), true, true));
		return "/annuncio/show";
	}
	
	@GetMapping("/annuncio/edit/{idAnnuncio}")
	public String edit(@PathVariable(required = true) Long idAnnuncio, Model model, HttpServletRequest request) {
		AnnuncioDTO annuncioDTO = AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioService.caricaSingoloElementoConCategorie(idAnnuncio), true, true);
		model.addAttribute("edit_annuncio_attr", annuncioDTO);
		model.addAttribute("categorie_totali_attr", CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAll()));
		return "annuncio/edit";
	}
	
	@PostMapping("/annuncio/update")
	public String update(@Valid @ModelAttribute("edit_annuncio_attr") AnnuncioDTO annuncioDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("categorie_totali_attr", CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAll()));
			return "annuncio/list";
		}
		
		annuncioDTO.setData(new Date());
		annuncioDTO.setAperto(true);
		UtenteDTO utenteInSessione = (UtenteDTO) request.getSession().getAttribute("userInfo");
		Long idUtenteSessione = utenteInSessione.getId();
		annuncioDTO.setUtenteInserimento(utenteInSessione);
		annuncioService.aggiorna(annuncioDTO.buildAnnuncioModel(true));

		redirectAttrs.addFlashAttribute("successMessage", "" + idUtenteSessione +"");
		redirectAttrs.addFlashAttribute("utenteId", idUtenteSessione);
		return "redirect:/home";
	}
	
	
	@GetMapping("/annuncio/delete/{idAnnuncio}")
	public String delete(@PathVariable(required = true) Long idAnnuncio, Model model, HttpServletRequest request) {
		AnnuncioDTO annuncioDTO = AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioService.caricaSingoloElementoConCategorie(idAnnuncio), false, true);
		model.addAttribute("delete_annuncio_attr", annuncioDTO);
		model.addAttribute("categorie_annuncio_attr", CategoriaDTO
				.createCategoriaDTOListFromModelList(categoriaService.findAllCategorieByIds(annuncioDTO.getCategorieIds())));
		return "annuncio/delete";
	}
	
	@PostMapping("/annuncio/remove")
	public String remove(@RequestParam Long idAnnuncio, RedirectAttributes redirectAttrs) {

		annuncioService.rimuovi(idAnnuncio);
		
		redirectAttrs.addFlashAttribute("successMessage", "Eliminazione eseguita correttamente");
		return "redirect:/home";
	}
}
