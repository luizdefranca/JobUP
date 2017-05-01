package com.br.jobup.services.usuarioServices.interfaces;

/*
 * Created by Luiz Carlos Ramos on 01/05/17 00:45 at $today.hour24:45:19.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 01/05/17 00:45
 */

import com.br.jobup.models.Formacao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by luizramos on 01/05/17.
 */

public interface IFormacao {
    public static final String PATH = "formacao/";


    @GET(PATH)
    Call<List<Formacao>> getAll(
            @Query("ID_USUARIO") String idUsuario,
            @Query("ID_ESPECIALIDADE") int idEspecialidade
    );

    @GET(PATH)
    Call<Formacao> get(
            @Query("ID_USUARIO") String idUsuario,
            @Query("ID_ESPECIALIDADE") int idEspecialide,
            @Query("ID_FORMACAO") int idFormacao
    );

    @POST(PATH)
    Call<Formacao> post(
            @Body Formacao formacao
    );

    @PUT(PATH)
    Call<Formacao> put(
            @Query("ID_USUARIO") String idUsuario,
            @Query("ID_ESPECIALIDADE") int idEspecialide,
            @Query("ID_FORMACAO") int idFormacao
    );

    @DELETE(PATH + "{id}")
    Call<Formacao> delete(
            @Path("id") String id

    );
}
