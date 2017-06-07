package com.br.jobup.preferencesPersistence;

/*
 * Created by Luiz Carlos Ramos on 28/05/17 21:48
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 28/05/17 21:48
 */

import android.content.Context;

import com.github.hynra.gsonsharedpreferences.GSONSharedPreferences;
import com.github.hynra.gsonsharedpreferences.ParsingException;

/**
 * Created by luizramos on 28/05/17.
 */

public class PreferencePersistence<T> {

    private final Context context;

    public PreferencePersistence(Context context){
        this.context = context;
    }

    public T getObjectSavedInPreferences(String prefsNome, String NomeDaClasse){
        GSONSharedPreferences gsonSharedPrefs = new GSONSharedPreferences(context, prefsNome);
        T object = null;
        try {
            object = (T) gsonSharedPrefs.getObject(Class.forName(NomeDaClasse).newInstance());
//            Log.i("test", usuarioCorrente.getNome());
            //          Toast.makeText(this, "usuario corrente "+ usuarioCorrente.getNome(), Toast.LENGTH_SHORT).show();
        } catch (ParsingException | IllegalAccessException |InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public void saveObjectInPreferences(String prefsNome, T objeto){
        GSONSharedPreferences gsonSharedPrefs = new GSONSharedPreferences(context, prefsNome);
        gsonSharedPrefs.saveObject(objeto);
    }



}
