package com.br.jobup.services.interfaces;

/*
 * Created by Luiz Carlos Ramos on 30/04/17 22:53 at $today.hour24:53:50.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/04/17 22:53
 */

import com.br.jobup.models.usuario.Avaliacao;
import com.br.jobup.models.usuario.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by luizramos on 30/04/17.
 */

public interface IAvaliacaoAPI {
    public static final String PATH = "avaliacao/";


    @GET(PATH)
    Call<List<Avaliacao>> getAll(
            @Query("idUsuario") String idUsuario,
            @Query("idEspecialidade") int idEspecialidade
    );

    @GET(PATH)
    Call<List<Avaliacao>> get(
            @Query("idUsuario") String idUsuario,
            @Query("idEspecialidade") int idEspecialidade,
            @Query("idCliente") int idCliente
    );

    @POST(PATH)
    Call<Avaliacao> post(
            @Body Usuario avaliacao
    );

    @PUT(PATH+"{id}")
    Call<Avaliacao> put(
            @Query("idUsuario") String idUsuario,
            @Query("idEspecialidade") int idEspecialidade,
            @Query("idCliente") String idCliente,
            @Body Avaliacao avaliacao
    );

    @DELETE(PATH+"{id}")
    Call<Avaliacao> delete(
            @Query("idUsuario") String idUsuario,
            @Query("idEspecialidade") int idEspecialidade,
            @Query("idCliente") String idCliente

    );
}
