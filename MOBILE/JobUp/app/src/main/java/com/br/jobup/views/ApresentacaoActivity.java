package com.br.jobup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.UUID;

public class ApresentacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        Log.d("GUID", UUID.randomUUID().toString());
    }
    public void LoginActivity(View view) {
        Intent LoginActivity = new Intent(ApresentacaoActivity.this, LoginActivity.class);
        startActivity(LoginActivity);
        finish();
    }
}
