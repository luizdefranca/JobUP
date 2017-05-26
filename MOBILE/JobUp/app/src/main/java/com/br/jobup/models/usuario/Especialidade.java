package com.br.jobup.models.usuario;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
 * Created by Luiz Carlos Ramos on 12/05/17 02:52
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 05/05/17 08:56
 */

/**
 * Created by luizramos on 09/04/17.
 */

public class Especialidade implements Serializable {

    @SerializedName("ID_ESPECIALIDADE")
    @Expose
    private int idEspecialidade;

    @SerializedName("DESCRICAO")
    @Expose
    private String descricao;

    @SerializedName("EXIGIR_COMPROVACAO")
    @Expose
    private boolean exigirComprovacao;



    public int getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }


    public Especialidade() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isExigirComprovacao() {
        return exigirComprovacao;
    }

    public void setExigirComprovacao(boolean exigirComprovacao) {
        this.exigirComprovacao = exigirComprovacao;
    }

    public Especialidade(String descricao, boolean exigirComprovacao) {
        this.descricao = descricao;
        this.exigirComprovacao = exigirComprovacao;
    }
}
