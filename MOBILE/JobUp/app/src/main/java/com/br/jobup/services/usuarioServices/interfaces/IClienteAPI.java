package com.br.jobup.services.usuarioServices.interfaces;

/*
 * Created by Luiz Carlos Ramos on 01/05/17 00:12 at $today.hour24:12:39.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 01/05/17 00:12
 */

import com.br.jobup.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by luizramos on 01/05/17.
 */

public interface IClienteAPI {
        public static final String PATH = "usuario/";


        @GET(PATH+"{id}")
        Call<List<Usuario>> getAllUsuarios(
                @Path("id") String idCliente

        );

}
