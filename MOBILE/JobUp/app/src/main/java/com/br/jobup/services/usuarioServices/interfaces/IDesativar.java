package com.br.jobup.services.usuarioServices.interfaces;

/*
 * Created by Luiz Carlos Ramos on 01/05/17 00:25 at $today.hour24:25:31.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 01/05/17 00:25
 */

import com.br.jobup.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by luizramos on 01/05/17.
 */

public interface IDesativar {
    public static final String PATH = "desativar/";

    @GET(PATH + "{id}")
    Call<List<Usuario>> get(
            @Path("ID_USUARIO") String idCliente

    );

}