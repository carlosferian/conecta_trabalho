package com.example.conecta_trabalho.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.conecta_trabalho.database.AnuncioDatabase;
import com.example.conecta_trabalho.database.UsuarioDatabase;

import java.util.ArrayList;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){

    }

    public static DataModel getInstance(){
        return instance;
    }

    public Usuario usuario = new Usuario("teste", "1234");

    private ArrayList<Anuncio> anuncios;
    private AnuncioDatabase anuncioDatabase;
    private UsuarioDatabase usuarioDatabase;

    public void createDatabase(Context context){
        anuncioDatabase = new AnuncioDatabase(context);
        usuarioDatabase = new UsuarioDatabase(context);
        anuncios = anuncioDatabase.getAnunciosFromDB();

    }

    public boolean loginUsuario(String nome, String senha){
        return usuarioDatabase.checkCredencialDB(nome, senha);
    }

    public boolean addUsuario(Usuario u){
        long id = usuarioDatabase.createUsuarioInDB(u);
        if(id > 0){
            return true;
        }
        return false;
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
        long id = anuncioDatabase.createAnuncioInDB(a);
        if(id > 0){
            a.setId(id);
            anuncios.add(a);
            return true;
        }
        return false;
    }


    public boolean insertAnuncio(Anuncio a, int pos){
        long id = anuncioDatabase.insertAnuncioInDB(a);
        if(id > 0){
            anuncios.add(pos, a);
            return true;
        }
        return false;
    }

    public boolean updateAnuncio(Anuncio a, int pos){
        int count = anuncioDatabase.updateAnuncioInDB(a);
        if(count == 1){
            anuncios.set(pos, a);
            return true;
            }
        return false;
        }


    public boolean removeAnuncio(int pos){
        int count = anuncioDatabase.removeAnuncioInDB(getAnuncio(pos));
        if(count == 1){
            anuncios.remove(pos);
            return true;
        }
        return false;
    }
}

