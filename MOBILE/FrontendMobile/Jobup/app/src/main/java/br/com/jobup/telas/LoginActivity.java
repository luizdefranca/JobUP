package br.com.jobup.telas;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.jobup.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void LoginActivity(View view) {
        Intent LoginActivity = new Intent(LoginActivity.this, PerfilActivity.class);
        startActivity(LoginActivity);
        finish();
    }
    public void RegistroActivity(View view) {
        Intent RegistroActivity = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(RegistroActivity);
    }

}
