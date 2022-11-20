package it.prova.myebay.repository.annuncio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Annuncio;

public interface AnnuncioRepository extends CrudRepository<Annuncio, Long>, JpaSpecificationExecutor<Annuncio>, CustomAnnuncioRepository {

	@Query("from Annuncio a join fetch a.utenteInserimento where a.id = ?1")
	Annuncio FindSingleAnnuncioEager(Long id);
	
	@Query("from Annuncio a left join fetch  a.utenteInserimento au where au.id = ?1")
	List<Annuncio> FindAllAnnunciById(Long id);
	
}
