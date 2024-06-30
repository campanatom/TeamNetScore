package it.unibo.teamnetscore.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Evento extends EventoGenerale {

	private static final long serialVersionUID = 1L;
	
	private LocalDate data;
	private LocalTime orario;
	private String luogo;
	private TipoEvento tipoEvento;
	private List<Presenza> presenze;

	public Evento(String id, LocalDateTime datainserimento, String descrizione, Squadra squadra,
			LocalDate data, LocalTime orario, String luogo, TipoEvento tipoevento) {
		super(id, datainserimento, descrizione, squadra);
		this.data = data;
		this.luogo = luogo;
		this.orario = orario;
		this.tipoEvento = tipoevento;
		
		this.presenze = new ArrayList<Presenza>();
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getOrario() {
		return orario;
	}

	public void setOrario(LocalTime orario) {
		this.orario = orario;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public List<Presenza> getPresenze() {
		return presenze;
	}

	public void setPresenze(List<Presenza> presenze) {
		this.presenze = presenze;
	}

	public boolean addPresenza(Presenza presenza) {
		return this.presenze.add(presenza);
	}
	
	public boolean removePresenza(Presenza presenza) {
		return this.presenze.remove(presenza);
	}
}
