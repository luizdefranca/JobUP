package com.br.jobup.services.interfaces;

/*
 * Created by Luiz Carlos Ramos on 05/05/17 16:44
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 05/05/17 16:44
 */

import android.support.annotation.CallSuper;

import com.br.jobup.models.Login;
import com.br.jobup.models.Usuario;
import com.squareup.okhttp.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.br.jobup.R.id.login;

/**
 * Created by luizramos on 05/05/17.
 */

public interface ILoginAPI {
    public static final String PATH = "login/";


    @GET(PATH)
    Call<Response> get(
            @Query("Email") String email,
            @Query("Password") String password
    );


}
