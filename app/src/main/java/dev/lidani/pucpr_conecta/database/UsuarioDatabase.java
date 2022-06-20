package dev.lidani.pucpr_conecta.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import dev.lidani.pucpr_conecta.model.Usuario;

public class UsuarioDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "usuarios.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "usuario";
    private static String COL_ID = "id";
    private static String COL_NOME = "nome";
    private static String COL_SENHA = "senha";

    private Context context;
    public UsuarioDatabase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String query = "Create Table if not exists " + DB_TABLE + "( " +
                        COL_ID + " Integer primary key autoincrement, " +
                        COL_NOME + " TEXT, " +
                        COL_SENHA + " TEXT )";
        sqLiteDatabase.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){}

    /*CRUD*/
    public long createUsuarioInDB(Usuario u){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOME, u.getUsername());
        values.put(COL_SENHA, u.getPassword());
        long id = database.insert(DB_TABLE, null, values);
        database.close();
        return id;
    }

    public boolean checkCredencialDB(String nome, String senha){
        SQLiteDatabase database = getReadableDatabase();
        String query = "Select nome,senha FROM usuario WHERE nome=? AND senha=?";
        String [] selection = {nome, senha};
        try {
            Cursor cursor = database.rawQuery(query, selection);
            cursor.moveToFirst();

            if(cursor.getCount() > 0){
                Log.i("Passou no if", "loginUsuario: paparara ");
                return true;
            }
            cursor.close();
            database.close();

            return false;
        }catch (Exception e){
            Log.d("Erro", "loginUsuario() returned: " + true);
            e.printStackTrace();
        }

        return true;
    }
}
