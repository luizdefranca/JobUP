package com.br.jobup.services;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.br.jobup.models.usuario.Usuario;

/*
 * Created by Luiz Carlos Ramos on 30/04/17 16:01 at $today.hour24:1:6.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/04/17 16:01
 */

/**
 * Created by luizramos on 30/04/17.
 */

public class TemplateUsuarioLoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(0, null, new LoaderCallBacks());
    }

    private class LoaderCallBacks implements android.app.LoaderManager.LoaderCallbacks<Usuario>{


        @Override
        public android.content.Loader<Usuario> onCreateLoader(int i, Bundle bundle) {
            return null;
        }

        @Override
        public void onLoadFinished(android.content.Loader<Usuario> loader, Usuario usuario) {

        }

        @Override
        public void onLoaderReset(android.content.Loader<Usuario> loader) {

        }
    }
}
