package com.br.jobup.services.usuarioFullServices.loaders;

/*
 * Created by Luiz Carlos Ramos on 01/05/17 12:56 at $today.hour24:56:15.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 01/05/17 12:56
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.br.jobup.models.Usuario;
import com.br.jobup.services.usuarioFullServices.tasks.TaskUsuarioFullGet;
import com.br.jobup.services.usuarioFullServices.tasks.TaskUsuarioFullGetAll;

import java.util.List;

/**
 * Created by luizramos on 01/05/17.
 */

public class LoaderUsuarioFullGet implements LoaderManager.LoaderCallbacks<Usuario>{
    public static final String TAG = "LCFR/ " + LoaderUsuarioFullGetAll.class.getSimpleName();
    private final Context context;

    public LoaderUsuarioFullGet(Context context){
        this.context = context;
    }
    @Override
    public Loader<Usuario> onCreateLoader(int id, Bundle args) {
        return new TaskUsuarioFullGet(context);
    }

    @Override
    public void onLoadFinished(Loader<Usuario> loader, Usuario usuario) {

    }

    @Override
    public void onLoaderReset(Loader<Usuario> loader) {

    }
}
