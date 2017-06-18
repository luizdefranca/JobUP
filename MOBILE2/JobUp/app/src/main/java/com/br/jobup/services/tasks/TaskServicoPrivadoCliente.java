package com.br.jobup.services.tasks;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.services.parsers.ParserServicoPrivadoCliente;

import java.util.List;

/*
 * Created by Luiz Carlos Ramos on 6/16/17 12:24 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/15/17 11:10 PM
 */

public class TaskServicoPrivadoCliente extends AsyncTaskLoader<List<ServicoOfertaPrivada>> {

    public static final String TAG = "LCFR -> " + TaskServicoPrivadoCliente.class.getSimpleName();
    private String idUsuarioCliente;
    List<ServicoOfertaPrivada> mOfertasPrivadasComPropostas;


    public TaskServicoPrivadoCliente(Context context, String idUsuarioCliente) {
        super(context);
        this.idUsuarioCliente = idUsuarioCliente;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mOfertasPrivadasComPropostas == null){
            Log.e(TAG, "onStartLoading: forceLoad" );
            forceLoad();
        }else {
            Log.e(TAG, "onStartLoading: deliverResult");
            deliverResult(mOfertasPrivadasComPropostas);
        }
    }

    @Override
    public List<ServicoOfertaPrivada> loadInBackground() {

        ParserServicoPrivadoCliente parser = new ParserServicoPrivadoCliente(idUsuarioCliente);

        mOfertasPrivadasComPropostas = parser.getAllPorIdUsuarioCliente();
        return mOfertasPrivadasComPropostas;
    }

    @Override
    public void deliverResult(List<ServicoOfertaPrivada> data) {
        if(isStarted() && ! isReset()) {
            super.deliverResult(data);
        }
    }
}


