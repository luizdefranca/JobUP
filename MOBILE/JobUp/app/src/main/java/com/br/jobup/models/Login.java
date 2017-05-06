package com.br.jobup.models;

/*
 * Created by Luiz Carlos Ramos on 05/05/17 16:51
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 05/05/17 16:51
 */

import java.io.Serializable;

/**
 * Created by luizramos on 05/05/17.
 */

public class Login implements Serializable {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login(String email, String password) {

        this.email = email;
        this.password = password;
    }
}
