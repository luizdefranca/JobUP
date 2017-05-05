package br.com.jobup.telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.jobup.R;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void LoginActivity(View view) {
        Intent LoginActivity = new Intent(RegistroActivity.this, LoginActivity.class);
        startActivity(LoginActivity);
        finish();
    }
}
