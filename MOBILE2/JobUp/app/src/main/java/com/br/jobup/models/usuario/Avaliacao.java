package com.br.jobup.models.usuario;

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

public class Avaliacao implements Serializable {

    @SerializedName("ID_USUARIO")
    @Expose
    private String idUsuario;

    @SerializedName("ID_ESPECIALIDADE")
    @Expose
    private int idEspecialidade;

    @SerializedName("ID_CLIENTE")
    @Expose
    private String idCliente;

    @SerializedName("DT_ULT_AVALIACAO")
    @Expose
    private Date dataUltimaAvaliacao;

    @SerializedName("NOTA")
    @Expose
    private int nota;

    @SerializedName("COMENTARIO")
    @Expose
    private String comentario;

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

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Avaliacao() {
    }

    public Avaliacao(Date dataUltimaAvaliacao, int nota, String comentario) {
        this.dataUltimaAvaliacao = dataUltimaAvaliacao;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Date getDataUltimaAvaliacao() {
        return dataUltimaAvaliacao;
    }

    public void setDataUltimaAvaliacao(Date dataUltimaAvaliacao) {
        this.dataUltimaAvaliacao = dataUltimaAvaliacao;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
