
package com.br.jobup.models.model_new;

import java.io.Serializable;
import java.util.List;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * Created by Luiz Carlos Ramos on 06/05/17 16:14
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 06/05/17 19:11
 */

public class Usuario implements Serializable
{

    @SerializedName("ID_USUARIO")
    @Expose
    public String idUsuario;
    @SerializedName("NOME")
    @Expose
    public String nome;
    @SerializedName("Cpf")
    @Expose
    public Cpf cpf;
    @SerializedName("Rg")
    @Expose
    public Rg rg;
    @SerializedName("DT_NASCIMENTO")
    @Expose
    public String dtNascimento;
    @SerializedName("DT_INCLUSAO")
    @Expose
    public String dtInclusao;
    @SerializedName("DT_ALTERACAO")
    @Expose
    public String dtAlteracao;
    @SerializedName("DT_APROVACAO")
    @Expose
    public String dtAtivacao;
    @SerializedName("DT_ATIVACAO")
    @Expose
    public String dtOrdenacao;
    @SerializedName("DT_ORDENACAO")
    @Expose
    public String dtAprovacao;
    @SerializedName("APROVADO")
    @Expose
    public Boolean aprovado;
    @SerializedName("ATIVO")
    @Expose
    public Boolean ativo;
    @SerializedName("BLOQUEADO")
    @Expose
    public Boolean bloqueado;
    @SerializedName("UF")
    @Expose
    public int uf;
    @SerializedName("CEP")
    @Expose
    public String cep;
    @SerializedName("LOGRADOURO")
    @Expose
    public String logradouro;
    @SerializedName("COMPLEMENTO")
    @Expose
    public String complemento;
    @SerializedName("BAIRRO")
    @Expose
    public String bairro;
    @SerializedName("CIDADE")
    @Expose
    public String cidade;
    @SerializedName("Fixo")
    @Expose
    public Telefone fixo;
    @SerializedName("Celular")
    @Expose
    public Telefone celular;
    @SerializedName("Email")
    @Expose
    public Email email;
    @SerializedName("PERFIS_PROFISSIONAIS")
    @Expose
    public List<PerfilProfisional> perfisProfissionais = null;
    @SerializedName("OFERTAS_SERVICO")
    @Expose
    public List<Object> ofertaServico;
    @SerializedName("PROPOSTAS_SERVICO")
    @Expose
    public List<Object> propostaServico;

}
