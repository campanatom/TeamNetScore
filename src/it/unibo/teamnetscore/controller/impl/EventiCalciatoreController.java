package it.unibo.teamnetscore.controller.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.teamnetscore.controller.interfaces.ICalciatore;
import it.unibo.teamnetscore.controller.interfaces.IEventiCalciatore;
import it.unibo.teamnetscore.dao.EventoGeneraleDAO;
import it.unibo.teamnetscore.dao.PresenzaDAO;
import it.unibo.teamnetscore.model.Evento;
import it.unibo.teamnetscore.model.EventoGenerale;
import it.unibo.teamnetscore.model.Presenza;
import it.unibo.teamnetscore.model.Squadra;

public class EventiCalciatoreController implements IEventiCalciatore {

	private ICalciatore calciatoreController;
	
	public EventiCalciatoreController(ICalciatore calciatoreController) {
		super();
		this.calciatoreController = calciatoreController;
	}

	public List<Presenza> getPresenzeEvento(Evento evento){
		
		List<Presenza> result = new ArrayList<Presenza>();
		PresenzaDAO presenzaDAO = new PresenzaDAO();
		
		result = presenzaDAO.getPresenzeByEvento(evento);
		return result;
	}
	
	public boolean setPresenzaEvento(Evento evento, boolean presente) {
		
		boolean result;
		
		try {
			//Check is Presenza is already present
			PresenzaDAO presenzaDAO = new PresenzaDAO();
			Presenza presenza = presenzaDAO.getPresenzaByIds(evento, calciatoreController.getCalciatore());
			
			if (!presenza.equals(null)) {
				presenza.setPresente(presente);
				presenzaDAO.updatePresenza(presenza);
			}
			else {
				presenza = new Presenza(calciatoreController.getCalciatore(), evento, presente);
				presenzaDAO.savePresenza(presenza);
			}
			
			result = true;
		}
		catch (Exception e)
		{ result = false; }
		
		return result;
	}
	
	//Restituisce tutti gli eventi per la squadra selezionata.
	public List<EventoGenerale> visualizzaCalendarioSquadra(Squadra squadra)
	{
		EventoGeneraleDAO eventoDAO = new EventoGeneraleDAO();
		return eventoDAO.getEventiBySquadra(squadra);
	}
	
}
