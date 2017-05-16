package com.br.jobup.services.interfaces;

import com.br.jobup.models.usuario.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/*
 * Created by Luiz Carlos Ramos on 30/04/17 22:47 at $today.hour24:47:31.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/04/17 20:18
 */

/**
 * Created by luizramos on 27/04/17.
 */

public interface IUsuarioAPI {
    public static final String PATH = "usuario/";


    @GET(PATH)
    Call<List<Usuario>> getAll(
    );

    @GET(PATH)
    Call<Usuario> get(
            @Query("id") String id
    );

    @POST(PATH)
    Call<Usuario> post(
            @Body Usuario usuario
    );

    @PUT(PATH+"{id}")
    Call<Usuario> put(
            @Path("id") String id,
            @Body Usuario usuario
    );

    @DELETE(PATH+"{id}")
    Call<Usuario> delete(
      @Path("id") String id

    );
}
