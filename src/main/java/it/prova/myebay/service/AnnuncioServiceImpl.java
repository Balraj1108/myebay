package it.prova.myebay.service;

import java.util.Date;
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

	@Override
	@Transactional(readOnly = true)
	public List<Annuncio> findByExample(Annuncio example) {
		// TODO Auto-generated method stub
		return annuncioRepository.findByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public Annuncio caricaSingoloElementoEager(Long id) {
		// TODO Auto-generated method stub
		return annuncioRepository.FindSingleAnnuncioEager(id);
	}

	@Override
	public List<Annuncio> FindAllAnnunciById(Long id) {
		// TODO Auto-generated method stub
		return annuncioRepository.FindAllAnnunciById(id);
	}

	@Override
	public Annuncio caricaSingoloElementoConCategorie(Long id) {
		// TODO Auto-generated method stub
		return annuncioRepository.findByIdConCategorie(id).orElse(null);
	}

}
