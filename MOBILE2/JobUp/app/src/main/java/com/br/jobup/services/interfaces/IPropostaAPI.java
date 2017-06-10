package com.br.jobup.services.interfaces;

/*
 * Created by Luiz Carlos Ramos on 28/05/17 19:59
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 28/05/17 19:59
 */

import com.br.jobup.models.servico.Servico;

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
 * Created by luizramos on 28/05/17.
 */

public interface IPropostaAPI {
    public static final String PATH = "proposta/";

    @GET(PATH)
    Call<List<Servico>> getServico(
            @Query("idUsuario") String idUsuario
    );

    @GET(PATH)
    Call<Servico> getServicoPorServico(
            @Query("idServico") String idServico
    );

    @POST(PATH)
    Call<Servico> post(
            @Body Servico servico
    );

    @PUT(PATH+"{id}")
    Call<Servico> put(
            @Path("id") String id,
            @Body Servico Servico
    );

    @DELETE(PATH+"{id}")
    Call<Servico> delete(
            @Path("id") String id

    );


}
