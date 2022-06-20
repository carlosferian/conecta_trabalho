package dev.lidani.pucpr_conecta.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.lidani.pucpr_conecta.R;
import dev.lidani.pucpr_conecta.model.Anuncio;
import dev.lidani.pucpr_conecta.model.DataModel;
import com.google.android.material.snackbar.Snackbar;

public class AnunciosActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AnuncioAdapter adapter = new AnuncioAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);
        recyclerView = findViewById(R.id.recyclerviewAnuncios);

        DataModel.getInstance().createDatabase(getApplicationContext());


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(AnunciosActivity.this)
        );
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                AnunciosActivity.this, DividerItemDecoration.VERTICAL
        );
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setOnItemClickListener(new AnuncioAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Anuncio a = DataModel.getInstance().getAnuncio(position);
                botaoEditarAnuncio(a, position);
            }
        });
        adapter.setOnItemLongClickListener(new AnuncioAdapter.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(View view, int position){

                Anuncio a = DataModel.getInstance().getAnuncio(position);
                DataModel.getInstance().removeAnuncio(position);
                adapter.notifyItemRemoved(position);

                View contextView = findViewById(android.R.id.content);
                Snackbar.make(contextView, R.string.removed, Snackbar.LENGTH_LONG)
                        .setAction(R.string.undo, new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                DataModel.getInstance().insertAnuncio(a, position);
                                adapter.notifyItemInserted(position);
                            }
                        })
                        .show();
                return true;
            }
        });
    }
    static int counter = 0;
    public void botaoNovoAnuncio(View v){
        Intent intent = new Intent(AnunciosActivity.this, NovoAnuncioActivity.class);
        startActivity(intent);
    }

    public void botaoEditarAnuncio(Anuncio a, int pos) {
        Intent intent = new Intent(AnunciosActivity.this, NovoAnuncioActivity.class);
        intent.putExtra("anuncio", a);
        intent.putExtra("pos", pos);
        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menuAdicionaContato){
            Intent intent = new Intent(AnunciosActivity.this, NovoAnuncioActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
