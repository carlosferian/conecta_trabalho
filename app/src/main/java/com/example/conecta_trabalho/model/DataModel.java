package com.example.conecta_trabalho.model;

import android.content.Context;

import com.example.conecta_trabalho.database.AnuncioDatabase;

import java.util.ArrayList;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){

    }

    public static DataModel getInstance(){
        return instance;
    }
    public Usuario usuario = new Usuario("mark", "1234");
    private ArrayList<Anuncio> anuncios;
    private AnuncioDatabase database;

    public void createDatabase(Context context){
        database = new AnuncioDatabase(context);
        anuncios = database.getAnunciosFromDB();
    }

    public ArrayList<Anuncio> getAnuncios(){
        return anuncios;
    }

    public Anuncio getAnuncio(int pos){
        return anuncios.get(pos);
    }

    public int getAnunciosSize(){
        return anuncios.size();
    }

    public boolean addAnuncio(Anuncio a){
        long id = database.createAnuncioInDB(a);
        if(id > 0){
            a.setId(id);
            anuncios.add(a);
            return true;
        }
        return false;
    }

    public boolean insertAnuncio(Anuncio a, int pos){
        long id = database.insertAnuncioInDB(a);
        if(id > 0){
            anuncios.add(pos, a);
            return true;
        }
        return false;
    }

    public boolean updateAnuncio(Anuncio a, int pos){
        int count = database.updateAnuncioInDB(a);
        if(count == 1){
            anuncios.set(pos, a);
            return true;
            }
        return false;
        }


    public boolean removeAnuncio(int pos){
        int count = database.removeAnuncioInDB(getAnuncio(pos));
        if(count == 1){
            anuncios.remove(pos);
            return true;
        }
        return false;
    }
}

