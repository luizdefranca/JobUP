package com.br.jobup.activities;

/*
 * Created by Luiz Carlos Ramos on 17/06/17 22:42
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 17/06/17 22:42
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.br.jobup.R;

/**
 * Created by andre on 17/06/2017.
 */

public class AvaliacaoActivity extends Activity {

    private RatingBar ratingbar;

    private Button btnEnviar;


    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        ratingbar = (RatingBar) findViewById(R.id.ratingBar);
        btnEnviar  = (Button) findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AvaliacaoActivity.this,
                        String.valueOf(ratingbar.getRating()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
