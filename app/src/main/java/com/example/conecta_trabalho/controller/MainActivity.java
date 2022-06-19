package com.example.conecta_trabalho.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.conecta_trabalho.R;
import com.example.conecta_trabalho.model.DataModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataModel.getInstance().createDatabase(getApplicationContext());
    }
    public void loginButtonOnClick(View v){
        EditText usernameEditText = findViewById(R.id.editTextUser);
        EditText passwordEditText = findViewById(R.id.editTextPassword);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(DataModel.getInstance().loginUsuario(username, password)) {
            // Toast.makeText(MainActivity.this, getString(R.string.user_logged),
            //        Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, AnunciosActivity.class);
            startActivity(intent);
            System.out.print((DataModel.getInstance().loginUsuario(username, password)));

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getString(R.string.error));
            builder.setMessage(getString(R.string.wrong_user));
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    usernameEditText.setText("");
                    passwordEditText.setText("");
                }
            });
            builder.create().show();
        }
    }
    public void cadastrarOnClick(View view){

        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

}