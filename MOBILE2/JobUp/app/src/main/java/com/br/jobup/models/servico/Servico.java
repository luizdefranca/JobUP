package com.br.jobup.models.servico;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/*
 * Created by Luiz Carlos Ramos on 22/05/17 02:53
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 22/05/17 02:41
 */

/**
 * Created by luizramos on 04/05/17.
 */

public class Servico implements Serializable
{

    @SerializedName("ID_SERVICO")
    @Expose
    public String idServico;

    @SerializedName("ID_USUARIO")
    @Expose
    public String idUsuario;

    @SerializedName("ID_ESPECIALIDADE")
    @Expose
    public Integer idEspecialidade;

    @SerializedName("ID_SUB_ESPECIALIDADE")
    @Expose
    public Integer idSubespecialidade;

    @SerializedName("DT_CADASTRO")
    @Expose
    public Date dtCadastro;

    @SerializedName("PUBLICO")
    @Expose
    public boolean publico;

    @SerializedName("DS_TITULO")
    @Expose
    public String dsTitulo;

    @SerializedName("DS_OBSERVACOES")
    @Expose
    public String dsObservacoes;

    @SerializedName("VL_SUGERIDO")
    @Expose
    public TempServico vlSugerido;


    @SerializedName("TEMPO_SERVICO")
    @Expose
    public Integer tempServico;

    public Servico(){}

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

    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public Integer getIdSubespecialidade() {
        return idSubespecialidade;
    }

    public void setIdSubespecialidade(Integer idSubespecialidade) {
        this.idSubespecialidade = idSubespecialidade;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
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

    public TempServico getVlSugerido() {
        return vlSugerido;
    }

    public void setVlSugerido(TempServico vlSugerido) {
        this.vlSugerido = vlSugerido;
    }

    public Integer getTempServico() {
        return tempServico;
    }

    public void setTempServico(Integer tempServico) {
        this.tempServico = tempServico;
    }
}