package com.br.jobup.models.model_new;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
 * Created by Luiz Carlos Ramos on 06/05/17 16:30
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/04/17 05:56
 */

/**
 * Created by luizramos on 09/04/17.
 */

public class Telefone implements Serializable{
    public static final String TAG = Telefone.class.getSimpleName();

    @SerializedName("NR")
    private String nr;
    public static final int MAX_LENGTH = 12;

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
