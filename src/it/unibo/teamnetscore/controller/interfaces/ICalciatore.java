package it.unibo.teamnetscore.controller.interfaces;

import java.util.List;
import it.unibo.teamnetscore.model.Calciatore;
import it.unibo.teamnetscore.model.Squadra;

public interface ICalciatore {

	public Calciatore getCalciatore();
	public List<Squadra> getSquadre();
}
