package com.br.jobup.services.interfaces;

/*
 * Created by Luiz Carlos Ramos on 28/05/17 21:06
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 28/05/17 21:06
 */


import com.br.jobup.models.servico.ServicoOfertaPrivada;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by luizramos on 28/05/17.
 */

public interface IServicoPrivadoAPI {
    public static final String PATH = "ServicoPrivado/";


    @GET(PATH)
    Call<List<ServicoOfertaPrivada>> getAllPorUsuarioProfissional(
            @Query("idUsuarioProfissional") String idUsuarioProfissional
    );

    @POST(PATH)
    Call<Void> post(
            @Body ServicoOfertaPrivada servicoOferta
    );

}
