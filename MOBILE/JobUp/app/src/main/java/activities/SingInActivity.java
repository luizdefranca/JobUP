package activities;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.jobup.R;
import com.br.jobup.models.login.UsuarioSignIn;
import com.br.jobup.models.usuario.Usuario;
import com.br.jobup.services.usuarioFullServices.parsers.ParserUsuarioSignIn;
import com.github.hynra.gsonsharedpreferences.GSONSharedPreferences;
import com.github.hynra.gsonsharedpreferences.ParsingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Created by Luiz Carlos Ramos on 09/05/17 19:02
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 09/05/17 18:46
 */

public class SingInActivity extends AppCompatActivity {

    /* Views */
    ProgressDialog progDialog;
    EditText usernameTxt;
    EditText passwordTxt;

    Usuario usuario;


    @Override
    protected void onStart() {

        super.onStart();
        GSONSharedPreferences gsonSharedPrefs = new GSONSharedPreferences(SingInActivity.this, "UsuarioCorrente");
        Usuario usuarioCorrente = null;
        try {
            usuarioCorrente = (Usuario) gsonSharedPrefs.getObject(new Usuario());
            if (usuarioCorrente != null) {
                Log.i("test", usuarioCorrente.getNome());
                Toast.makeText(SingInActivity.this, "usuario corrente " + usuarioCorrente.getNome(), Toast.LENGTH_SHORT).show();
            }
        } catch (ParsingException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Set Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        // Set Title on the ActionBar
        getSupportActionBar().setTitle("UsuarioSignIn");


        // Init a ProgressDialog
        progDialog = new ProgressDialog(this);
        progDialog.setTitle(R.string.app_name);
        progDialog.setMessage("Logging in...");

        progDialog.setIndeterminate(false);

        usernameTxt = (EditText) findViewById(R.id.usernameTxt);
        passwordTxt = (EditText) findViewById(R.id.passwordTxt);


        // UsuarioSignIn Button
        Button loginButt = (Button) findViewById(R.id.loginButt);
        loginButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progDialog.show();

                String userName = usernameTxt.getText().toString();
                String password = passwordTxt.getText().toString();

//TODO: VERIFICAR SE JA EXISTE UM USUARIO CORRENTE SALVO NA PREFERENCIA DO APARELHO CASO ESTEJA É PRA LOGAR AUTOMATICAMENTE

                final UsuarioSignIn login = new UsuarioSignIn(userName, password);
                final ParserUsuarioSignIn parser = new ParserUsuarioSignIn(login);

                //TODO: retirar a linha a seguir ela faz o login sem checar
 //               startActivity(new Intent(SingInActivity.this, MainActivity.class));

                parser.get().enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                        if (response.isSuccessful()) {

                            if (response.body() != null) {
                                Usuario usuarioCorrente = response.body();
                                Toast.makeText(SingInActivity.this, "usuario: " + response.body().getNome()
                                        + " logado com sucesso!", Toast.LENGTH_SHORT).show();
                                GSONSharedPreferences gsonSharedPrefs = new GSONSharedPreferences(SingInActivity.this, "UsuarioCorrente");
                                gsonSharedPrefs.saveObject(usuarioCorrente);
                                progDialog.dismiss();
                                final Intent intent = new Intent(SingInActivity.this, MainActivity.class);
                                intent.putExtra("usuarioCorrent", usuario);
                                startActivity(intent);
                            } else if(response.body() == null){
                                progDialog.dismiss();
                                final AlertDialog alert =
                                        new AlertDialog.Builder(SingInActivity.this)
                                                .setTitle("Ufa! Falta pouco!")
                                                .setMessage("Agora complete o seu cadastro para ser mais um usuário do JobUp.")
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        usernameTxt.setText("");
                                                        passwordTxt.setText("");
                                                        usernameTxt.requestFocus();
                                                    }
                                                })
                                                .create();
                                alert.show();
                            }
                        } else if(response.code() == 400){
                            final AlertDialog alert =
                                    new AlertDialog.Builder(SingInActivity.this)
                                            .setTitle("Ops!")
                                            .setMessage("Senha ou usuário inválido!")
                                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    usernameTxt.setText("");
                                                    passwordTxt.setText("");
                                                    usernameTxt.requestFocus();
                                                }
                                            })
                                            .create();
                            alert.show();
                        }

                        //Desabilita o ProgressDialog
                        if (progDialog.isShowing()) {
                            progDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        if (progDialog.isShowing()) {
                            progDialog.dismiss();
                        }
                        Toast.makeText(SingInActivity.this, "Usuário ou senha inválidos.", Toast.LENGTH_SHORT).show();

                        //TODO: LIMPAR O TEXTVIEW COM O NOME DO USUARIO E A SENHA

                    }
                });
            }
        });


        // Sign Up Button
        Button signupButt = (Button) findViewById(R.id.signUpButt);
        signupButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingInActivity.this, SignUpActivity.class));
            }
        });


        // Forgot Password Button
        Button fpButt = (Button) findViewById(R.id.forgotPassButt);
        fpButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(SingInActivity.this);
                alert.setTitle(R.string.app_name);
                alert.setMessage("Type the valid email address you've used to register on this app");

                // Add an EditTxt
                final EditText editTxt = new EditText(SingInActivity.this);
                editTxt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                alert.setView(editTxt);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

//
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

                alert.show();

            }
        });


        // MARK: - FACEBOOK LOGIN BUTTON ------------------------------------------------------------------
        Button fbButt = (Button) findViewById(R.id.facebookButt);
        fbButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> permissions = Arrays.asList("public_profile", "email");
                progDialog.setMessage("Please wait...");
                progDialog.show();

//
            }
        });


        // This code generates a KeyHash that you'll have to copy from your Logcat console and paste it into Key Hashes field in the 'Settings' section of your Facebook Android App
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    // IMPORTANT: REPLACE THE PACKAGE NAME BELOW WITH YOUR OWN ONE!
                    "classify.domain.com.classify",

                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("log-", "keyhash: " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }// end onCreate()


    // MARK: - FACEBOOK GRAPH REQUEST --------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    // BACK BUTTON ----------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            // DEFAULT BACK BUTTON
            case android.R.id.home:
                this.finish();
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }


}//@end





