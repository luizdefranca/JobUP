package com.br.jobup.services.usuarioFullServices.loaders;

/*
 * Created by Luiz Carlos Ramos on 5/1/17 4:57 PM at $today.hour24:57:46.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 5/1/17 4:57 PM
 */


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.br.jobup.models.Usuario;
import com.br.jobup.services.usuarioFullServices.tasks.TaskUsuarioFullGetAll;
import com.br.jobup.services.usuarioFullServices.tasks.TaskUsuarioFullPost;

import java.util.List;

/**
 * Created by luizramos on 01/05/17.
 */

public class LoaderUsuarioFullPost implements LoaderManager.LoaderCallbacks<Usuario>{
    public static final String TAG = "LCFR/ " + LoaderUsuarioFullPost.class.getSimpleName();
    private final Context context;

    public LoaderUsuarioFullPost(Context context, Usuario usuario){
        this.context = context;
    }
    @Override
    public Loader<Usuario> onCreateLoader(int id, Bundle args) {
        return new TaskUsuarioFullPost(context);
    }

    @Override
    public void onLoadFinished(Loader<Usuario> loader, Usuario usuarios) {

    }

    @Override
    public void onLoaderReset(Loader<Usuario> loader) {

    }
}
