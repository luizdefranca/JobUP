package com.br.jobup.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by luizramos on 09/04/17.
 */

public class Endereco implements Serializable {

    public static final String TAG = Endereco.class.getName();
    public static final int MAX_LENGTH = 256;

    public Endereco(String idUsuario, int uf, String cep, String logradouro, String complemento, String bairro, String cidade) {
        this.idUsuario = idUsuario;
        Uf = uf;
        Cep = cep;
        this.logradouro = logradouro;
        Complemento = complemento;
        Bairro = bairro;
        Cidade = cidade;
    }

    private String idUsuario;

    @SerializedName("UF")
    private int Uf;
    @SerializedName("CEP")
    private String Cep;
    @SerializedName("LOGRADOURO")
    private String logradouro;
    @SerializedName("COMPLEMENTO")
    private String Complemento;
    @SerializedName("BAIRRO")
    private String Bairro;
    @SerializedName("CIDADE")
    private String Cidade;

    public Endereco() {


    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }


    public int getUf() {
        return Uf;
    }

    public void setUf(int uf) {
        Uf = uf;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String cep) {
        Cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String complemento) {
        Complemento = complemento;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }
}