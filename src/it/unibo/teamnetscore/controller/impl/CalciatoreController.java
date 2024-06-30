package it.unibo.teamnetscore.controller.impl;

import java.util.List;

import it.unibo.teamnetscore.controller.interfaces.ICalciatore;
import it.unibo.teamnetscore.model.Calciatore;
import it.unibo.teamnetscore.model.Squadra;

public class CalciatoreController implements ICalciatore {

	private Calciatore calciatore;
	
	public CalciatoreController(Calciatore calciatore) {
		super();
		this.calciatore = calciatore;
	}

	@Override
	public Calciatore getCalciatore() {
		return this.calciatore;
	}

	@Override
	public List<Squadra> getSquadre() {
		return this.calciatore.getSquadre();
	}

}
