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

public class Rg implements Serializable{

    @SerializedName("UF")
    public int uf;
    @SerializedName("NR")
    public String nr;

    public Rg() {
    }

    public Rg(int uf, String nr) {
        this.uf = uf;
        this.nr = nr;
    }

    public int getUf() {
        return uf;
    }

    public void setUf(int uf) {
        this.uf = uf;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }
}
