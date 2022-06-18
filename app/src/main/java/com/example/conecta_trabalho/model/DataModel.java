package com.example.conecta_trabalho.model;

import java.util.ArrayList;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){
        anuncios.add(new Anuncio("marcos", "3247-986", "construção", "pintura"));
        anuncios.add(new Anuncio("jeferson", "3247-7995", "confeitaria", "faz doces"));
        anuncios.add(new Anuncio("paulo", "123456", "alimentação", "faz pizza"));
    }

    public static DataModel getInstance(){
        return instance;
    }

    public Usuario usuario = new Usuario("mark", "1234");

    public ArrayList<Anuncio> anuncios = new ArrayList<>();


}

