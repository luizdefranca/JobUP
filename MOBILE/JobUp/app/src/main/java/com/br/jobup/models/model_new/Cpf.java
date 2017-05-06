
package com.br.jobup.models.model_new;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * Created by Luiz Carlos Ramos on 06/05/17 16:14
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 06/05/17 19:11
 */

public class Cpf implements Serializable
{

    @SerializedName("NR")
    @Expose
    public String nR;
    private final static long serialVersionUID = -360608712185976437L;

}
