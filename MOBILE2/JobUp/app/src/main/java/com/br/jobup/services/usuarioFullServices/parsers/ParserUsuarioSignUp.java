package com.br.jobup.services.usuarioFullServices.parsers;

/*
 * Created by Luiz Carlos Ramos on 08/05/17 20:43
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 08/05/17 20:43
 */

import com.br.jobup.models.login.UsuarioSignUp;
import com.br.jobup.services.RetroFitInicializador;

import retrofit2.Call;

/**
 * Created by luizramos on 08/05/17.
 */

public class ParserUsuarioSignUp {

    public static final String TAG = "LCFR / " + ParserUsuarioSignUp.class.getSimpleName();

    private final UsuarioSignUp usuarioSignUp;
    public ParserUsuarioSignUp(UsuarioSignUp usuarioSignUp){
        this.usuarioSignUp = usuarioSignUp;
    }
    public Call<String> get(){
        Call<String> loginResponse = null;
        loginResponse = new RetroFitInicializador()
                .signUpAPI()
                .get(usuarioSignUp.login,usuarioSignUp.email, usuarioSignUp.password);
        return loginResponse;

    }
}
