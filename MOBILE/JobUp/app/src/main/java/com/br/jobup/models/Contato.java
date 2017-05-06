package com.br.jobup.models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static com.br.jobup.R.id.email;

/**
 * Created by luizramos on 09/04/17.
 */

public class Contato implements Serializable {

    public static final String TAG = Contato.class.getName();

    public Contato(String idUsuario, Telefone fixo, Telefone celular, Email email) {
        this.idUsuario = idUsuario;
        this.fixo = fixo;
        this.celular = celular;
        this.email = email;
    }
    @SerializedName("ID_USUARIO")
    private String idUsuario;
    @SerializedName("Fixo")
    private Telefone fixo;
    @SerializedName("Celular")
    private Telefone celular;
    @SerializedName("Email")
    private Email email;

    public Contato() {
    }



    public Telefone getFixo() {
        return fixo;
    }

    public void setFixo(Telefone fixo) {
        this.fixo = fixo;
    }

    public Telefone getCelular() {
        return celular;
    }

    public void setCelular(Telefone celular) {
        this.celular = celular;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
