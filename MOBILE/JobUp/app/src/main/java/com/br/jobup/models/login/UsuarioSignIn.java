package com.br.jobup.models.login;

import java.io.Serializable;

/*
 * Created by Luiz Carlos Ramos on 12/05/17 02:53
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 08/05/17 19:51
 */

/**
 * Created by luizramos on 05/05/17.
 */

public class UsuarioSignIn implements Serializable {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsuarioSignIn(String email, String password) {

        this.userName = email;
        this.password = password;
    }
}
