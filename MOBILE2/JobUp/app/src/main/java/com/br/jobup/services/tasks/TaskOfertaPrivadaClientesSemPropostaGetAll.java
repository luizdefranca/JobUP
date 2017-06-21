package com.br.jobup.services.tasks;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import com.br.jobup.models.servico.Oferta;
import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.services.parsers.ParserOfertaPrivada;
import com.br.jobup.services.parsers.ParserOfertaPrivadaClienteSemProposta;

import java.util.List;

/*
 * Created by Luiz Carlos Ramos on 6/15/17 12:33 PM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/15/17 12:33 PM
 */

public class TaskOfertaPrivadaClientesSemPropostaGetAll extends AsyncTaskLoader<List<ServicoOfertaPrivada>> {

    public static final String TAG = "LCFR -> " + TaskOfertaPrivadaClientesSemPropostaGetAll.class.getSimpleName();
    private String idUsuarioCliente;
    List<ServicoOfertaPrivada> mOfertasPrivadasSemPropostas;


    public TaskOfertaPrivadaClientesSemPropostaGetAll(Context context, String idUsuarioCliente) {
        super(context);
        this.idUsuarioCliente = idUsuarioCliente;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mOfertasPrivadasSemPropostas == null){
            Log.e(TAG, "onStartLoading: forceLoad" );
            forceLoad();
        }else {
            Log.e(TAG, "onStartLoading: deliverResult");
            deliverResult(mOfertasPrivadasSemPropostas);
        }
    }

    @Override
    public List<ServicoOfertaPrivada> loadInBackground() {

        ParserOfertaPrivadaClienteSemProposta parser = new ParserOfertaPrivadaClienteSemProposta(idUsuarioCliente);

        mOfertasPrivadasSemPropostas = parser.getAllPorIdUsuarioCliente();
        return mOfertasPrivadasSemPropostas;
    }

    @Override
    public void deliverResult(List<ServicoOfertaPrivada> data) {
        if(isStarted() && ! isReset()) {
            super.deliverResult(data);
        }
    }


}


