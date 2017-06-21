package com.br.jobup.services.interfaces;

import com.br.jobup.models.servico.ServicoOfertaPrivada;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * Created by Luiz Carlos Ramos on 6/21/17 5:06 PM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/17/17 11:32 PM
 */

/**
 * Created by luizramos on 15/06/17.
 */

public interface IServicoPrivadoClienteAPI2 {

    public static final String PATH = "ServicoPrivadoCliente/";


    @GET(PATH)
    Call<List<ServicoOfertaPrivada>> getAll(
            @Query("idUsuarioCliente") String idUsuarioCliente
    );
}
