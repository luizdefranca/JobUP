package com.br.jobup.services.usuarioFullServices.parsers;

import android.util.Log;

import com.br.jobup.models.servico.Oferta;
import com.br.jobup.models.usuario.Usuario;
import com.br.jobup.services.RetroFitInicializador;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/*
 * Created by Luiz Carlos Ramos on 30/05/17 16:15 
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 16/05/17 03:49
 */

/**
 * Created by luizramos on 29/04/17.
 */

public class ParserOfertaPrivada {

    public static final String TAG = ParserOfertaPrivada.class.getSimpleName();


    public  List<Oferta> getAll(String idUsuario){

        List<Oferta> ofertas = null;
        ofertas = (List<Oferta>) new RetroFitInicializador()
                .createOfertaAPI()
                .getOfertas(idUsuario);
        return ofertas;

    }

    public Usuario get(String id){

        Usuario usuario = null;
        try {
            usuario = new RetroFitInicializador()
                    .createUsuarioAPI()
                    .get(id)
                    .execute()
                    .body();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "getAll: Erro Baixando ofertas" , e );
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
