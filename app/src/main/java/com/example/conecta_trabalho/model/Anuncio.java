package com.example.conecta_trabalho.model;

import android.util.Log;

public class Anuncio {
    private long id;
    private String anunciante;
    private String telefone;
    private String ramo;
    private String descricao;

     public Anuncio(String anunciante, String telefone, String ramo, String descricao) {
        this.anunciante = anunciante;
        this.telefone = telefone;
        this.ramo = ramo;
        this.descricao = descricao;
    }

    public Anuncio(long id, String anunciante, String telefone, String ramo, String descricao) {
        this.id = id;
        this.anunciante = anunciante;
        this.telefone = telefone;
        this.ramo = ramo;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(String anunciante) {
        this.anunciante = anunciante;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void print(){
         Log.v("SQLDatabase", "Anunciante: " + anunciante);
    }
}
