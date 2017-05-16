package com.br.jobup.models.usuario;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/*
 * Created by Luiz Carlos Ramos on 12/05/17 02:52
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 07/05/17 17:42
 */

/**
 * Created by luizramos on 09/04/17.
 */

public class Email implements Serializable {


    public static final int EnderecoMaxLength = 254;
    @SerializedName("Email")
    public String email;

    public Email() {
    }

    public Email(String email) {
        this.email = email;
    }

    public static int getEnderecoMaxLength() {
        return EnderecoMaxLength;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
