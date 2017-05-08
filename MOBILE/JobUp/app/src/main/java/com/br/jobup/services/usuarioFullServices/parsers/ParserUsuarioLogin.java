package com.br.jobup.services.usuarioFullServices.parsers;

/*
 * Created by Luiz Carlos Ramos on 05/05/17 16:53
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 05/05/17 16:53
 */

import com.br.jobup.models.UsuarioLogin;
import com.br.jobup.services.RetroFitInicializador;

import retrofit2.Call;

/**
 * Created by luizramos on 05/05/17.
 */

public class ParserUsuarioLogin {

    public static final String TAG = "LCFR / " + ParserUsuarioLogin.class.getSimpleName();

    private final UsuarioLogin usuarioLogin;
    public ParserUsuarioLogin(UsuarioLogin usuarioLogin){
        this.usuarioLogin = usuarioLogin;
    }
    public Call<String> get(){
         Call<String> loginResponse = null;
            loginResponse = new RetroFitInicializador()
                    .loginAPI()
                    .get(usuarioLogin.getUserName(), usuarioLogin.getPassword());



        return loginResponse;

    }
}
