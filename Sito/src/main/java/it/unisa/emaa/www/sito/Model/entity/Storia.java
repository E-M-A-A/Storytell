package it.unisa.emaa.www.sito.Model.entity;


import java.time.LocalDate;

/**
 *Classe entity Storia che è il rispettivo della tabella storia all'interno della base di dati
 * contiene gli attributi della tabella come variabili ed i metodi get e set utili per l'esecuzione
 * del design pattern dao
 *
 * @author Antonio Scotellaro
 *
 *

 */

public class Storia {

    private int id;
    private String username;
    private String contenuto;
    private int nReazioni;
    private int nCommenti;
    private LocalDate dataCreazione;
    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public Integer getNCommenti() {
        return nCommenti;
    }

    public void setNCommenti(Integer nCommenti) {
        this.nCommenti = nCommenti;
    }

    public Integer getNReazioni() {
        return nReazioni;
    }

    public void setNReazioni(Integer nReazioni) {
        this.nReazioni = nReazioni;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Storia(){

    }

    public Storia( String username, String contenuto, Integer nReazioni, Integer nCommenti, LocalDate dataCreazione) {
        this.username = username;
        this.contenuto = contenuto;
        this.nReazioni = nReazioni;
        this.nCommenti = nCommenti;
        this.dataCreazione = dataCreazione;
    }
}