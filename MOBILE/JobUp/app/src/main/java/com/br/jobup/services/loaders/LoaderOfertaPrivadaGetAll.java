package com.br.jobup.services.loaders;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;


import com.br.jobup.models.servico.Oferta;
import com.br.jobup.services.tasks.TaskOfertaPrivadaGetAll;

import java.util.List;

/*
 * Created by Luiz Carlos Ramos on 30/05/17 16:38
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/05/17 15:59
 */

/**
 * Created by luizramos on 30/04/17.
 */

public class LoaderOfertaPrivadaGetAll implements LoaderManager.LoaderCallbacks<List<Oferta>>{

    public static final String TAG = "LCFR/ " + LoaderOfertaPrivadaGetAll.class.getSimpleName();
    private final Context context;

    private String idUsuario;

    public LoaderOfertaPrivadaGetAll(Context context){
        this.context = context;
    }
    public LoaderOfertaPrivadaGetAll(String idUsuario, Context context){
        this.context = context;
        this.idUsuario = idUsuario;
    }
    @Override
    public Loader<List<Oferta>> onCreateLoader(int id, Bundle args) {
        return new TaskOfertaPrivadaGetAll(context, idUsuario);
    }

    @Override
    public void onLoadFinished(Loader<List<Oferta>> loader, List<Oferta> Ofertas) {

        if(Ofertas != null) {
            for (Oferta Oferta :
                    Ofertas) {
                Log.e(TAG, "onLoadFinished: " + Oferta.toString());
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Oferta>> loader) {

    }
}
