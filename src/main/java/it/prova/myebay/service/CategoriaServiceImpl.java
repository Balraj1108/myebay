package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Categoria;
import it.prova.myebay.repository.categoria.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> listAll() {
		return (List<Categoria>)categoriaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria caricaSingoloElemento(Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Categoria categoriaInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Categoria categoriaInstance) {
		categoriaRepository.save(categoriaInstance);
	}

	@Override
	public void rimuovi(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria cercaPerDescrizioneECodice(String descrizione, String codice) {
		return categoriaRepository.findByDescrizioneAndCodice(descrizione, codice);
	}

}
