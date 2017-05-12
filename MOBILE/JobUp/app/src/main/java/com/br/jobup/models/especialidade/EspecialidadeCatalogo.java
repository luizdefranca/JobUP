package com.br.jobup.models.especialidade;

/*
 * Created by Luiz Carlos Ramos on 12/05/17 02:56
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 12/05/17 02:56
 */

/**
 * Created by luizramos on 12/05/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class EspecialidadeCatalogo implements Serializable {

    @SerializedName("ID_USUARIO")
    @Expose
    public String idUsuario;
    @SerializedName("NOME")
    @Expose
    public String nome;
    @SerializedName("DT_NASCTO")
    @Expose
    public Date dtNascimento;
    @SerializedName("ID_ESPECIALIDADE")
    @Expose
    public int idEspecialidade;
    @SerializedName("DESC_ESPECIALIDADE")
    @Expose
    public String descEspecialidade;
    @SerializedName("RESUMO_CURRICULO")
    @Expose
    public String resumoCurriculo;


    public EspecialidadeCatalogo(String idUsuario, String nome, Date dtNascimento,
                                 int idEspecialidade, String descEspecialidade,
                                 String resumoCurriculo) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.idEspecialidade = idEspecialidade;
        this.descEspecialidade = descEspecialidade;
        this.resumoCurriculo = resumoCurriculo;
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

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public int getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getDescEspecialidade() {
        return descEspecialidade;
    }

    public void setDescEspecialidade(String descEspecialidade) {
        this.descEspecialidade = descEspecialidade;
    }

    public String getResumoCurriculo() {
        return resumoCurriculo;
    }

    public void setResumoCurriculo(String resumoCurriculo) {
        this.resumoCurriculo = resumoCurriculo;
    }
}