package com.br.jobup.services.interfaces;

/*
 * Created by Luiz Carlos Ramos on 08/05/17 20:41
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 08/05/17 20:41
 */

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by luizramos on 08/05/17.
 */

public interface IUsuarioSignUpAPI {
    public static final String PATH = "login/";


    @GET(PATH)
    Call<String> get(
            @Query("Login") String userName,
            @Query("Email") String email,
            @Query("Password") String password

    );
}
