package it.unisa.emaa.www.sito.Model.entity;



public class Commento {
    private int id;
    private int idStoria;
    private String username;
    private String contenuto;

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

    public int getIdStoria() {
        return idStoria;
    }

    public void setIdStoria(int idStoria) {
        this.idStoria = idStoria;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}