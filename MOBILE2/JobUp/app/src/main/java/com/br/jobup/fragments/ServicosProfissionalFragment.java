package com.br.jobup.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.br.jobup.R;
import com.br.jobup.models.servico.Servico;

import java.util.Arrays;
import java.util.List;

import static com.br.jobup.R.drawable.servicos;

/*
 * Created by Luiz Carlos Ramos on 25/05/17 22:07
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 25/05/17 22:07
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicosProfissionalFragment extends Fragment {

    public static final String ARGUMENTO_SERVICOS_PROFISSIONAIS = "arg_servicos";

    private View mView;
    private Bundle mBundle;
    private ListView mUltimosServicosListView;
    private String idProfissional;
    private int idEspecialidade;

    public ServicosProfissionalFragment() {
        // Required empty public constructor
    }

    public static ServicosProfissionalFragment getInstance(Bundle bundle){

        ServicosProfissionalFragment frag = new ServicosProfissionalFragment();
        Bundle args =  new Bundle();
        args.putBundle(ARGUMENTO_SERVICOS_PROFISSIONAIS, bundle);

        frag.setArguments(args);
        frag.getId();

        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments().getBundle(ARGUMENTO_SERVICOS_PROFISSIONAIS);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_servicos_profissional, view, false);
        mUltimosServicosListView = (ListView) view.findViewById(R.id.lstViewUltimosServicos);

        List<Servico> servicos = Arrays.asList(
                new Servico("caes enorme", "Servico como cuidador de 6 pastores alemaes"),
                new Servico("caes maiores", "Servico como cuidador de 8 doubermas alemaes"));



        return mView;
    }

    class Servico{
        String nome;
        String descricao;
        Servico (String nome, String descricao){
            this.nome = nome;
            this.descricao = descricao;
        }
    }
}
