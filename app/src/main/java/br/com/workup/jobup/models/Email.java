package br.com.workup.jobup.models;


/**
 * Created by luizramos on 09/04/17.
 */

public class Email {


    public static final int EnderecoMaxLength = 254;
    public String email;

    public Email() {
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
