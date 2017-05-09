package com.br.jobup.models;

/*
 * Created by Luiz Carlos Ramos on 04/05/17 19:24
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 04/05/17 19:24
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

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
    @SerializedName("NOME")
    @Expose
    public String nome;
    @SerializedName("ID_ESPECIALIDADE")
    @Expose
    public Integer idEspecialidade;
    @SerializedName("DESC_ESPECIALIDADE")
    @Expose
    public String descEspecialidade;
    @SerializedName("ID_SUB_ESPECIALIDADE")
    @Expose
    public Integer idSubespecialidade;
    @SerializedName("DESC_SUB_ESPECIALIDADE")
    @Expose
    public String descSubEspecialidade;
    @SerializedName("DT_CADASTRO")
    @Expose
    public Date dtCadastro;
    @SerializedName("DS_TITULO")
    @Expose
    public String dsTitulo;
    @SerializedName("DS_OBSERVACOES")
    @Expose
    public String dsObservacoes;
    @SerializedName("VL_SUGERIDO")
    @Expose
    public  TempServico vlSugerido;
    @SerializedName("TEMPO_SERVICO")
    @Expose
    public Integer tempServico;
    @SerializedName("TEMPO_SERVICO_DESC")
    @Expose
    public String descTempServico;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getDescEspecialidade() {
        return descEspecialidade;
    }

    public void setDescEspecialidade(String descEspecialidade) {
        this.descEspecialidade = descEspecialidade;
    }

    public Integer getIdSubespecialidade() {
        return idSubespecialidade;
    }

    public void setIdSubespecialidade(Integer idSubespecialidade) {
        this.idSubespecialidade = idSubespecialidade;
    }

    public String getDescSubEspecialidade() {
        return descSubEspecialidade;
    }

    public void setDescSubEspecialidade(String descSubEspecialidade) {
        this.descSubEspecialidade = descSubEspecialidade;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
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

    public String getDescTempServico() {
        return descTempServico;
    }

    public void setDescTempServico(String descTempServico) {
        this.descTempServico = descTempServico;
    }
}