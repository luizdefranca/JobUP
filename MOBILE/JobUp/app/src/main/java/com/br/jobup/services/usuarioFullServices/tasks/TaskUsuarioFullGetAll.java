package com.br.jobup.services.usuarioFullServices.tasks;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.br.jobup.models.usuario.Usuario;
import com.br.jobup.services.usuarioFullServices.parsers.ParserUsuarioFull;

import java.util.List;

/*
 * Created by Luiz Carlos Ramos on 01/05/17 12:18 at $today.hour24:18:34.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 01/05/17 10:33
 */

/**
 * Created by luizramos on 29/04/17.
 */





    public class TaskUsuarioFullGetAll extends AsyncTaskLoader<List<Usuario>> {
        public final String TAG = TaskUsuarioFullGetAll.class.getSimpleName();
        List<Usuario> mUsuarios;


        public TaskUsuarioFullGetAll(Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();

            if (mUsuarios == null){
                Log.e(TAG, "onStartLoading: forceLoad" );
                forceLoad();
            }else {
                Log.e(TAG, "onStartLoading: deliverResult");
                deliverResult(mUsuarios);
            }
        }

        @Override
        public List<Usuario> loadInBackground() {

            ParserUsuarioFull parser = new ParserUsuarioFull();

            mUsuarios = parser.getAll();
            return mUsuarios;
        }
    }




