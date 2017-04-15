package br.com.workup.jobup.models;


/**
 * Created by luizramos on 09/04/17.
 */

public class Cpf  {

    private String nr;
    public static final int MAX_LENGTH = 11;

    public Cpf() {
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }
}
