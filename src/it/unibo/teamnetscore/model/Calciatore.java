package it.unibo.teamnetscore.model;

import java.util.ArrayList;
import java.util.List;

public class Calciatore extends Utente {

    private static final long serialVersionUID = 1L;
    
    private List<Squadra> squadre;
    private List<Presenza> presenze;
    
    // Costruttore vuoto
    public Calciatore() {
        super();
        this.setRuolo(Ruolo.CALCIATORE); // Imposta il ruolo di default come CALCIATORE
        this.squadre = new ArrayList<Squadra>();
    }

    // Costruttore con parametri
    public Calciatore(String nome, String cognome, String username, String password) {
        super(nome, cognome, username, password, Ruolo.CALCIATORE);
        this.squadre = new ArrayList<Squadra>();
     }
     
     // Getters and Setters
    public List<Squadra> getSquadre() {
        return squadre;
    }

    public void setSquadre(List<Squadra> squadre) {
        this.squadre = squadre;
    }
    
    public boolean addSquadra(Squadra squadra) {
        return this.squadre.add(squadra);
    }
    
    public boolean removeSquadra(Squadra squadra) {
        return this.squadre.remove(squadra);
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
    
    public boolean removeSquadra(Presenza presenza) {
        return this.presenze.remove(presenza);
    }
    
     // Metodo toString per includere anche gli attributi specifici di Allenatore
    @Override
    public String toString() {
        return super.toString() + " Squadre=" + this.squadre.toString();
    }
}
