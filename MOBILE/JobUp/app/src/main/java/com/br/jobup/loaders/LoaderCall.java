package com.br.jobup.loaders;

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

import com.br.jobup.MainActivity;
import com.br.jobup.models.Usuario;
import com.br.jobup.services.GetAllTask;

import java.util.List;

/**
 * Created by luizramos on 30/04/17.
 */

public class LoaderCall implements LoaderManager.LoaderCallbacks<List<Usuario>>{

    public static final String TAG = "LCFR/ " + LoaderCall.class.getSimpleName();
    private final Context context;

    public LoaderCall(Context context){
        this.context = context;
    }
    @Override
    public Loader<List<Usuario>> onCreateLoader(int id, Bundle args) {
        return new GetAllTask(context);
    }

    @Override
    public void onLoadFinished(Loader<List<Usuario>> loader, List<Usuario> data) {
        List<Usuario> usuarios = data;
        for (Usuario usuario :
                usuarios) {
            Log.e(TAG, "onLoadFinished: " + usuario );
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Usuario>> loader) {

    }
}
