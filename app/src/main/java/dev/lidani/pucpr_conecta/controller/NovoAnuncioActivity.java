package dev.lidani.pucpr_conecta.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dev.lidani.pucpr_conecta.R;
import dev.lidani.pucpr_conecta.model.Anuncio;
import dev.lidani.pucpr_conecta.model.DataModel;

import androidx.appcompat.app.AppCompatActivity;

public class NovoAnuncioActivity extends AppCompatActivity {
    EditText editTextAnuncianteNome;
    EditText editTextAnuncianteTelefone;
    EditText editTextAnuncianteRamo;
    EditText editTextAnuncioDescricao;

    Anuncio anuncio;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_anuncio);

        editTextAnuncianteNome = findViewById(R.id.editTextAnuncianteNome);
        editTextAnuncianteTelefone = findViewById(R.id.editTextAnuncianteTelefone);
        editTextAnuncianteRamo = findViewById(R.id.editTextAnuncianteRamo);
        editTextAnuncioDescricao = findViewById(R.id.editTextAnuncioDescricao);

        anuncio = (Anuncio) getIntent().getSerializableExtra("anuncio");
        pos = (int) getIntent().getIntExtra("pos", -1);

        if (this.isUpdate()) {

            editTextAnuncianteNome.setText(anuncio.getAnunciante());
            editTextAnuncianteTelefone.setText(anuncio.getTelefone());
            editTextAnuncianteRamo.setText(anuncio.getRamo());
            editTextAnuncioDescricao.setText(anuncio.getDescricao());

            TextView title = findViewById(R.id.novo_anuncio_titulo);
            title.setText("Editar Anúncio");

            Button button = findViewById(R.id.buttonAdicionarAnuncio);
            button.setText("Salvar");
        }
    }

    private boolean isUpdate() {
        return this.anuncio != null && this.pos > -1;
    }

    @Override
    public void onBackPressed(){
        this.adicionarNovoAnuncioOnClicked(null);
    }

    public void adicionarNovoAnuncioOnClicked(View v){
        String anunciante = editTextAnuncianteNome.getText().toString();
        String telefone = editTextAnuncianteTelefone.getText().toString();
        String ramo = editTextAnuncianteRamo.getText().toString();
        String descricao = editTextAnuncioDescricao.getText().toString();

        // update
        if (this.isUpdate()) {
            DataModel.getInstance().updateAnuncio(
                    new Anuncio(anunciante, telefone, ramo, descricao, this.anuncio.getId()),
                    this.pos
            );
        } else {
            DataModel.getInstance().addAnuncio(
                    new Anuncio(anunciante, telefone, ramo, descricao, null)
            );
        }

        finish();
    }

}
