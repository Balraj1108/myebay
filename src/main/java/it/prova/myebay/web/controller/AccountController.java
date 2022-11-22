package it.prova.myebay.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.UtenteCambioPasswordDTO;
import it.prova.myebay.service.UtenteService;
import it.prova.myebay.validation.ValidationNoPassword;
import it.prova.myebay.validation.ValidationWithPassword;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/cambioPassword")
	public String create(Model model) {
		model.addAttribute("insert_utente_attr", new UtenteCambioPasswordDTO());
		return "utente/cambioPassword";
	}
	
	@PostMapping("/save")
	public String save(
			@Validated({ ValidationWithPassword.class,
					ValidationNoPassword.class }) @ModelAttribute("insert_utente_attr") UtenteCambioPasswordDTO utenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, Principal principal) {

		
		if (!result.hasFieldErrors("vecchiaPassword") && !passwordEncoder.matches(utenteDTO.getVecchiaPassword(), utenteService.findByUsername(principal.getName()).getPassword())
				)
			result.rejectValue("vecchiaPassword", "password.diverse");
		
		if (!result.hasFieldErrors("nuovaPassword") && !utenteDTO.getNuovaPassword().equals(utenteDTO.getConfermaPassword()))
			result.rejectValue("confermaPassword", "password.diverse");

		if (result.hasErrors()) {
			return "utente/cambioPassword";
		}
		
		
		utenteService.findByUsername(principal.getName()).setPassword(passwordEncoder.encode(utenteDTO.getConfermaPassword()));
		utenteService.aggiorna(utenteService.findByUsername(principal.getName()));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/executeLogout";
	}
}
