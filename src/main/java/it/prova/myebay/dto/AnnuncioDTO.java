package it.prova.myebay.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.validation.ValidationNoPassword;
import it.prova.myebay.validation.ValidationWithPassword;

public class AnnuncioDTO {

	
	private Long id;
	
	@NotBlank(message = "{nome.notblank}", groups = { ValidationWithPassword.class, ValidationNoPassword.class })
	private String testoAnnuncio;
	
	@NotNull(message = "{film.minutiDurata.notnull}")
	@Min(1)
	private Integer prezzo;
	
	private Date data;
	
	private Boolean aperto;
	
	private UtenteDTO utenteInserimento;
	
	private Long[] categorieIds;
	
	public AnnuncioDTO() {
		
	}

	public AnnuncioDTO(Long id, String testoAnnuncio, Integer prezzo, Date data, Boolean aperto,
			UtenteDTO utenteInserimento) {
		super();
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
		this.utenteInserimento = utenteInserimento;
	}

	public AnnuncioDTO(Long id, String testoAnnuncio, Integer prezzo, Date data, Boolean aperto) {
		super();
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestoAnnuncio() {
		return testoAnnuncio;
	}

	public void setTestoAnnuncio(String testoAnnuncio) {
		this.testoAnnuncio = testoAnnuncio;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getAperto() {
		return aperto;
	}

	public void setAperto(Boolean aperto) {
		this.aperto = aperto;
	}

	public UtenteDTO getUtenteInserimento() {
		return utenteInserimento;
	}

	public void setUtenteInserimento(UtenteDTO utenteInserimento) {
		this.utenteInserimento = utenteInserimento;
	}
	
	
	
	public Long[] getCategorieIds() {
		return categorieIds;
	}

	public void setCategorieIds(Long[] categorieIds) {
		this.categorieIds = categorieIds;
	}

	public Annuncio buildAnnuncioModel(boolean includeIdCategorie) {
		Annuncio result = new Annuncio(this.id, this.testoAnnuncio, this.prezzo, this.data, this.aperto,
				this.utenteInserimento.buildUtenteModel(true));
		if (includeIdCategorie && categorieIds != null)
			result.setCategorie(Arrays.asList(categorieIds).stream().map(id -> new Categoria(id)).collect(Collectors.toSet()));

		return result;
	}

	public static AnnuncioDTO buildAnnuncioDTOFromModel(Annuncio annuncioModel, boolean includeUtenti
			, boolean includeCategorie) {
		AnnuncioDTO result = new AnnuncioDTO(annuncioModel.getId(), annuncioModel.getTestoAnnuncio(), annuncioModel.getPrezzo(),
				annuncioModel.getData(), annuncioModel.getAperto());

		if (includeUtenti)
			result.setUtenteInserimento(UtenteDTO.buildUtenteDTOFromModel(annuncioModel.getUtenteInserimento(), true));
		
		if (annuncioModel.getCategorie() != null && includeCategorie && !annuncioModel.getCategorie().isEmpty())
			result.categorieIds = annuncioModel.getCategorie().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});	

		return result;
	}

	public static List<AnnuncioDTO> createAnnuncioDTOListFromModelList(List<Annuncio> modelListInput, boolean includeUtenti
			, boolean includeCategorie) {
		return modelListInput.stream().map(annuncioEntity -> {
			return AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioEntity, includeUtenti, includeCategorie);
		}).collect(Collectors.toList());
	}
	
}
