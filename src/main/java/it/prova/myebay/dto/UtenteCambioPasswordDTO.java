package it.prova.myebay.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;
import it.prova.myebay.validation.ValidationNoPassword;
import it.prova.myebay.validation.ValidationWithPassword;

public class UtenteCambioPasswordDTO {

	/*private Long id;

	
	private String username;*/

	@NotBlank(message = "{password.notblank}", groups = ValidationWithPassword.class)
	@Size(min = 8, max = 15, message = "Il valore inserito deve essere lungo tra {min} e {max} caratteri")
	private String vecchiaPassword;
	
	@NotBlank(message = "{password.notblank}", groups = ValidationWithPassword.class)
	@Size(min = 8, max = 15, message = "Il valore inserito deve essere lungo tra {min} e {max} caratteri")
	private String nuovaPassword;

	private String confermaPassword;

	/*private String nome;

	private String cognome;

	private Date dateCreated;
	
	
	private Integer creditoResiduo;

	private StatoUtente stato;

	private Long[] ruoliIds;*/
	
	
	public UtenteCambioPasswordDTO() {
	}

	/*public UtenteCambioPasswordDTO(Long id, String username, String nome, String cognome, StatoUtente stato
			,Integer creditoResiduo) {
		super();
		this.id = id;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.stato = stato;
		this.creditoResiduo = creditoResiduo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}*/

	public String getConfermaPassword() {
		return confermaPassword;
	}

	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}

	/*public Long[] getRuoliIds() {
		return ruoliIds;
	}

	public void setRuoliIds(Long[] ruoliIds) {
		this.ruoliIds = ruoliIds;
	}
	
	
	
	public Integer getCreditoResiduo() {
		return creditoResiduo;
	}

	public void setCreditoResiduo(Integer creditoResiduo) {
		this.creditoResiduo = creditoResiduo;
	}*/
	
	


	public String getVecchiaPassword() {
		return vecchiaPassword;
	}

	public void setVecchiaPassword(String vecchiaPassword) {
		this.vecchiaPassword = vecchiaPassword;
	}

	public String getNuovaPassword() {
		return nuovaPassword;
	}

	public void setNuovaPassword(String nuovaPassword) {
		this.nuovaPassword = nuovaPassword;
	}

	/*public boolean isAttivo() {
		return this.stato != null && this.stato.equals(StatoUtente.ATTIVO);
	}

	public Utente buildUtenteModel1(boolean includeIdRoles) {
		Utente result = new Utente(this.id, this.username, this.nuovaPassword, this.nome, this.cognome, this.dateCreated,
				this.stato, this.creditoResiduo);
		if (includeIdRoles && ruoliIds != null)
			result.setRuoli(Arrays.asList(ruoliIds).stream().map(id -> new Ruolo(id)).collect(Collectors.toSet()));

		return result;
	}

	// niente password...
	public static UtenteCambioPasswordDTO buildUtenteDTOFromModel1(Utente utenteModel, boolean includeRoles) {
		UtenteCambioPasswordDTO result = new UtenteCambioPasswordDTO(utenteModel.getId(), utenteModel.getUsername(), utenteModel.getNome(),
				utenteModel.getCognome(), utenteModel.getStato(), utenteModel.getCreditoResiduo());

		if (includeRoles && !utenteModel.getRuoli().isEmpty())
			result.setRuoliIds(utenteModel.getRuoli().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {}));

		return result;
	}

	public static List<UtenteCambioPasswordDTO> createUtenteDTOListFromModelList(List<Utente> modelListInput, boolean includeRoles) {
		return modelListInput.stream().map(utenteEntity -> {
			return UtenteCambioPasswordDTO.buildUtenteDTOFromModel1(utenteEntity, includeRoles);
		}).collect(Collectors.toList());
	}*/
}
