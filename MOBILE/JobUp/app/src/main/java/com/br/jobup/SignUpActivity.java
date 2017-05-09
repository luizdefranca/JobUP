package com.br.jobup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.jobup.models.UsuarioSignUp;
import com.br.jobup.services.usuarioFullServices.parsers.ParserUsuarioSignUp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        usernameTxt = (EditText) findViewById(R.id.usernameTxt2);
        emailTxt = (EditText) findViewById(R.id.emailTxt2);
        passwordTxt = (EditText) findViewById(R.id.passwordTxt2);
        reWritePasswordTxt = (EditText) findViewById(R.id.rewritePasswordTxt);


        // Init a ProgressDialog
        progDialog = new ProgressDialog(this);
        progDialog.setTitle(R.string.app_name);
        progDialog.setMessage("Efetuando cadastro...");
        progDialog.setIndeterminate(false);


        Button signupButt = (Button) findViewById(R.id.signUpButt2);
        signupButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progDialog.show();
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


                } else if (!passwordTxt.getText().toString().equals(reWritePasswordTxt.getText()
                        .toString())) {
                        reWritePasswordTxt.setError("As senhas têm de serem iguais");
                } else {
                    progDialog.show();
                    dismisskeyboard();

                    //TODO FAZER CHAMADA DA API PARA CADASTRAR O USUARIO

                    String username = usernameTxt.getText().toString();
                    String email = emailTxt.getText().toString();
                    String password = passwordTxt.getText().toString();
                    UsuarioSignUp usuario = new UsuarioSignUp(username, email, password);

                    final ParserUsuarioSignUp parser = new ParserUsuarioSignUp(usuario);
                    parser.get().enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                String idUsuario = response.body().toString();
                                Toast.makeText(SignUpActivity.this, "usário com id=" + idUsuario +
                                        " cadastrado", Toast.LENGTH_SHORT).show();
                                progDialog.dismiss();
                                startActivity(new Intent(SignUpActivity.this, SingInActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(SignUpActivity.this, "Falha ao cadastrar Usuario",
                                    Toast.LENGTH_SHORT).show();
                            progDialog.dismiss();
                        }
                    });


                }
            }
        });


    }


    // DISMISS KEYBOARD
    public void dismisskeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(usernameTxt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(emailTxt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(passwordTxt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(reWritePasswordTxt.getWindowToken(), 0);
    }

}
