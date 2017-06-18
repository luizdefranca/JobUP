package com.br.jobup.services.interfaces;

/*
 * Created by Luiz Carlos Ramos on 6/15/17 11:28 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/15/17 11:28 AM
 */

import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.models.usuario.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by luizramos on 15/06/17.
 */

public interface IServicoPrivadoClienteSemPropostaAPI {

    public static final String PATH = "ServicoPrivadoClienteSemProposta/";


    @GET(PATH)
    Call<List<ServicoOfertaPrivada>> getAll(
            @Query("idUsuarioCliente") String idUsuarioCliente
    );
}
