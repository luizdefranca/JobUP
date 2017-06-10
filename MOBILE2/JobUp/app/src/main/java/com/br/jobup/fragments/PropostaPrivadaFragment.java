package com.br.jobup.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.jobup.R;

/*
 * Created by Luiz Carlos Ramos on 30/05/17 10:11
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 27/05/17 03:30
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class PropostaPrivadaFragment extends Fragment {

    public static final String ARGUMENTO_AVALIACOES_PROFISSIONAIS = "arg_avaliacoes";

    private View mView;
    private Bundle mBundle;
//    private String idProfissional;
//    private int idEspecialidade;

    public PropostaPrivadaFragment() {
        // Required empty public constructor
    }

    public static PropostaPrivadaFragment getInstance(Bundle bundle){

        PropostaPrivadaFragment frag = new PropostaPrivadaFragment();
        Bundle args =  new Bundle();
//        args.putBundle(ARGUMENTO_AVALIACOES_PROFISSIONAIS, bundle);

        frag.setArguments(args);

        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments().getBundle(ARGUMENTO_AVALIACOES_PROFISSIONAIS);
//        idProfissional = mBundle.getString(CatalogoEspecialidadeActivity.ID_PROFISSIONAL);
//        idEspecialidade = mBundle.getInt(CatalogoEspecialidadeActivity.ID_ESPECIALIDADE);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


         mView = inflater.inflate(R.layout.fragment_proposta_privada, container, false);


        return mView;
    }

}
