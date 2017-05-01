package com.br.jobup.services.usuarioFullServices.loaders;

/*
 * Created by Luiz Carlos Ramos on 30/04/17 19:16 at $today.hour24:16:22.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/04/17 19:16
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.br.jobup.models.Usuario;
import com.br.jobup.services.usuarioFullServices.tasks.TaskUsuarioFullGetAll;

import java.util.List;

/**
 * Created by luizramos on 30/04/17.
 */

public class LoaderUsuarioFullGetAll implements LoaderManager.LoaderCallbacks<List<Usuario>>{

    public static final String TAG = "LCFR/ " + LoaderUsuarioFullGetAll.class.getSimpleName();
    private final Context context;

    public LoaderUsuarioFullGetAll(Context context){
        this.context = context;
    }
    @Override
    public Loader<List<Usuario>> onCreateLoader(int id, Bundle args) {
        return new TaskUsuarioFullGetAll(context);
    }

    @Override
    public void onLoadFinished(Loader<List<Usuario>> loader, List<Usuario> usuarios) {
        for (Usuario usuario :
                usuarios) {
            Log.e(TAG, "onLoadFinished: " + usuario.toString() );
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Usuario>> loader) {

    }
}
