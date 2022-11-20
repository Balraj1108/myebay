package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Acquisto;

public interface AcquistoService {
	public List<Acquisto> listAll() ;
	
	public Acquisto caricaSingoloElemento(Long id) ;

	public void aggiorna(Acquisto acquistoInstance) ;

	public void inserisciNuovo(Acquisto acquistoInstance) ;

	public void rimuovi(Long id) ;	
	
	public List<Acquisto> FindAllAcquistiById(Long id);
}
