package com.br.jobup.services.tasks;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.br.jobup.models.servico.Oferta;
import com.br.jobup.services.parsers.ParserOfertaPrivada;

import java.util.List;

/*
 * Created by Luiz Carlos Ramos on 30/05/17 16:39
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 16/05/17 03:49
 */

/**
 * Created by luizramos on 29/04/17.
 */





    public class TaskOfertaPrivadaGetAll extends AsyncTaskLoader<List<Oferta>> {
        public final String TAG = TaskOfertaPrivadaGetAll.class.getSimpleName();
        List<Oferta> mOferta;
        private String idUsuario;

        public TaskOfertaPrivadaGetAll(Context context, String idUsuario) {
            super(context);
            this.idUsuario = idUsuario;
        }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mOferta == null) {
            Log.e(TAG, "onStartLoading: forceLoad");
            forceLoad();
        } else {
            Log.e(TAG, "onStartLoading: deliverResult");
            deliverResult(mOferta);
        }
    }

    @Override
    public List<Oferta> loadInBackground() {

        ParserOfertaPrivada parser = new ParserOfertaPrivada(idUsuario);

        mOferta = parser.getAllPorIdUsuario();
        return mOferta;
    }

    @Override
    public void deliverResult(List<Oferta> data) {
        if (isStarted() && !isReset()) {
            super.deliverResult(data);
        }
    }
}




