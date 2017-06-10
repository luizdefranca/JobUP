package com.br.jobup.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.br.jobup.fragments.AvaliacoesProfissionalFragment;
import com.br.jobup.fragments.DetalheProfissionalFragment;
import com.br.jobup.fragments.ServicosProfissionalFragment;

import static android.os.Build.VERSION_CODES.N;
import static com.br.jobup.fragments.ServicosProfissionalFragment.getInstance;

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

    public static final String TAG = TabDetalheProfissionalAdapter.class.getSimpleName();
    private Context context;
    private Bundle bundle;
    private static final int NUM_TABS = 3;
    private static final String[] TABS = {"Detalhes", "Avaliações", "Últimos Serviços"};

    public TabDetalheProfissionalAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    public TabDetalheProfissionalAdapter(Bundle bundle, FragmentManager fm) {
        super(fm);
        this.bundle = bundle;
        Log.e(TAG, "TabDetalheProfissionalAdapter: " + bundle.toString() );
    }

    public TabDetalheProfissionalAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return DetalheProfissionalFragment.getInstance(bundle);
        } else if (position == 1) {
            return AvaliacoesProfissionalFragment.getInstance(bundle);
        } else {
            return ServicosProfissionalFragment.getInstance(bundle);
        }
    }

    @Override
    public int getCount() {
        return TABS.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {


        return TABS[position];
    }
}
