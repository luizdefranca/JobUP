package br.com.workup.jobup.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.br.jobup.R;

public class ApresentacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);
    }
    public void LoginActivity(View view) {
        Intent LoginActivity = new Intent(ApresentacaoActivity.this, LoginActivity.class);
        startActivity(LoginActivity);
        finish();
    }
}
