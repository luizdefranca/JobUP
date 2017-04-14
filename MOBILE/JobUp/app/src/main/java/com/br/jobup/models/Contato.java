package com.br.jobup.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by luizramos on 09/04/17.
 */

public class Contato extends RealmObject {

    public static final String TAG = Contato.class.getName();

    @PrimaryKey
    private String idUsuario;
    private Telefone fixo;
    private Telefone celular;
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
