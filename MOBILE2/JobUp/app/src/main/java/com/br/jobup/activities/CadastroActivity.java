package com.br.jobup.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import com.br.jobup.R;
import com.br.jobup.helpers.CadastroUsuarioHelper;
import com.br.jobup.models.usuario.Usuario;
import com.br.jobup.services.usuarioFullServices.parsers.ParserUsuarioFull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Created by Luiz Carlos Ramos on 6/7/17 12:01 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 5/25/17 12:35 AM
 */

public class CadastroActivity extends AppCompatActivity {
    public static final String TAG = CadastroActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Spinner spinner = (Spinner) findViewById(R.id.rgUfCadastroView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_salvar) {

            CadastroUsuarioHelper usuarioHelper = new CadastroUsuarioHelper(this);
             Usuario usuario = usuarioHelper.getUsuario();
            final Call<Usuario> usuarioCall = new ParserUsuarioFull().post(usuario);
            usuarioCall.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    Log.e(TAG, "onResponse: "+ response.message() );
                    Toast.makeText(CadastroActivity.this, "Cadastro feito com sucesso!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t );

                }
            });

            /*

//Recupera a data atual
Date dataAtual = Calendar.getInstance().getTime();

//Inicializa o SimpleDateFormat passando o formato no construtor
DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

//utilizaca o objeto dateFormat com o método format para formatar a data atual criada
String hoje = dateFormat.format(dataAtual);

//exibe na tela o resultado
Log.i("FORMATA_DATA", "Hoje é : " + hoje );




             */

            Log.e(TAG, "onNavigationItemSelected: "+ "mensagem enviada" );

            startActivity(new Intent(CadastroActivity.this, SingInActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
/*




final Call<Usuario> usuarioCall = new ParserUsuarioFull().post(usuario);
            usuarioCall.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    Log.e(TAG, "onResponse: "+ response.message() );
                    Toast.makeText(CadastroActivity.this, "Cadastro feito com sucesso!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t );

                }
            });
 */