package com.br.jobup.services.usuarioServices.interfaces;

/*
 * Created by Luiz Carlos Ramos on 01/05/17 00:17 at $today.hour24:17:29.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 01/05/17 00:17
 */

import com.br.jobup.models.Contato;

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

public interface IContatoAPI {
    public static final String PATH = "contato/";


    @GET(PATH)
    Call<List<Contato>> getAll(
    );

    @GET(PATH)
    Call<Contato> get(
            @Query("id") String idUsuario
    );

    @POST(PATH)
    Call<Contato> post(
            @Body Contato contato
    );

    @PUT(PATH+"{id}")
    Call<Contato> put(
            @Path("id") String idUsuario,
            @Body Contato contato

    );

    @DELETE(PATH+"{id}")
    Call<Contato> delete(
            @Path("id") String idUsuario

    );
}
