package com.br.jobup.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.br.jobup.R;


public class ContraPropostaActivity extends AppCompatActivity {

    private static final String[] opcoes = {"O quanto antes possível", "nos próximos 30 dias",
            "Nos próximos três meses", "Não tenho certeza"};

    ArrayAdapter<String> aOpcoes;
    Spinner mPrazo;
    private EditText mObsContraProposta;
    private EditText mValorSugeridoContraProposta;
    private Button mBtnEnviarContraProposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contra_proposta);

        mObsContraProposta = (EditText) findViewById(R.id.contratacao_servico_observacao);
        mValorSugeridoContraProposta = (EditText) findViewById(R.id.valor_sugerido);
        mBtnEnviarContraProposta = (Button) findViewById(R.id.contratacao_servico_contratar);
        aOpcoes = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, opcoes);
        mPrazo = (Spinner) findViewById(R.id.spnPrazo);
        mPrazo.setAdapter(aOpcoes);

    }
}
