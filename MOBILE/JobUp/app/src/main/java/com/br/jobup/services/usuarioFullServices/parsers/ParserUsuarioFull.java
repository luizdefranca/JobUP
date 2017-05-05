package com.br.jobup.services.usuarioFullServices.parsers;

import android.util.Log;

import com.br.jobup.models.Usuario;
import com.br.jobup.services.RetroFitInicializador;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/*
 * Created by Luiz Carlos Ramos on 01/05/17 10:29 at $today.hour24:29:13.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/04/17 22:47
 */

/**
 * Created by luizramos on 29/04/17.
 */

public class ParserUsuarioFull {

    public static final String TAG = ParserUsuarioFull.class.getSimpleName();


    public  List<Usuario> getAll(){

        List<Usuario> usuarios = null;
        try {
            usuarios = (List<Usuario>) new RetroFitInicializador()
                    .createUsuarioFullAPI()
                    .getAll()
                    .execute()
                    .body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "getAll: Erro Baixando usuarios" , e );
        }

        return usuarios;

    }

    public Usuario get(){

        Usuario usuario = null;
        try {
            usuario = new RetroFitInicializador()
                    .createUsuarioAPI()
                    .get("id")
                    .execute()
                    .body();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "getAll: Erro Baixando usuarios" , e );
        }

        return usuario;

    }

    public Call<Usuario> post(Usuario usuario) {
        final Call<Usuario> callPost = new RetroFitInicializador()
                .createUsuarioAPI()
                .post(usuario);

        return callPost;
    }

    public Call<Usuario>  delete(Usuario usuario){
        final Call<Usuario> callDelete = new RetroFitInicializador()
                .createUsuarioAPI()
                .post(usuario);
        return callDelete;
    }
}
