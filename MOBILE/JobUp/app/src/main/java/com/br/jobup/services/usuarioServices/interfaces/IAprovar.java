package com.br.jobup.services.usuarioServices.interfaces;

/*
 * Created by Luiz Carlos Ramos on 30/04/17 22:52 at $today.hour24:52:26.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/04/17 22:52
 */

import com.br.jobup.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by luizramos on 30/04/17.
 */

public interface IAprovar {
    public static final String PATH = "aprovar/";
    @GET(PATH)
    Call<Usuario> get(
            @Query("id") String id
    );

}
