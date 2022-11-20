package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Annuncio;

public interface AnnuncioService {

	public List<Annuncio> listAll() ;
	
	public Annuncio caricaSingoloElemento(Long id) ;

	public void aggiorna(Annuncio annuncioInstance) ;

	public void inserisciNuovo(Annuncio annuncioInstance) ;

	public void rimuovi(Long id);
	
	List<Annuncio> findByExample(Annuncio example);
	
	public Annuncio caricaSingoloElementoEager(Long id);
	
	public 	List<Annuncio> FindAllAnnunciById(Long id);
}
