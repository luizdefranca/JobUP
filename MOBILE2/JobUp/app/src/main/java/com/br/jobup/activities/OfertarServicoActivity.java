package com.br.jobup.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.br.jobup.R;
import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.services.parsers.ParserOfertaPrivada;
import com.br.jobup.validations.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Integer.getInteger;



/*
 * Created by Luiz Carlos Ramos on 6/7/17 12:02 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 5/31/17 5:12 PM
 */

public class OfertarServicoActivity extends AppCompatActivity {

    private static final String[] opcoes = {"O quanto antes possível", "nos próximos 30 dias",
            "Nos próximos três meses", "Não tenho certeza"};
    private static final String TAG = OfertarServicoActivity.class.getSimpleName();

    private String idUsuario;
    private String idProfissional;
    private int idEspecialidade;
    private String nomeProfissonal;
    ArrayAdapter<String> aOpcoes;
    Spinner mPrazo;
    private EditText mServicoTitulo;
    private EditText mServicoObservacao;
    private EditText mValorSugerido;
    private Button mBtnContratar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_servico);

        // Set Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Set Title on the ActionBar
        getSupportActionBar().setTitle("Contratar Servico");

        //Pega informacoes do Bundle DETALHE_CONTRATAR_SERVICO
        //Tais como o idUsuario, IdProfissional, idEspecialidade
        final Bundle bundle = getIntent()
                .getBundleExtra(CatalogoEspecialidadeActivity.DETALHE_CONTRATAR_SERVICO);
        idUsuario = bundle.getString(CatalogoEspecialidadeActivity.ID_USUARIO);
        idProfissional = bundle.getString(CatalogoEspecialidadeActivity.ID_PROFISSIONAL);
        nomeProfissonal = bundle.getString(CatalogoEspecialidadeActivity.NOME_PROFISSIONAL);
        idEspecialidade = bundle.getInt(CatalogoEspecialidadeActivity.ID_ESPECIALIDADE);

        //Inicializa e preenche o Spinner com o tempo de servico
        aOpcoes = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, opcoes);
        mPrazo = (Spinner) findViewById(R.id.spnPrazo);
        mPrazo.setAdapter(aOpcoes);

        //Inicializa os elementos da tela
        mServicoTitulo = (EditText) findViewById(R.id.contratacao_servico_titulo);
        mServicoObservacao = (EditText) findViewById(R.id.contratacao_servico_observacao);
        mValorSugerido = (EditText) findViewById(R.id.valor_sugerido);
        mBtnContratar = (Button) findViewById(R.id.contratacao_servico_contratar);

        //Cria evento onClick do Botao Contratar

        mBtnContratar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = mServicoTitulo.getText().toString();
                String observacao = mServicoObservacao.getText().toString();
                double valorSugerido = (mValorSugerido.getText().toString() != "") ? Double.parseDouble(mValorSugerido.getText().toString()): 0.0;
                int tempoServico = mPrazo.getSelectedItemPosition();
                ServicoOfertaPrivada ofertaPrivada =
                        new ServicoOfertaPrivada(idUsuario,
                            idProfissional,
         //                       nomeProfissonal,
                                idEspecialidade,
                                titulo,
                                observacao,
                                tempoServico,
                                valorSugerido);

                String idServico = Util.getUUID();
                ofertaPrivada.setIdServico(idServico);
                enviaOfertaPrivada(ofertaPrivada);
                startActivity(new Intent(OfertarServicoActivity.this, MainActivity.class));
            }
        });

    }

    /**
     * Envia uma oferta privada passando um objeto ServicoOfertaPrivada
     * @param ofertaPrivada
     */
    private void enviaOfertaPrivada(ServicoOfertaPrivada ofertaPrivada) {
        Log.e("LCFR " + TAG, "Entrada no método carregaCatalogoEspecialidade: " );


        final ParserOfertaPrivada parser = new ParserOfertaPrivada(idProfissional);
        parser.post(ofertaPrivada).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(OfertarServicoActivity.this, "Oferta privada enviada com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OfertarServicoActivity.this, "Falha ao enviar oferta privada!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "onFailure: ao enviar oferta", t);
                t.printStackTrace();
                Toast.makeText(OfertarServicoActivity.this, "Falha na conexão!", Toast.LENGTH_SHORT).show();
            }
        });

    }

//    Truque para não reiniciar a activity pai quando o botao up é acionado
    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        Intent it = super.getParentActivityIntent();
        if(it != null){
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        return it;
    }
}

