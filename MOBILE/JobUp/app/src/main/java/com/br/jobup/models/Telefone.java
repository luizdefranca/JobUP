package com.br.jobup.models;


/**
 * Created by luizramos on 09/04/17.
 */

public class Telefone {
    private String nr;
    public static final int MAX_LENGTH = 12;

    public Telefone() {
    }

    public Telefone(String nr) {
        this.nr = nr;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }
}
