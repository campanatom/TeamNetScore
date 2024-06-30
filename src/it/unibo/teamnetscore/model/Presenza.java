package it.unibo.teamnetscore.model;

public class Presenza {

	
	private Calciatore calciatore;
	private Evento evento;
	//L'attributo evidenzia l'eventuale presenza dell'utente
	// E' true se il calciatore e' presente, false altrimenti.
	private boolean presente;
	
	public Presenza(Calciatore calciatore, Evento evento, boolean presente) {
		
		this.calciatore = calciatore;
		this.evento = evento;
		this.presente = presente;
	}
	
	public Presenza() {
		super();
	}

	public Calciatore getCalciatore() {
		return calciatore;
	}
	public void setCalciatore(Calciatore calciatore) {
		this.calciatore = calciatore;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public boolean isPresente() {
		return presente;
	}
	public void setPresente(boolean presente) {
		this.presente = presente;
	}
}
