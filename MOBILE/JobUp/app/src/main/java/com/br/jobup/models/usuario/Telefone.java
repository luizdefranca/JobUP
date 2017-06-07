package com.br.jobup.models.usuario;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
 * Created by Luiz Carlos Ramos on 12/05/17 02:52
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/04/17 05:56
 */

/**
 * Created by luizramos on 09/04/17.
 */

public class Telefone implements Serializable{
    public static final String TAG = Telefone.class.getSimpleName();

    @SerializedName("NrTelefone")
    private String nr;

    public Telefone() {
    }

    public Telefone(String nr) {
        this.nr = nr;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }
}
