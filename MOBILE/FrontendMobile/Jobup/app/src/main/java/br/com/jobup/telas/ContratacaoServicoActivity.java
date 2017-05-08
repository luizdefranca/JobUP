package br.com.jobup.telas;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import br.com.jobup.R;

public class ContratacaoServicoActivity extends AppCompatActivity {

    private static final String[] opcoes = {"O mais rapidp possivel", "Ainda essa semana", "Vamos combinar"};
    ArrayAdapter<String> aOpcoes;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratacao_servico);

        aOpcoes = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, opcoes);
        spinner = (Spinner) findViewById(R.id.spnOpcoes);
        spinner.setAdapter(aOpcoes);


    }
}

