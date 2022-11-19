package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import it.prova.myebay.model.Annuncio;
import it.prova.myebay.repository.annuncio.AnnuncioRepository;

@Service
public class AnnuncioServiceImpl implements AnnuncioService {

	
	@Autowired
	private AnnuncioRepository annuncioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Annuncio> listAll() {
		return (List<Annuncio>) annuncioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Annuncio caricaSingoloElemento(Long id) {
		return annuncioRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Annuncio annuncioInstance) {
		annuncioRepository.save(annuncioInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Annuncio annuncioInstance) {
		annuncioRepository.save(annuncioInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long id) {
		annuncioRepository.deleteById(id);
	}

}
