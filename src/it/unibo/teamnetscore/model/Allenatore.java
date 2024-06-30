package it.unibo.teamnetscore.model;

public class Allenatore extends Utente {

    private static final long serialVersionUID = 1L;
	private Squadra squadra;

    // Costruttore vuoto
    public Allenatore() {
        super();
        this.setRuolo(Ruolo.ALLENATORE); // Imposta il ruolo di default come ALLENATORE
        this.squadra = null;
    }

    // Costruttore con parametri
    public Allenatore(String nome, String cognome, String username, String password) {
        super(nome, cognome, username, password, Ruolo.ALLENATORE);
        this.squadra = null;
    }
    
    public Squadra getSquadra(){
        return this.squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }
    
    // Metodo toString per includere anche gli attributi specifici di Allenatore
    @Override
    public String toString() {
        return super.toString() + " squadra=" + this.squadra.toString();
    }
}
