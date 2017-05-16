package com.br.jobup.models.model_new;

import com.br.jobup.util.Parsers;

import java.io.Serializable;
import java.util.Date;


/*
 * Created by Luiz Carlos Ramos on 06/05/17 16:22
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/04/17 05:56
 */

/**
 * Created by luizramos on 09/04/17.
 */

public class Formacao  implements Serializable {
    private String instituicao;
    private String nomeCurso;
    private int anoFormacao;
    private Date dataAprovacao;
    private boolean aprovado;

    public Formacao() {
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getAnoFormacao() {
        return anoFormacao;
    }

    public void setAnoFormacao(int anoFormacao) {
        this.anoFormacao = anoFormacao;
    }

    public Date getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(Date dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }
    public void setDataAprovacao(String dataAprovacao) {
        this.dataAprovacao = Parsers.parseStringToDataAPI(dataAprovacao);
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }
}

