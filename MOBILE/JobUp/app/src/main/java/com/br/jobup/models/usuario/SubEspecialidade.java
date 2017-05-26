package com.br.jobup.models.usuario;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
 * Created by Luiz Carlos Ramos on 12/05/17 02:52
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 04/05/17 18:23
 */

/**
 * Created by luizramos on 03/05/17.
 */

public class SubEspecialidade implements Serializable {


    @SerializedName("ID_SUB_ESPECIALIDADE")
    @Expose
    private int idSubEspecialidade;

    @SerializedName("ID_ESPECIALIDADE")
    @Expose
    private int idEspecialidade;

    @SerializedName("DESCRICAO")
    @Expose
    private String descricao;

    public SubEspecialidade(){}
    public int getIdSubEspecialidade() {
        return idSubEspecialidade;
    }

    public void setIdSubEspecialidade(int idSubEspecialidade) {
        this.idSubEspecialidade = idSubEspecialidade;
    }

    public int getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
