package com.br.jobup.services.interfaces;

/*
 * Created by Luiz Carlos Ramos on 01/05/17 00:45 at $today.hour24:45:19.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 01/05/17 00:45
 */

import com.br.jobup.models.usuario.Formacao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by luizramos on 01/05/17.
 */

public interface IFormacaoAPI {
    public static final String PATH = "formacao/";


    @GET(PATH)
    Call<List<Formacao>> getAll(
            @Query("idUsuario") String idUsuario,
            @Query("idEspecialidade") int idEspecialidade
    );

    @GET(PATH)
    Call<Formacao> get(
            @Query("idUsuario") String idUsuario,
            @Query("idEspecialidade") int idEspecialide,
            @Query("idFormacao") int idFormacao
    );

    @POST(PATH)
    Call<Formacao> post(
            @Body Formacao formacao
    );

    @PUT(PATH)
    Call<Formacao> put(
            @Query("idUsuario") String idUsuario,
            @Query("idEspecialidade") int idEspecialide,
            @Query("idFormacao") int idFormacao
    );

    @DELETE(PATH)
    Call<Formacao> delete(
            @Query("idUsuario") String idUsuario,
            @Query("idEspecialidade") int idEspecialide,
            @Query("idFormacao") int idFormacao

    );
}
