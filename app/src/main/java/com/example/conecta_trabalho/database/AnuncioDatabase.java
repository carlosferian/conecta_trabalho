package com.example.conecta_trabalho.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.conecta_trabalho.model.Anuncio;

import java.util.ArrayList;

public class AnuncioDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "anuncios.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "anuncio";
    private static String COL_ID = "id";
    private static String COL_ANUNCIANTE = "anunciante";
    private static String COL_TELEFONE = "telefone";
    private static String COL_RAMO = "ramo";
    private static String COL_DESCRICAO = "descricao";


    private Context context;
    public AnuncioDatabase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String query = "Create Table if not exists "+ DB_TABLE + "( " +
                        COL_ID + " Integer primary key autoincrement, " +
                        COL_ANUNCIANTE + " TEXT, "+
                        COL_TELEFONE + " TEXT,"+
                        COL_RAMO + " TEXT,"+
                        COL_DESCRICAO + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){}

    /*CRUD*/
    public long createAnuncioInDB(Anuncio a){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ANUNCIANTE, a.getAnunciante());
        values.put(COL_TELEFONE, a.getTelefone());
        values.put(COL_RAMO, a.getTelefone());
        values.put(COL_DESCRICAO, a.getDescricao());
        long id = database.insert(DB_TABLE, null, values);
        database.close();
        return id;
    }

    public long insertAnuncioInDB(Anuncio a){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID, a.getId());
        values.put(COL_ANUNCIANTE, a.getAnunciante());
        values.put(COL_TELEFONE, a.getTelefone());
        values.put(COL_RAMO, a.getTelefone());
        values.put(COL_DESCRICAO, a.getDescricao());
        long id = database.insert(DB_TABLE, null, values);
        database.close();
        return id;
    }
    public ArrayList<Anuncio> getAnunciosFromDB(){
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DB_TABLE, null, null,
                null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                String anunciante = cursor.getString(cursor.getColumnIndexOrThrow(COL_ANUNCIANTE));
                String telefone = cursor.getString(cursor.getColumnIndexOrThrow(COL_TELEFONE));
                String ramo = cursor.getString(cursor.getColumnIndexOrThrow(COL_RAMO));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRICAO));
                anuncios.add(
                        new Anuncio(id, anunciante, telefone, ramo, descricao)
                );
            }while(cursor.moveToNext());
        }
        database.close();
        return anuncios;
    }

    public int updateAnuncioInDB(Anuncio anuncio){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ANUNCIANTE, anuncio.getAnunciante());
        values.put(COL_TELEFONE, anuncio.getTelefone());
        values.put(COL_RAMO, anuncio.getRamo());
        values.put(COL_DESCRICAO, anuncio.getDescricao());
        String id = String.valueOf(anuncio.getId());
        int count = database.update(DB_TABLE, values, COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }

    public int removeAnuncioInDB(Anuncio anuncio){
        SQLiteDatabase database = getWritableDatabase();
        String id = String.valueOf(anuncio.getId());
        int count = database.delete(DB_TABLE,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }
}



