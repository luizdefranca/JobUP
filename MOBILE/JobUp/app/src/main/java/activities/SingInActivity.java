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
import com.br.jobup.models.Usuario;
import com.br.jobup.models.UsuarioSignIn;
import com.br.jobup.services.usuarioFullServices.parsers.ParserUsuarioSignIn;
import com.github.hynra.gsonsharedpreferences.GSONSharedPreferences;
import com.google.gson.Gson;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.id;

/*
 * Created by Luiz Carlos Ramos on 09/05/17 19:02
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 09/05/17 18:46
 */

public class SingInActivity extends AppCompatActivity  {

    /* Views */
    ProgressDialog progDialog;
    EditText usernameTxt;
    EditText passwordTxt;

    Usuario usuario;



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

                final UsuarioSignIn login = new UsuarioSignIn(userName, password);
                final ParserUsuarioSignIn parser = new ParserUsuarioSignIn(login);
                parser.get().enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        Usuario usuarioCorrente = response.body();
                        if(response.isSuccessful()){
                            Toast.makeText(SingInActivity.this, "usuario: "+ response.body().getNome()
                                    + " logado com sucesso!", Toast.LENGTH_SHORT).show();
                            GSONSharedPreferences gsonSharedPrefs = new GSONSharedPreferences(SingInActivity.this, "UsuarioCorrente");
                            gsonSharedPrefs.saveObject(usuarioCorrente);
                            final Intent intent = new Intent(SingInActivity.this, MainActivity.class);
                            intent.putExtra("usuarioCorrent", usuario);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {

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
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
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





