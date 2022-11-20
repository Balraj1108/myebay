package it.prova.myebay.repository.annuncio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;

public class AnnuncioRepositoryImpl implements CustomAnnuncioRepository {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Annuncio> findByExample(Annuncio example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select distinct a from Annuncio as a left join fetch a.categorie as ac where a.id = a.id ");

		if (StringUtils.isNotEmpty(example.getTestoAnnuncio())) {
			whereClauses.add(" a.testoAnnuncio  like :testoAnnuncio ");
			paramaterMap.put("testoAnnuncio", "%" + example.getTestoAnnuncio() + "%");
		}
		if (example.getPrezzo() != null) {
			whereClauses.add(" a.prezzo >= :prezzo ");
			paramaterMap.put("prezzo", example.getPrezzo());
		}
		for (Categoria categoria : example.getCategorie()) {
			
			if (categoria.getId() != null) {
				whereClauses.add(" ac.id =:id ");
				paramaterMap.put("id", categoria.getId());
			}
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Annuncio> typedQuery = entityManager.createQuery(queryBuilder.toString(), Annuncio.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
