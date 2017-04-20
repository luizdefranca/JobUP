package com.br.jobup.models;


/**
 * Created by luizramos on 09/04/17.
 */

public class Rg {


    public int uf;
    public String nr;

    public Rg() {
    }

    public Rg(int uf, String nr) {
        this.uf = uf;
        this.nr = nr;
    }

    public int getUf() {
        return uf;
    }

    public void setUf(int uf) {
        this.uf = uf;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }
}
