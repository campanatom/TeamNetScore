package it.unibo.teamnetscore.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class EventoGenerale implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private LocalDateTime datainserimento;
	private String descrizione;
	private Squadra squadra;
	
	public EventoGenerale(String id, LocalDateTime datainserimento, String descrizione, Squadra squadra) {
		this.id = id;
		this.datainserimento = datainserimento;
		this.descrizione = descrizione;
		this.squadra = squadra;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public LocalDateTime getDatainserimento() {
		return datainserimento;
	}
	
	public void setDatainserimento(LocalDateTime datainserimento) {
		this.datainserimento = datainserimento;
	}
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public Squadra getSquadra() {
		return squadra;
	}
	
	public void setSquadra(Squadra squadra) {
		this.squadra = squadra;
	}

	@Override
	public String toString() {
		return "EventoGenerale [id=" + id + ", datainserimento=" + datainserimento + ", descrizione=" + descrizione
				+ ", squadra=" + squadra + "]";
	}
}
