package com.br.jobup.models.servico;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
 * Created by Luiz Carlos Ramos on 22/05/17 02:54
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 22/05/17 02:54
 */

/**
 * Created by luizramos on 22/05/17.
 */

public class Oferta implements Serializable {

    @SerializedName("ID_SERVICO")
    @Expose
    public String idServico;

    @SerializedName("ID_USUARIO")
    @Expose
    public String idUsuario;

    @SerializedName("ACEITA")
    @Expose
    private Boolean aceita;

    @SerializedName("DS_TITULO")
    @Expose
    public String dsTitulo;

    @SerializedName("DS_OBSERVACOES")
    @Expose
    public String dsObservacoes;

    public Oferta(){}

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
