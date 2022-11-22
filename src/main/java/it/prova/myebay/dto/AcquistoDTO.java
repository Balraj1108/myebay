package it.prova.myebay.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import it.prova.myebay.model.Acquisto;

public class AcquistoDTO {

	
	private Long id;
	
	private String descrizione;
	
	private Date data;
	
	private Integer prezzo;
	
	private UtenteDTO utenteAcquirente;
	
	public AcquistoDTO() {
		
	}

	public AcquistoDTO(Long id, String descrizione, Date data, Integer prezzo) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.data = data;
		this.prezzo = prezzo;
	}

	public AcquistoDTO(Long id, String descrizione, Date data, Integer prezzo, UtenteDTO utenteAcquirente) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.data = data;
		this.prezzo = prezzo;
		this.utenteAcquirente = utenteAcquirente;
	}
	
	

	public AcquistoDTO(String descrizione, Date data, Integer prezzo) {
		super();
		this.descrizione = descrizione;
		this.data = data;
		this.prezzo = prezzo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public UtenteDTO getUtenteAcquirente() {
		return utenteAcquirente;
	}

	public void setUtenteAcquirente(UtenteDTO utenteAcquirente) {
		this.utenteAcquirente = utenteAcquirente;
	}
	
	public Acquisto buildAcquistoModel() {
		Acquisto result = new Acquisto(this.id, this.descrizione, this.data, this.prezzo,
				this.utenteAcquirente.buildUtenteModel(true));

		return result;
	}
	
	public static AcquistoDTO buildAcquistoDTOFromModel(Acquisto acquistoModel, boolean includeUtenti) {
		AcquistoDTO result = new AcquistoDTO(acquistoModel.getId(), acquistoModel.getDescrizione(), acquistoModel.getData(),
				acquistoModel.getPrezzo());

		if (includeUtenti)
			result.setUtenteAcquirente(UtenteDTO.buildUtenteDTOFromModel(acquistoModel.getUtenteAcquirente(), true));
			

		return result;
	}
	
	public static List<AcquistoDTO> createAcquistoDTOListFromModelList(List<Acquisto> modelListInput, boolean includeUtenti) {
		return modelListInput.stream().map(acquistoEntity -> {
			return AcquistoDTO.buildAcquistoDTOFromModel(acquistoEntity, includeUtenti);
		}).collect(Collectors.toList());
	}
}
