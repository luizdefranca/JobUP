package com.br.jobup.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.jobup.R;

import com.br.jobup.activities.CatalogoEspecialidadeActivity;

/*
 * Created by Luiz Carlos Ramos on 25/05/17 22:04
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 25/05/17 22:04
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class AvaliacoesProfissionalFragment extends Fragment {

    public static final String ARGUMENTO_AVALIACOES_PROFISSIONAIS = "arg_avaliacoes";

    private View mView;
    private Bundle mBundle;
    private String idProfissional;
    private int idEspecialidade;

    public AvaliacoesProfissionalFragment() {
        // Required empty public constructor
    }

    public static AvaliacoesProfissionalFragment getInstance(Bundle bundle){

        AvaliacoesProfissionalFragment frag = new AvaliacoesProfissionalFragment();
        Bundle args =  new Bundle();
        args.putBundle(ARGUMENTO_AVALIACOES_PROFISSIONAIS, bundle);

        frag.setArguments(args);

        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments().getBundle(ARGUMENTO_AVALIACOES_PROFISSIONAIS);
        idProfissional = mBundle.getString(CatalogoEspecialidadeActivity.ID_PROFISSIONAL);
        idEspecialidade = mBundle.getInt(CatalogoEspecialidadeActivity.ID_ESPECIALIDADE);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


         mView = inflater.inflate(R.layout.fragment_avaliacoes_profissional, container, false);


        return mView;
    }

}
