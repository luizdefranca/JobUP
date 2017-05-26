package com.br.jobup.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.jobup.R;

import static com.br.jobup.fragments.AvaliacoesProfissionalFragment.ARGUMENTO_AVALIACOES_PROFISSIONAIS;

/*
 * Created by Luiz Carlos Ramos on 25/05/17 21:59
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 25/05/17 21:59
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalheProfissionalFragment extends Fragment {

    public static final String ARGUMENTO_DETALHE_PROFISSIONAL = "ARG_DETALHE";
    private View mView;
    private Bundle mBundle;

    public DetalheProfissionalFragment() {
        // Required empty public constructor
    }
    public static DetalheProfissionalFragment getInstance(Bundle bundle){

        DetalheProfissionalFragment frag = new DetalheProfissionalFragment();
        Bundle args =  new Bundle();
        args.putBundle(ARGUMENTO_DETALHE_PROFISSIONAL, bundle);

        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments().getBundle(ARGUMENTO_AVALIACOES_PROFISSIONAIS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_detalhe_profissional, container, false);


        return mView;
    }

}
