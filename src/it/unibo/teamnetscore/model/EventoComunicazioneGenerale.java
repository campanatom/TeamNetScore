package it.unibo.teamnetscore.model;

import java.time.LocalDateTime;

public class EventoComunicazioneGenerale extends EventoGenerale {

	private static final long serialVersionUID = 1L;

	public EventoComunicazioneGenerale(String id, LocalDateTime datainserimento, String descrizione,
			Squadra squadra) {
		super(id, datainserimento, descrizione, squadra);
	}
	
}
