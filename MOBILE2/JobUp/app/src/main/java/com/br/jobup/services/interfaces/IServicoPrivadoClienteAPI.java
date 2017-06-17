package com.br.jobup.services.interfaces;

import com.br.jobup.models.servico.ServicoOfertaPrivada;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * Created by Luiz Carlos Ramos on 6/16/17 12:12 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/15/17 11:10 PM
 */

/**
 * Created by luizramos on 15/06/17.
 */

public interface IServicoPrivadoClienteAPI {

    public static final String PATH = "ServicoPrivadoClienteAdapter/";


    @GET(PATH)
    Call<List<ServicoOfertaPrivada>> getAll(
            @Query("idUsuarioCliente") String idUsuarioCliente
    );
}
