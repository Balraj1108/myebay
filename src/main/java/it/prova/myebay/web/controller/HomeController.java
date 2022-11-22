package it.prova.myebay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.CategoriaDTO;
import it.prova.myebay.dto.RuoloDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.service.CategoriaService;
import it.prova.myebay.service.RuoloService;
import it.prova.myebay.service.UtenteService;
import it.prova.myebay.validation.ValidationNoPassword;
import it.prova.myebay.validation.ValidationWithPassword;

@Controller
public class HomeController {
	
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private RuoloService ruoloService;
	
	@RequestMapping(value = {"/home",""})
	public String home(Model model) {
		model.addAttribute("categorie_totali_attr", CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAll()));
		model.addAttribute("insert_utente_attr", new UtenteDTO());
		return "index";
	}
	
	@GetMapping("/registrazione")
	public String registrazione(Model model) {
		model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
		model.addAttribute("insert_utente_attr", new UtenteDTO());
		return "registrazione";
	}
	
	@PostMapping("/registraUtente")
	public String registrazioneUtente(
			@Validated({ ValidationWithPassword.class,
					ValidationNoPassword.class }) @ModelAttribute("insert_utente_attr") UtenteDTO utenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (!result.hasFieldErrors("password") && !utenteDTO.getPassword().equals(utenteDTO.getConfermaPassword()))
			result.rejectValue("confermaPassword", "password.diverse");

		if (result.hasErrors()) {
			return "registrazione";
		}
		
		
		
		//utenteDTO.setStato(StatoUtente.CREATO);
		utenteService.inserisciNuovo(utenteDTO.buildUtenteModel(false));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente aspettare admin per attivazione");
		return "redirect:/login";
	}
}
