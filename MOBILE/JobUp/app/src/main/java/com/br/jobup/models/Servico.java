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

}