package trash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.br.jobup.R;
import com.br.jobup.dao.DatabaseHelper;

/*
 * Created by Luiz Carlos Ramos on 07/05/17 15:12
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 01/05/17 11:51
 */

public class ApresentacaoActivity extends AppCompatActivity {
    public static final String TAG = ApresentacaoActivity.class.getSimpleName();
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
                Intent LoginActivity = new Intent(ApresentacaoActivity.this, com.br.jobup.LoginActivity.class);
                startActivity(LoginActivity);
                this.finish();
            }

            private void finish() {
            }
        }, splashInterval);


        //List<Usuario> usuarios = new UsuarioService().getAll();





//        Log.i(TAG, "usuario: " + usuarios.get(0).getNome());
    }
}
