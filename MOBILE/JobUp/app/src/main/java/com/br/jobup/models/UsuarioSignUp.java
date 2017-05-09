package com.br.jobup.models;

/*
 * Created by Luiz Carlos Ramos on 08/05/17 20:38
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 08/05/17 20:38
 */

/**
 * Created by luizramos on 08/05/17.
 */

public class UsuarioSignUp {
    public String login;
    public String email;
    public String password;

    public UsuarioSignUp(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }
}
