package com.br.jobup.services.usuarioFullServices.parsers;

/*
 * Created by Luiz Carlos Ramos on 05/05/17 16:53
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 05/05/17 16:53
 */

import com.br.jobup.models.Login;
import com.br.jobup.services.RetroFitInicializador;

import retrofit2.Call;

/**
 * Created by luizramos on 05/05/17.
 */

public class ParserLogin {

    public static final String TAG = "LCFR / " + ParserLogin.class.getSimpleName();

    private final Login login;
    public ParserLogin(Login login){
        this.login = login;
    }
    public Call<com.squareup.okhttp.Response> get(){
         Call<com.squareup.okhttp.Response> loginResponse = null;
            loginResponse = new RetroFitInicializador()
                    .loginAPI()
                    .get(login.getUserName(), login.getPassword());



        return loginResponse;

    }
}
