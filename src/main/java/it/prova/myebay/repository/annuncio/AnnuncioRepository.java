package it.prova.myebay.repository.annuncio;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Annuncio;

public interface AnnuncioRepository extends CrudRepository<Annuncio, Long>, JpaSpecificationExecutor<Annuncio>, CustomAnnuncioRepository {

	
}
