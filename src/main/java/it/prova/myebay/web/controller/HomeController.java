package it.prova.myebay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.myebay.dto.CategoriaDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.service.CategoriaService;

@Controller
public class HomeController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value = {"/home",""})
	public String home(Model model) {
		model.addAttribute("categorie_totali_attr", CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAll()));
		model.addAttribute("insert_utente_attr", new UtenteDTO());
		return "index";
	}
}
