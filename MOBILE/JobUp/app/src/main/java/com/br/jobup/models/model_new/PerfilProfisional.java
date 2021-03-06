package com.br.jobup.models.model_new;


import com.br.jobup.util.Parsers;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/*
 * Created by Luiz Carlos Ramos on 06/05/17 16:22
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 03/05/17 18:11
 */

/**
 * Created by luizramos on 09/04/17.
 */

public class PerfilProfisional implements Serializable {

    private Date dataAprovacao;
    private boolean aprovado;
    private String resumoCurriculo;
    private List<Especialidade> especialidade;
    private List<Avaliacoes> avaliacoes;
    private List<Formacao> formacoes;

    public PerfilProfisional() {
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

    public String getResumoCurriculo() {
        return resumoCurriculo;
    }

    public void setResumoCurriculo(String resumoCurriculo) {
        this.resumoCurriculo = resumoCurriculo;
    }

    public List<Especialidade> getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(List<Especialidade> especialidade) {
        this.especialidade = especialidade;
    }

    public List<Avaliacoes> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacoes> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Formacao> getFormacoes() {
        return formacoes;
    }

    public void setFormacoes(List<Formacao> formacoes) {
        this.formacoes = formacoes;
    }
}
