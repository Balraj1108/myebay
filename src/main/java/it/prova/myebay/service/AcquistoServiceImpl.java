package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.repository.acquisto.AcquistoRepository;

@Service
public class AcquistoServiceImpl implements AcquistoService {

	@Autowired
	private AcquistoRepository acquistoRepository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Acquisto> listAll() {
		return (List<Acquisto>) acquistoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Acquisto caricaSingoloElemento(Long id) {
		return acquistoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Acquisto acquistoInstance) {
		acquistoRepository.save(acquistoInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Acquisto acquistoInstance) {
		acquistoRepository.save(acquistoInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long id) {
		acquistoRepository.deleteById(id);
	}

}
