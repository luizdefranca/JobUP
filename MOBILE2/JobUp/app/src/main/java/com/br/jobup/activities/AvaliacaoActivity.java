package com.br.jobup.activities;

/*
 * Created by Luiz Carlos Ramos on 17/06/17 22:42
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 17/06/17 22:42
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.br.jobup.R;
import com.br.jobup.models.usuario.Avaliacao;
import com.br.jobup.services.parsers.ParserAvaliarProfissional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andre on 17/06/2017.
 */

public class AvaliacaoActivity extends AppCompatActivity {

    private RatingBar ratingbar;

    private Button btnEnviar;


    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);
        // Set Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Set Title on the ActionBar
        getSupportActionBar().setTitle("Avaliacao");

        Intent intent = getIntent();
        String id_profissional = intent.getStringExtra("ID_PROFISSIONAL");
        final int idEspecialidade = intent.getIntExtra("ID_ESPECIALIDADE", 0);

        ratingbar = (RatingBar) findViewById(R.id.ratingBar);
        btnEnviar  = (Button) findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Avaliacao avaliacao = new Avaliacao();
                //AQUI
                ParserAvaliarProfissional parser = new ParserAvaliarProfissional(avaliacao);
                parser.post(avaliacao).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(AvaliacaoActivity.this, "Avaliação enviada", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(AvaliacaoActivity.this, CatalogoEspecialidadeActivity.class);
                            Bundle extras = new Bundle();
                            extras.putInt("idEspecialidade", idEspecialidade);
                            i.putExtras(extras);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }

    /**
     * Este método é utilizado para não pagar a activity quando usar o botao up
     * @return
     */
    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
}
