package it.prova.myebay.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import it.prova.myebay.model.Annuncio;

public class AnnuncioDTO {

	
	private Long id;
	
	private String testoAnnuncio;
	
	private Integer prezzo;
	
	private Date data;
	
	private Boolean aperto;
	
	private UtenteDTO utenteInserimento;
	
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
	
	public Annuncio buildAnnuncioModel() {
		return new Annuncio(this.id, this.testoAnnuncio, this.prezzo, this.data, this.aperto,
				this.utenteInserimento.buildUtenteModel(true));
	}

	public static AnnuncioDTO buildAnnuncioDTOFromModel(Annuncio annuncioModel, boolean includeUtenti) {
		AnnuncioDTO result = new AnnuncioDTO(annuncioModel.getId(), annuncioModel.getTestoAnnuncio(), annuncioModel.getPrezzo(),
				annuncioModel.getData(), annuncioModel.getAperto());

		if (includeUtenti)
			result.setUtenteInserimento(UtenteDTO.buildUtenteDTOFromModel(annuncioModel.getUtenteInserimento(), true));
			

		return result;
	}

	public static List<AnnuncioDTO> createAnnuncioDTOListFromModelList(List<Annuncio> modelListInput, boolean includeUtenti) {
		return modelListInput.stream().map(annuncioEntity -> {
			return AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioEntity, includeUtenti);
		}).collect(Collectors.toList());
	}
	
}
