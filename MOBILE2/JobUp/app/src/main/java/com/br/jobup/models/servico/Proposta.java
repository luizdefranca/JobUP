package com.br.jobup.models.servico;

/*
 * Created by Luiz Carlos Ramos on 22/05/17 00:42
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 22/05/17 00:42
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by luizramos on 22/05/17.
 */

public class Proposta implements Serializable {

    @SerializedName("ID_SERVICO")
    @Expose
    private String idServico;

    @SerializedName("ID_USUARIO")
    @Expose
    private String idUsuario;

    @SerializedName("DT_PROPOSTA")
    @Expose
    private String dtProposta;

    @SerializedName("DURACAO_SERVICO")
    @Expose
    private Integer duracaoServico;

    @SerializedName("VALOR_DURACAO_SERVICO")
    @Expose
    private Integer valorDuracaoServico;

    @SerializedName("VL_PROPOSTA")
    @Expose
    private Integer vlProposta;

    @SerializedName("JUSTIFICATIVA")
    @Expose
    private String justificativa;

    @SerializedName("ACEITA")
    @Expose
    private Boolean aceita;

    @SerializedName("DS_TITULO")
    @Expose
    private String dsTitulo;

    @SerializedName("DS_OBSERVACOES")
    @Expose
    private String dsObservacoes;


    public Proposta(){}
    public String getIdServico() {
        return idServico;
    }

    public void setIdServico(String idServico) {
        this.idServico = idServico;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDtProposta() {
        return dtProposta;
    }

    public void setDtProposta(String dtProposta) {
        this.dtProposta = dtProposta;
    }

    public Integer getDuracaoServico() {
        return duracaoServico;
    }

    public void setDuracaoServico(Integer duracaoServico) {
        this.duracaoServico = duracaoServico;
    }

    public Integer getValorDuracaoServico() {
        return valorDuracaoServico;
    }

    public void setValorDuracaoServico(Integer valorDuracaoServico) {
        this.valorDuracaoServico = valorDuracaoServico;
    }

    public Integer getVlProposta() {
        return vlProposta;
    }

    public void setVlProposta(Integer vlProposta) {
        this.vlProposta = vlProposta;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Boolean getAceita() {
        return aceita;
    }

    public void setAceita(Boolean aceita) {
        this.aceita = aceita;
    }

    public String getDsTitulo() {
        return dsTitulo;
    }

    public void setDsTitulo(String dsTitulo) {
        this.dsTitulo = dsTitulo;
    }

    public String getDsObservacoes() {
        return dsObservacoes;
    }

    public void setDsObservacoes(String dsObservacoes) {
        this.dsObservacoes = dsObservacoes;
    }
}
