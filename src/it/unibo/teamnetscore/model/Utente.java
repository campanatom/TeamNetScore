package it.unibo.teamnetscore.model;

import java.io.Serializable;

public abstract class Utente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // Attributi
    private String nome;
    private String cognome;
    private String username;
    private String password; //Password in chiaro per semplicità
    private Ruolo ruolo; // Attributo di tipo Ruolo (collegato all'enumerazione)

    // Costruttore vuoto (necessario per i JavaBean)
    public Utente() {
    }

    // Costruttore con parametri
    public Utente(String nome, String cognome, String username, String password, Ruolo ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.ruolo = ruolo;
    }

    // Getter e setter per il nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e setter per il cognome
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    // Getter e setter per l'username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter e setter per il ruolo
    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    // Getter e setter per la password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Metodo toString per una rappresentazione leggibile dell'oggetto
    @Override
    public String toString() {
        return "Utente {" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", username='" + username + '\'' +
                ", ruolo=" + ruolo +
                '}';
    }
}
