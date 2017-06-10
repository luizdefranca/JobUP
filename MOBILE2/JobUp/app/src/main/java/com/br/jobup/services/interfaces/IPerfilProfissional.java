package com.br.jobup.services.interfaces;

/*
 * Created by Luiz Carlos Ramos on 27/05/17 01:25 
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 27/05/17 01:25
 */


import com.br.jobup.models.usuario.PerfilProfissional;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by luizramos on 27/05/17.
 */

public interface IPerfilProfissional {

    public static final String PATH = "perfilprofissional/";


    @GET(PATH)
    Call<List<PerfilProfissional>> getAllPorIdEspecialidade(
            @Query("idEspecialidade") int idEspecialidade
    );

    @GET(PATH)
    Call<List<PerfilProfissional>> getPorIdUsuario(
            @Query("idUsuario") String idUsuario
    );

    @GET(PATH)
    Call<PerfilProfissional> getPorIdUsuarioEIdEspecialidade(
            @Query("idUsuario") String idUsuario,
            @Query("idEspecialidade") int idEspecialidade
    );

    @POST(PATH)
    Call<Void> post(
            @Body PerfilProfissional perfilProfissional
    );

    @PUT(PATH)
    Call<Void> put(
            @Query("idUsuario") String idUsuario,
            @Query("idEspecialidade") int idEspecialidade,
            @Body PerfilProfissional PerfilProfissional
    );


    @DELETE(PATH)
    Call<Void> delete(
            @Query("idUsuario") String idUsuario,
            @Query("idEspecialidade") int idEspecialidade

    );
}
