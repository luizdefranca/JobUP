package com.br.jobup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * Created by Luiz Carlos Ramos on 07/05/17 17:01
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 07/05/17 17:01
 */

public class SignUpActivity extends AppCompatActivity {

    /* Views */
    EditText usernameTxt;
    EditText emailTxt;
    EditText passwordTxt;
    EditText reWritePasswordTxt;
    ProgressDialog progDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Set Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        // Set Title on the ActionBar
        getSupportActionBar().setTitle("Cadastro de Usuario");

        // Init views
        usernameTxt = (EditText)findViewById(R.id.usernameTxt2);
        emailTxt = (EditText)findViewById(R.id.emailTxt2);
        passwordTxt = (EditText)findViewById(R.id.passwordTxt2);
        reWritePasswordTxt = (EditText)findViewById(R.id.rewritePasswordTxt);

        Button signupButt = (Button)findViewById(R.id.signUpButt2);
        signupButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usernameTxt.getText().toString().matches("") ||
                        emailTxt.getText().toString().matches("") ||
                        passwordTxt.getText().toString().matches("") ||
                        reWritePasswordTxt.getText().toString().matches("")) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(SignUpActivity.this);
                    alert.setMessage("Você deve preencher todos os campos para se cadastrar!")
                            .setTitle(R.string.app_name)
                            .setPositiveButton("OK", null)
                            .setIcon(R.drawable.logojobup);
                    alert.create().show();


                } else {
                    progDialog.show();
                    dismisskeyboard();

                    //TODO FAZER CHAMADA DA API PARA CADASTRAR O USUARIO


                }
            }});


    }


    // DISMISS KEYBOARD
    public void dismisskeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(usernameTxt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(emailTxt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(passwordTxt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(reWritePasswordTxt.getWindowToken(), 0);
    }

}
