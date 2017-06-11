package com.br.jobup.fragments;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.br.jobup.R;
import com.br.jobup.adapters.TabDetalheProfissionalAdapter;

import com.br.jobup.activities.CatalogoEspecialidadeActivity;

/*
 * Created by Luiz Carlos Ramos on 26/05/17 01:28
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 26/05/17 01:28
 */

public class DetalheProfissionalViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_profissional_view_pager);

        // Set Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Set Title on the ActionBar
        getSupportActionBar().setTitle("Detalhe do Profissional");

        //Pega o Bundle da CatalogoEspecialidadeActivity com as informacoes do idEspecialidade e IdProfissional
        final Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra(CatalogoEspecialidadeActivity.DETALHE_PROFISSIONAL);

        //Cria o viewPageAdapter e passa o Bundle
        TabDetalheProfissionalAdapter viewPageAdapter =
                new TabDetalheProfissionalAdapter(bundle,getSupportFragmentManager());

        //Cria o view page e insere o viewPageAdapter
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPageDetalheProfissional);
        viewPager.setAdapter(viewPageAdapter);


        final FragmentManager fragmentManager = getSupportFragmentManager();

        //Cria o TabLayout e insere o viewPageAdapter
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tblt_detalheProfissional);
        tabLayout.setupWithViewPager(viewPager);

    }

    /**
     * Este método é utilizado para não pagar a activity quando usar o botao up
     * @return
     */
    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
}
