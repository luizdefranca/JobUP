package com.br.jobup.services.usuarioServices.interfaces;

/*
 * Created by Luiz Carlos Ramos on 30/04/17 22:48 at $today.hour24:48:37.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/04/17 22:48
 */

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

import static com.br.jobup.services.usuarioServices.interfaces.IUsuarioService.PATH;

/**
 * Created by luizramos on 30/04/17.
 */

public interface IUsuarioFullService {
    public static final String PATH = "usuariofull/";


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
