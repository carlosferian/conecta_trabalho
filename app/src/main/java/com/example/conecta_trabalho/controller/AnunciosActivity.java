package com.example.conecta_trabalho.controller;

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

import com.example.conecta_trabalho.R;
import com.example.conecta_trabalho.model.Anuncio;
import com.example.conecta_trabalho.model.DataModel;
import com.google.android.material.snackbar.Snackbar;

public class AnunciosActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AnuncioAdapter adapter = new AnuncioAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);
        recyclerView = findViewById(R.id.recyclerviewAnuncios);
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
                //goToDetailActivity(position);
            }
        });
        adapter.setOnItemLongClickListener(new AnuncioAdapter.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(View view, int position){
                Anuncio a = DataModel.getInstance().anuncios.remove(position);
                adapter.notifyItemRemoved(position);

                View contextView = findViewById(android.R.id.content);
                Snackbar.make(contextView, R.string.removed, Snackbar.LENGTH_LONG)
                        .setAction(R.string.undo, new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                DataModel.getInstance().anuncios.add(position, a);
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
