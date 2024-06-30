package it.unibo.teamnetscore.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Squadra implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String nomeSquadra;
    private File scudetto;
    private String descrizione;
    private Categoria categoria;
    
    private Allenatore allenatore;
    private List<Calciatore> calciatori;
    private List<EventoGenerale> eventi;

    // Costruttore senza argomenti
    public Squadra() {
        this.calciatori = new ArrayList<Calciatore>();
        this.eventi = new ArrayList<EventoGenerale>();
    }

    // Costruttore con argomenti
    public Squadra(String id, String nomeSquadra, File scudetto, String descrizione, 
                   Allenatore allenatore, List<Calciatore> calciatori, Categoria categoria) {
    
        this.eventi = new ArrayList<EventoGenerale>();
        this.id = id;
        this.nomeSquadra = nomeSquadra;
        this.scudetto = scudetto;
        this.descrizione = descrizione;
        this.allenatore = allenatore;
        this.calciatori = calciatori;
        this.categoria = categoria;
    }

    // Getter e Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeSquadra() {
        return nomeSquadra;
    }

    public void setNomeSquadra(String nomeSquadra) {
        this.nomeSquadra = nomeSquadra;
    }

    public File getScudetto() {
        return scudetto;
    }

    public void setScudetto(File scudetto) {
        this.scudetto = scudetto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Allenatore getAllenatore() {
        return allenatore;
    }

    public void setAllenatore(Allenatore allenatore) {
        this.allenatore = allenatore;
    }

    public List<Calciatore> getCalciatori() {
        return calciatori;
    }

    public void setCalciatori(List<Calciatore> calciatori) {
        this.calciatori = calciatori;
    }
    
    public boolean addCalciatore(Calciatore calciatore) {
        return this.calciatori.add(calciatore);
    }
    
     public boolean removeCalciatore(Calciatore calciatore) {
        return this.calciatori.remove(calciatore);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<EventoGenerale> getEventi() {
		return eventi;
	}

	public void setEventi(List<EventoGenerale> eventi) {
		this.eventi = eventi;
	}
	
	public boolean addEvento(EventoGenerale evento) {
		return this.eventi.add(evento);
	}
    
	public boolean removeEvento(EventoGenerale evento) {
		return this.eventi.remove(evento);
	}
	
    // toString per la rappresentazione in formato stringa
    @Override
    public String toString() {
        return "Squadra{" +
                "id='" + id + '\'' +
                ", nomeSquadra='" + nomeSquadra + '\'' +
                ", scudetto=" + scudetto +
                ", descrizione='" + descrizione + '\'' +
                ", allenatore=" + allenatore +
                ", calciatori=" + calciatori +
                ", categoria=" + categoria +
                '}';
    }

}
