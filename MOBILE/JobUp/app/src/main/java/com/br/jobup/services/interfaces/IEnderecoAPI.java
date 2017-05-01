package com.br.jobup.services.interfaces;

/*
 * Created by Luiz Carlos Ramos on 01/05/17 00:35 at $today.hour24:35:53.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 01/05/17 00:35
 */

import com.br.jobup.models.Endereco;

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

public interface IEnderecoAPI {
    public static final String PATH = "endereco/";


    

    @GET(PATH)
    Call<Endereco> get(
            @Query("id") String idUsuario
    );

    @POST(PATH)
    Call<Endereco> post(
            @Body Endereco endereco
    );

    @PUT(PATH+"{id}")
    Call<Endereco> put(
            @Path("id") String idUsuario,
            @Body Endereco endereco
    );

    @DELETE(PATH+"{id}")
    Call<Endereco> delete(
            @Path("id") String idUsuario

    );
}
