package com.br.jobup.models.usuario;

import com.br.jobup.util.Parsers;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;


/*
 * Created by Luiz Carlos Ramos on 12/05/17 02:52
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/04/17 05:56
 */

/**
 * Created by luizramos on 09/04/17.
 */

public class Formacao  implements Serializable {

    @SerializedName("ID_USUARIO")
    @Expose
    private String idUsuario;

    @SerializedName("ID_ESPECIALIDADE")
    @Expose
    private int idEspecialidade;

    @SerializedName("ID_FORMACAO")
    @Expose
    private int idFormacao;

    @SerializedName("INSTITUICAO")
    @Expose
    private String instituicao;

    @SerializedName("NOME_CURSO")
    @Expose
    private String nomeCurso;

    @SerializedName("ANO_FORMACAO")
    @Expose
    private int anoFormacao;

    @SerializedName("DT_APROVACAO")
    @Expose
    private Date dataAprovacao;

    @SerializedName("APROVADO")
    @Expose
    private boolean aprovado;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public int getIdFormacao() {
        return idFormacao;
    }

    public void setIdFormacao(int idFormacao) {
        this.idFormacao = idFormacao;
    }

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

