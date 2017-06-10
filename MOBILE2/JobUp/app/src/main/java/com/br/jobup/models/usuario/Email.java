package com.br.jobup.models.usuario;

/*
 * Created by Luiz Carlos Ramos on 22/05/17 00:30
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 22/05/17 00:30
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by luizramos on 22/05/17.
 */

public class Email {

    @SerializedName("EMail")
    @Expose
    private String email;

    public Email(){

    }

    public Email(String email){
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
