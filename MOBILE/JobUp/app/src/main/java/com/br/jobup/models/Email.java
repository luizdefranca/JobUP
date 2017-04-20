package com.br.jobup.models;


/**
 * Created by luizramos on 09/04/17.
 */

public class Email {


    public static final int EnderecoMaxLength = 254;
    public String email;

    public Email() {
    }

    public Email(String email) {
        this.email = email;
    }

    public static int getEnderecoMaxLength() {
        return EnderecoMaxLength;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
