package com.br.jobup.models;

import io.realm.RealmObject;

/**
 * Created by luizramos on 09/04/17.
 */

public class Rg  extends RealmObject {


    public String uf;
    public String nr;

    public Rg() {
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }
}
