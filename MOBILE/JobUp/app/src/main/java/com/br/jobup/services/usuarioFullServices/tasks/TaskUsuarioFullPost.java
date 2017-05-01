package com.br.jobup.services.usuarioFullServices.tasks;

/*
 * Created by Luiz Carlos Ramos on 5/1/17 5:01 PM at $today.hour24:1:44.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 5/1/17 5:01 PM
 */

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.br.jobup.models.Usuario;
import com.br.jobup.services.usuarioFullServices.parsers.ParserUsuarioFull;

/**
 * Created by luizramos on 01/05/17.
 */

public class TaskUsuarioFullPost extends AsyncTaskLoader<Usuario> {
    public final String TAG = TaskUsuarioFullGetAll.class.getSimpleName();

    Usuario mUsuario;


    public TaskUsuarioFullPost(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mUsuario == null){
            Log.e(TAG, "onStartLoading: forceLoad" );
            forceLoad();
        }else {
            Log.e(TAG, "onStartLoading: deliverResult");
            deliverResult(mUsuario);
        }
    }

    @Override
    public Usuario loadInBackground() {

        ParserUsuarioFull parser = new ParserUsuarioFull();

        mUsuario = parser.post(Usuario usuario);
        return mUsuario;
    }
}

