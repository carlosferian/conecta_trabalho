package com.example.conecta_trabalho.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.conecta_trabalho.R;
import com.example.conecta_trabalho.model.Anuncio;
import com.example.conecta_trabalho.model.DataModel;
import androidx.appcompat.app.AppCompatActivity;

public class NovoAnuncioActivity extends AppCompatActivity {
    EditText editTextAnuncianteNome;
    EditText editTextAnuncianteTelefone;
    EditText editTextAnuncianteRamo;
    EditText editTextAnuncioDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_anuncio);
        editTextAnuncianteNome = findViewById(R.id.editTextAnuncianteNome);
        editTextAnuncianteTelefone = findViewById(R.id.editTextAnuncianteTelefone);
        editTextAnuncianteRamo = findViewById(R.id.editTextAnuncianteRamo);
        editTextAnuncioDescricao = findViewById(R.id.editTextAnuncioDescricao);
    }
    @Override
    public void onBackPressed(){
        String anunciante = editTextAnuncianteNome.getText().toString();
        String telefone = editTextAnuncianteTelefone.getText().toString();
        String ramo = editTextAnuncianteRamo.getText().toString();
        String descricao = editTextAnuncioDescricao.getText().toString();

        DataModel.getInstance().addAnuncio(
                new Anuncio(anunciante, telefone, ramo, descricao)
        );
        finish();
    }

    public void adicionarNovoAnuncioOnClicked(View v){
        String anunciante = editTextAnuncianteNome.getText().toString();
        String telefone = editTextAnuncianteTelefone.getText().toString();
        String ramo = editTextAnuncianteRamo.getText().toString();
        String descricao = editTextAnuncioDescricao.getText().toString();

        DataModel.getInstance().addAnuncio(
                new Anuncio(anunciante, telefone, ramo, descricao)
        );
        finish();
    }

}
