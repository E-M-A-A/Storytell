package it.unisa.emaa.www.sito.Model.entity;


import java.util.Objects;

/**
 *Classe entity Utente che è il rispettivo della tabella utente all'interno della base di dati
 * contiene gli attributi della tabella come variabili ed i metodi get e set utili per l'esecuzione
 * del design pattern dao
 *
 * @author Antonio Scotellaro
 *
 *

*/
public class Utente {
    private String id;
    private String password;
    private String username;

    @Override
    public String toString() {
        return "Utente{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return id.equals(utente.id) && Objects.equals(password, utente.password) && Objects.equals(username, utente.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}