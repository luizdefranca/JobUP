package com.br.jobup.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.br.jobup.R;
import com.br.jobup.adapters.PropostaAdapter;
import com.br.jobup.models.servico.Proposta;

import java.util.List;


public class ListaPropostaActivity extends AppCompatActivity {

    private ListView mListPropostas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_proposta);

        mListPropostas = (ListView) findViewById(R.id.lVPropostas);
    }

    private void carregaListaPropostas(){
//        IPropostaDao dao = new PropostaDao(this);
//        List<Proposta> propostas = dao.getAll();
//        dao.close();
//        PropostaAdapter adapter = new PropostaAdapter(this, propostas);
//        mListPropostas.setAdapter(adapter);
    }
}
