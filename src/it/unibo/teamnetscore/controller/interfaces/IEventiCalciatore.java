package it.unibo.teamnetscore.controller.interfaces;

import java.util.List;

import it.unibo.teamnetscore.model.Evento;
import it.unibo.teamnetscore.model.EventoGenerale;
import it.unibo.teamnetscore.model.Presenza;
import it.unibo.teamnetscore.model.Squadra;

public interface IEventiCalciatore {

	public List<Presenza> getPresenzeEvento(Evento evento);
	public boolean setPresenzaEvento(Evento evento, boolean presente);
	public List<EventoGenerale> visualizzaCalendarioSquadra(Squadra squadra);
	
}
