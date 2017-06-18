package com.br.jobup.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.br.jobup.R;
import com.br.jobup.adapters.TabServicoPrivadoClienteAdapter;

/*
 * Created by Luiz Carlos Ramos on 30/05/17 10:02
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 27/05/17 12:23
 */

public class ServicoPrivadoClienteViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_privada_view_pager);

        // Set Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Set Title on the ActionBar
        getSupportActionBar().setTitle("Oferta Privada");


        //Cria o viewPageAdapter e passa o Bundle
        Bundle bundle = new Bundle(); //esta linha tem de ser tirada
        TabServicoPrivadoClienteAdapter viewPageAdapter =
                new TabServicoPrivadoClienteAdapter(  bundle,         //bundle
                        getSupportFragmentManager());

        //Cria o view page e insere o viewPageAdapter
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPageOfertaPrivada);
        viewPager.setAdapter(viewPageAdapter);


        final FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.findFragmentById(R.layout.fragment_detalhe_profissional);

        //Cria o TabLayout e insere o viewPageAdapter
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tblt_oferta_privada);
        tabLayout.setupWithViewPager(viewPager);

    }


}
