package com.br.jobup.services.interfaces;

/*
 * Created by Luiz Carlos Ramos on 30/04/17 22:52 at $today.hour24:52:26.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/04/17 22:52
 */

import com.br.jobup.models.usuario.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by luizramos on 30/04/17.
 */

public interface IAprovarAPI {
    public static final String PATH = "aprovar/";
    @GET(PATH)
    Call<Usuario> aprovar(
            @Query("id") String id
    );

}
