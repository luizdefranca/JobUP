package com.br.jobup.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.br.jobup.fragments.AvaliacoesProfissionalFragment;
import com.br.jobup.fragments.DetalheProfissionalFragment;
import com.br.jobup.fragments.ServicosProfissionalFragment;

/*
 * Created by Luiz Carlos Ramos on 25/05/17 20:30
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 25/05/17 20:30
 */

/**
 * Created by luizramos on 25/05/17.
 */

public class TabDetalheProfissionalAdapter extends FragmentPagerAdapter {

    private Context context;
    private static final int NUM_TABS = 3;
    private static final String[] TABS = {"Detalhes", "Avaliações", "Últimos Serviços" };

    public TabDetalheProfissionalAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }
    public TabDetalheProfissionalAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            return DetalheProfissionalFragment.getInstance(null);
        } else if(position == 1){
            return AvaliacoesProfissionalFragment.getInstance(null);
        } else {
            return ServicosProfissionalFragment.getInstance(null);
        }
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {


        return TABS[position];
    }
}
