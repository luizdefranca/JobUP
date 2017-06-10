package com.br.jobup.services.interfaces;

/*
 * Created by Luiz Carlos Ramos on 28/05/17 19:45
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 28/05/17 19:45
 */

import com.br.jobup.models.servico.Oferta;

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
 * Created by luizramos on 28/05/17.
 */

public interface IOfertaAPI {
    public static final String PATH = "oferta/";

    @GET(PATH)
    Call<List<Oferta>> getOfertas(
            @Query("idUsuario") String idUsuario
    );

    @GET(PATH)
    Call<Oferta> getOfertaPorServico(
            @Query("idServico") String idServico
    );

    @POST(PATH)
    Call<Void> post(
            @Body Oferta oferta
    );

    @PUT(PATH+"{id}")
    Call<Void> put(
            @Path("id") String id,
            @Body Oferta Oferta
    );

    @DELETE(PATH+"{id}")
    Call<Void> delete(
            @Path("id") String id

    );
    
}
