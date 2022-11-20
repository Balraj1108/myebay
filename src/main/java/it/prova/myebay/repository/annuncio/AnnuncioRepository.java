package it.prova.myebay.repository.annuncio;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Annuncio;

public interface AnnuncioRepository extends CrudRepository<Annuncio, Long>, JpaSpecificationExecutor<Annuncio>, CustomAnnuncioRepository {

	@Query("from Annuncio a join fetch a.utenteInserimento where a.id = ?1")
	Annuncio FindSingleAnnuncioEager(Long id);
	
}
