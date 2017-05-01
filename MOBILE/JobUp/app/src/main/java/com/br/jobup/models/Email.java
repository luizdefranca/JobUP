package com.br.jobup.models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static com.br.jobup.R.id.email;

/**
 * Created by luizramos on 09/04/17.
 */

public class Email implements Serializable {


    public static final int EnderecoMaxLength = 254;
    @SerializedName("EMAIL")
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
