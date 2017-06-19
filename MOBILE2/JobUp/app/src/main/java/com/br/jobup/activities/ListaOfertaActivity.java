package com.br.jobup.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.br.jobup.R;


public class ListaOfertaActivity extends AppCompatActivity {

    private ListView mListPropostas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_oferta);

        mListPropostas = (ListView) findViewById(R.id.lVPropostas);
    }

    private void carregaListaPropostas(){
//        IPropostaDao dao = new PropostaDao(this);
//        List<Proposta> propostas = dao.getAll();
//        dao.close();
//        OfertaAdapter adapter = new OfertaAdapter(this, propostas);
//        mListPropostas.setAdapter(adapter);
    }
}
