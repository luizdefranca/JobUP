package br.com.jobup.telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.jobup.R;

public class CatalogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);


    }

    public void DetalheCatalogoActivity(View view) {
        Intent DetalheCatalogoActivity = new Intent(CatalogoActivity.this, DetalheCatalogoActivity.class);
        startActivity(DetalheCatalogoActivity);
        finish();
    }
}
