package com.br.jobup.fragments;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.br.jobup.R;
import com.br.jobup.adapters.TabDetalheProfissionalAdapter;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

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
        TabDetalheProfissionalAdapter viewPageAdapter = new TabDetalheProfissionalAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPageDetalheProfissional);
        viewPager.setAdapter(viewPageAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tblt_detalheProfissional);
        tabLayout.setupWithViewPager(viewPager);

    }
}
