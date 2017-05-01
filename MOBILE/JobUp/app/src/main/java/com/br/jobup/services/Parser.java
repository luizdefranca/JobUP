package com.br.jobup.services;

import android.util.Log;

import java.io.IOException;
import java.util.List;

/**
 * Created by luizramos on 29/04/17.
 */

public class Parser<Usuario> {

    public static final String TAG = Parser.class.getSimpleName();


    public  List<Usuario> getAll(){

        List<Usuario> usuarios = null;
        try {
            usuarios = (List<Usuario>) new RetroFitInicializador()
                    .createUsuarioService()
                    .getAllUsuarios()
                    .execute()
                    .body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "getAll: Erro Baixando usuarios" , e );
        }

        return usuarios;

    }

}
