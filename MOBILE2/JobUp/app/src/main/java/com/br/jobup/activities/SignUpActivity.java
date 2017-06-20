package com.br.jobup.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.jobup.R;
import com.br.jobup.models.login.UsuarioSignUp;
import com.br.jobup.services.usuarioFullServices.parsers.ParserUsuarioSignUp;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
 * Created by Luiz Carlos Ramos on 6/7/17 12:02 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 5/25/17 1:47 PM
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Set Title on the ActionBar
        getSupportActionBar().setTitle("Faça seu cadastro!");

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

                boolean isFormularioValido = true;
                if (!passwordTxt.getText().toString().equals(reWritePasswordTxt.getText()
                        .toString())) {
                    reWritePasswordTxt.setError("As senhas têm de serem iguais");
                    isFormularioValido = false;
                }
                if (passwordTxt.getText().toString().isEmpty()) {
                    passwordTxt.setError("Campo nao pode ser em branco");
                    isFormularioValido = false;
                }
                if (!passwordTxt.getText().toString().matches("^(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$")) {
                    passwordTxt.setError("As senhas devem ter pelo menos oito caracteres, um caracter maiusculo e um número");
                    isFormularioValido = false;
                }
                if (!emailTxt.getText().toString().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") ||
                        emailTxt.getText().toString().matches("")) {
                    emailTxt.setError("Email inválido");
                    isFormularioValido = false;
                }
                if (usernameTxt.getText().toString().length() < 8) {
                    usernameTxt.setError("Nome do usuário deve ter mais de 8 caracteres");
                    isFormularioValido = false;
                }
                if (usernameTxt.getText().toString().isEmpty()) {
                    usernameTxt.setError("Nome do usuário não pode ser em branco");
                    isFormularioValido = false;
                }

                if (isFormularioValido) {
                    progDialog.show();
                    dismisskeyboard();

                    //TODO: FAZER CHAMADA DA API PARA CADASTRAR O USUARIO

                    final String username = usernameTxt.getText().toString();
                    String email = emailTxt.getText().toString();
                    String password = passwordTxt.getText().toString();
                    UsuarioSignUp usuario = new UsuarioSignUp(username, email, password);

                    final ParserUsuarioSignUp parser = new ParserUsuarioSignUp(usuario);
                    parser.get().enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {


                            if (response.isSuccessful()) {
                                final String idUsuario = response.body().toString();
                                Log.e("LCFR", "onResponse: " + idUsuario);

                                final AlertDialog alert =
                                        new AlertDialog.Builder(SignUpActivity.this)
                                                .setTitle("Parabéns!")
                                                .setMessage("Você esta quase lá. Agora complete o seu cadastro para ser mais um usuário do JobUp.")
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {


                                                        final Intent intent = new Intent(SignUpActivity.this, CadastroActivity.class);
                                                        intent.putExtra("idUsuario", idUsuario);
                                                        intent.putExtra("email", emailTxt.getText().toString());
                                                        startActivity(intent);
                                                    }
                                                })
                                                .create();
                                alert.show();


                            } else if (response.code() == 400) {
                                Gson gson = new Gson();

                                Body bd = null;
                                try {
                                    bd = gson.fromJson(response.errorBody().string(), Body.class);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                List<String> erros = bd.Errors;
                                if (erros.size() == 1) {
                                    erros = Arrays.asList(erros.get(0).split("\\."));
                                }

                                String mensagensDeErros = "";
                                for (String erro : erros) {
                                    mensagensDeErros += erro + "\n";
                                }
                                final AlertDialog alert =
                                        new AlertDialog.Builder(SignUpActivity.this)
                                                .setTitle("Ops! Erro ao criar a sua conta!")
                                                .setMessage(mensagensDeErros)
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        limpaFormulário();
                                                    }
                                                })
                                                .create();
                                alert.show();
                            }
                            progDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(SignUpActivity.this, "Falha ao cadastrar Usuario",
                                    Toast.LENGTH_SHORT).show();
                            progDialog.dismiss();
                        }
                    });
                }

                if (!isFormularioValido) {
                    final AlertDialog alert =
                            new AlertDialog.Builder(SignUpActivity.this)
                                    .setTitle("Ops! Erro ao preencher formulário")
                                    .setMessage("Corrija os campos inválidos")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            limpaFormulário();

                                        }
                                    })
                                    .create();
                    alert.show();
                }
                isFormularioValido = true;
            }
        });


    }

    private void limpaFormulário() {
        emailTxt.setText("");
        passwordTxt.setText("");
        reWritePasswordTxt.setText("");
        usernameTxt.setText("");
        usernameTxt.requestFocus();
    }


    // DISMISS KEYBOARD
    public void dismisskeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(usernameTxt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(emailTxt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(passwordTxt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(reWritePasswordTxt.getWindowToken(), 0);
    }

    class Body {
        boolean Succeeded;
        List<String> Errors;
    }
}
