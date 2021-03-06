package com.br.jobup.models.usuario;


import java.io.Serializable;
import java.util.List;

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


    private String descricao;

    private boolean exigirComprovacao;

    private List<SubEspecialidade> subEspecialidades;

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
