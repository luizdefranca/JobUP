package com.br.jobup;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.br.jobup.dao.DatabaseHelper;

public class ApresentacaoActivity extends AppCompatActivity {
    private static int splashInterval = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);
    }
    public void LoginActivity(View view) {
        DatabaseHelper.newInstance(this);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent LoginActivity = new Intent(ApresentacaoActivity.this, LoginActivity.class);
                startActivity(LoginActivity);
                this.finish();
            }

            private void finish() {
            }
        }, splashInterval);



    }
}
