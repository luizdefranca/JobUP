package com.br.jobup.services.interfaces;

import com.br.jobup.models.usuario.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * Created by Luiz Carlos Ramos on 6/18/17 1:17 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/10/17 11:59 AM
 */

/**
 * Created by luizramos on 30/04/17.
 */

public interface IRejeitarServicoAPI {
    public static final String PATH = "rejeitarservico/";
    @GET(PATH)
    Call<Void> rejeitarServico(
            @Query("id") String id
    );

}
