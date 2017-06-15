package com.br.jobup.services.loaders;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.services.tasks.TaskOfertaPrivadaClientesSemPropostaGetAll;

import java.util.List;

/*
 * Created by Luiz Carlos Ramos on 6/15/17 12:26 PM 
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/10/17 11:59 AM
 */

/**
 * Created by luizramos on 30/04/17.
 */

public class LoaderOfertaPrivadaClienteSemProposta implements LoaderManager.LoaderCallbacks<List<ServicoOfertaPrivada>>{

    public static final String TAG = "LCFR/ " + LoaderOfertaPrivadaClienteSemProposta.class.getSimpleName();
    private final Context context;

    private String idUsuarioCliente;

    public LoaderOfertaPrivadaClienteSemProposta(Context context){
        this.context = context;
    }
    public LoaderOfertaPrivadaClienteSemProposta(String idUsuarioCliente, Context context){
        this.context = context;
        this.idUsuarioCliente = idUsuarioCliente;
    }
    @Override
    public Loader<List<ServicoOfertaPrivada>> onCreateLoader(int id, Bundle args) {
        return new TaskOfertaPrivadaClientesSemPropostaGetAll(context, idUsuarioCliente);
    }

    @Override
    public void onLoadFinished(Loader<List<ServicoOfertaPrivada>> loader, List<ServicoOfertaPrivada> Ofertas) {

        if(Ofertas != null) {
            for (ServicoOfertaPrivada servicoOfertaPrivada :
                    Ofertas) {
                Log.e(TAG, "onLoadFinished: " + servicoOfertaPrivada.toString());
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ServicoOfertaPrivada>> loader) {

    }
}
