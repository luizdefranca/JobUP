package com.br.jobup.services.interfaces;

/*
 * Created by Luiz Carlos Ramos on 6/21/17 8:23 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/21/17 8:23 AM
 */

import com.br.jobup.models.servico.Servico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by luizramos on 21/06/17.
 */

public interface IRejeitarPropostaAPI {
    public static final String PATH = "RejeitarProposta/";

    @GET(PATH + "{id}")
    Call<Void> rejeitarProposta(
            @Path("id") String idServico,
            @Query("ID_USUARIO") String idUsuario
    );
}
