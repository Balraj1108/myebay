package it.prova.myebay.repository.acquisto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;


public interface AcquistoRepository extends CrudRepository<Acquisto, Long> {

	@Query("from Acquisto a left join fetch  a.utenteAcquirente au where au.id = ?1")
	List<Acquisto> FindAllAcquistiById(Long id);
}
