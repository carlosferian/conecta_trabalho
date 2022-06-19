package com.example.conecta_trabalho.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.conecta_trabalho.R;
import com.example.conecta_trabalho.model.DataModel;
import com.example.conecta_trabalho.model.Usuario;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {
    TextView usuarioCad, senhaCad;
    Button cadastraUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        DataModel.getInstance().createDatabase(getApplicationContext());

        usuarioCad = findViewById(R.id.usuarioCadastro);
        senhaCad = findViewById(R.id.senhaCadastro);
        cadastraUser = findViewById(R.id.cadastrarUsuarioBtn);
    }
    public void cadastrarUsuarioInDB(View view){

        Usuario usuario= new Usuario(usuarioCad.getText().toString(), senhaCad.getText().toString());
        DataModel.getInstance().addUsuario(usuario);
        Toast.makeText(this, "Usuário inserido" ,Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
