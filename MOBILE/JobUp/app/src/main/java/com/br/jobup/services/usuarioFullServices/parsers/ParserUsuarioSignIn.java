package com.br.jobup.services.usuarioFullServices.parsers;

/*
 * Created by Luiz Carlos Ramos on 05/05/17 16:53
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 05/05/17 16:53
 */

import com.br.jobup.models.Usuario;
import com.br.jobup.models.UsuarioSignIn;
import com.br.jobup.services.RetroFitInicializador;

import retrofit2.Call;

/**
 * Created by luizramos on 05/05/17.
 */

public class ParserUsuarioSignIn {

    public static final String TAG = "LCFR / " + ParserUsuarioSignIn.class.getSimpleName();

    private final UsuarioSignIn usuarioSignIn;
    public ParserUsuarioSignIn(UsuarioSignIn usuarioSignIn){
        this.usuarioSignIn = usuarioSignIn;
    }
    public Call<Usuario> get(){
         Call<Usuario> loginResponse = null;
            loginResponse = new RetroFitInicializador()
                    .loginAPI()
                    .get(usuarioSignIn.getUserName(), usuarioSignIn.getPassword());



        return loginResponse;

    }
}
