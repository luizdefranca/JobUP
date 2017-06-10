
package com.br.jobup.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.br.jobup.R;

/*
 * Created by Luiz Carlos Ramos on 6/7/17 12:01 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 5/23/17 1:32 PM
 */

public class ApresentacaoActivity extends AppCompatActivity {

    //Set waktu lama splashscreen
    private static int splashInterval = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_apresentacao);




        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(ApresentacaoActivity.this, SingInActivity.class);
                startActivity(i);
                this.finish();
            }

            private void finish() {
            }
        }, splashInterval);

    };

}
