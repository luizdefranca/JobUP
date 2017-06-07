package com.br.jobup.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.br.jobup.fragments.AvaliacoesProfissionalFragment;
import com.br.jobup.fragments.DetalheProfissionalFragment;
import com.br.jobup.fragments.OfertaPrivadaFragment;
import com.br.jobup.fragments.PropostaPrivadaFragment;
import com.br.jobup.fragments.ServicosProfissionalFragment;

/*
 * Created by Luiz Carlos Ramos on 30/05/17 10:07
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 26/05/17 22:59
 */

/**
 * Created by luizramos on 25/05/17.
 */

public class TabOfertaPrivadaAdapter extends FragmentPagerAdapter {

    public static final String TAG = TabOfertaPrivadaAdapter.class.getSimpleName();
    private Context context;
    private Bundle bundle;
    private static final int NUM_TABS = 2;
    private static final String[] TABS = {"Ofertas Feitas", "Propostas Aceitas"};

    public TabOfertaPrivadaAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    public TabOfertaPrivadaAdapter(Bundle bundle, FragmentManager fm) {
        super(fm);
        this.bundle = bundle;
        Log.e(TAG, "TabOfertaPrivadaAdapter: " + bundle.toString() );
    }

    public TabOfertaPrivadaAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return OfertaPrivadaFragment.getInstance(bundle);
        } else {
            return PropostaPrivadaFragment.getInstance(bundle);
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
