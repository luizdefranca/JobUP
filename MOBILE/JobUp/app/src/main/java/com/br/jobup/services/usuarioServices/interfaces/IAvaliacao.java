package com.br.jobup.services.usuarioServices.interfaces;

/*
 * Created by Luiz Carlos Ramos on 30/04/17 22:53 at $today.hour24:53:50.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/04/17 22:53
 */

import com.br.jobup.models.Avaliacoes;
import com.br.jobup.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static android.R.attr.id;

/**
 * Created by luizramos on 30/04/17.
 */

public interface IAvaliacao {
    public static final String PATH = "avaliacao/";


    @GET(PATH)
    Call<List<Avaliacoes>> getAll(
            @Query("ID_USUARIO") String idUsuario,
            @Query("ID_ESPECIALIDADE") int idEspecialidade
    );

    @GET(PATH)
    Call<List<Avaliacoes>> get(
            @Query("ID_USUARIO") String idUsuario,
            @Query("ID_ESPECIALIDADE") int idEspecialidade,
            @Query("ID_CLIENTE") int idCliente
    );

    @POST(PATH)
    Call<Avaliacoes> post(
            @Body Usuario avaliacao
    );

    @PUT(PATH+"{id}")
    Call<Avaliacoes> put(
            @Query("ID_USUARIO") String idUsuario,
            @Query("ID_ESPECIALIDADE") int idEspecialidade,
            @Query("ID_CLIENTE") String idCliente
    );

    @DELETE(PATH+"{id}")
    Call<Avaliacoes> delete(
            @Query("ID_USUARIO") String idUsuario,
            @Query("ID_ESPECIALIDADE") int idEspecialidade,
            @Query("ID_CLIENTE") String idCliente

    );
}
