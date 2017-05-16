package com.br.jobup.services.interfaces;

/*
 * Created by Luiz Carlos Ramos on 05/05/17 16:44
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 05/05/17 16:44
 */

import com.br.jobup.models.usuario.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by luizramos on 05/05/17.
 */

public interface IUsuarioSignInAPI {
    public static final String PATH = "login";


    @GET(PATH)
    Call<Usuario> get(
            @Query("Email") String email,
            @Query("Password") String password
    );


}
