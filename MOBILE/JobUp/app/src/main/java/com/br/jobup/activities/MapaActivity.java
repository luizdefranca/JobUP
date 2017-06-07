package com.br.jobup.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.br.jobup.R;
import com.br.jobup.fragments.MapaFragment;
import com.br.jobup.maps.Localizador;
import com.br.jobup.models.especialidade.ServicoOferta;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.R.attr.permission;

/*
 * Created by Luiz Carlos Ramos on 6/7/17 12:04 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/6/17 11:49 PM
 */

public class MapaActivity extends AppCompatActivity {
    public static final String LISTA_DE_ESPECIALIDADES = "LISTA_DE_ESPECIALIDADES";
    public static final String BUNDLE_LISTA_DE_ESPECIALIDADES = "BUNDLE_LISTA_DE_ESPECIALIDADES";
    private static final int REQUEST_PERMISSOES = 1;
    private MapaFragment mapaFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        // Set Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setTitle("Localização");

        //Pega o ArrayList com as especialidades do intent
        final Intent intent = getIntent();
        final ArrayList<ServicoOferta> arrayListEspecialidades = intent.getParcelableArrayListExtra(LISTA_DE_ESPECIALIDADES);

        //Cria o Bundle e coloca o ArrayListEspecialidade
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(LISTA_DE_ESPECIALIDADES, arrayListEspecialidades);

        //Seta o MapFragment
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        //Instancia o MapaFragment passando o Bundle
         mapaFragment = MapaFragment.getInstance(bundle);
//        mapaFragment.setArguments(bundle);
        transaction.replace(R.id.frameMapa, mapaFragment);
        transaction.commit();

//        //Pede permissão para utilizar o gps
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                String[] permissoes = {ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION};
                requestPermissions(permissoes, REQUEST_PERMISSOES);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSOES) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                new Localizador(this, mapaFragment);
            }
        }
    }
    /**
     * Este método é utilizado para não apagar a activity quando usar o botao up
     * @return
     */
    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
}
