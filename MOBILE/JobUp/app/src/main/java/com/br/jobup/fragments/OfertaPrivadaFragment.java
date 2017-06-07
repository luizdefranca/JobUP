package com.br.jobup.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.br.jobup.R;
import com.br.jobup.models.servico.Oferta;
import com.br.jobup.models.usuario.Usuario;
import com.br.jobup.preferencesPersistence.PreferencePersistence;
import com.br.jobup.services.tasks.TaskOfertaPrivadaGetAll;

import java.util.Arrays;
import java.util.List;

/*
 * Created by Luiz Carlos Ramos on 30/05/17 10:11
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 27/05/17 03:30
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class OfertaPrivadaFragment extends Fragment {

    public static final String ARGUMENTO_AVALIACOES_PROFISSIONAIS = "arg_avaliacoes";
    private LoaderManager mLoaderManager;
    private View mView;
    private Bundle mBundle;
    private ListView lstView;

    //    private String idProfissional;
//    private int idEspecialidade;
    private ListView mLstOfertaPrivada;

    public OfertaPrivadaFragment() {
        // Required empty public constructor
    }

    public static OfertaPrivadaFragment getInstance(Bundle bundle) {

        OfertaPrivadaFragment frag = new OfertaPrivadaFragment();
        Bundle args = new Bundle();
//        args.putBundle(ARGUMENTO_AVALIACOES_PROFISSIONAIS, bundle);

        frag.setArguments(args);

        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mBundle = getArguments().getBundle(ARGUMENTO_AVALIACOES_PROFISSIONAIS);
//        idProfissional = mBundle.getString(CatalogoEspecialidadeActivity.ID_PROFISSIONAL);
//        idEspecialidade = mBundle.getInt(CatalogoEspecialidadeActivity.ID_ESPECIALIDADE);
        mLoaderManager = getActivity().getSupportLoaderManager();
        setRetainInstance(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mView = inflater.inflate(R.layout.fragment_oferta_privada, container, false);

        lstView = (ListView) mView.findViewById(R.id.lstViewOfertaPrivada);
        final PreferencePersistence<Usuario> persistence = new PreferencePersistence<>(getContext());
        final Usuario usuarioCorrente = persistence.getObjectSavedInPreferences("UsuarioCorrent",
                "com.br.jobup.models.usuario.Usuario");
        mLoaderManager.initLoader(0, null, new LoaderCallBack(usuarioCorrente.idUsuario, getContext()));
//        mLoaderManager.initLoader(0, null, new LoaderCallBack("f4fafe12-e180-4a50-b39b-add622ef7cce", getContext()));


        List<Teste> listTest = Arrays.asList(
                new Teste("Título 1", "corpo 1"),
                new Teste("Título 2", "corpo 2")
        );


        return mView;
    }

    private class Teste {
        String titulo;
        String corpo;

        Teste(String titulo, String corpo) {
            this.titulo = titulo;
            this.corpo = corpo;
        }

    }

    public class TwolineAdapter extends ArrayAdapter<Oferta> {

        private List<Oferta> objects;

        public TwolineAdapter(Context context, List<Oferta> objects) {
            super(context, android.R.layout.simple_list_item_2, android.R.id.text1, objects);
            this.objects = objects;
        }

        @Override
        public View getView(int position, View view1, ViewGroup parent) {
            View view = (View) super.getView(position, view1, parent);
            TextView text1 = (TextView) view.findViewById(android.R.id.text1);
            TextView text2 = (TextView) view.findViewById(android.R.id.text2);

            text1.setText(objects.get(position).dsTitulo);
            text2.setText(objects.get(position).dsObservacoes);
            return view;
        }
    }

    public class LoaderCallBack implements LoaderManager.LoaderCallbacks<List<Oferta>> {

        private final Context context;

        private String idUsuario;

        public LoaderCallBack(Context context) {
            this.context = context;
        }

        public LoaderCallBack(String idUsuario, Context context) {
            this.context = context;
            this.idUsuario = idUsuario;
        }

        @Override
        public Loader<List<Oferta>> onCreateLoader(int id, Bundle args) {
            return new TaskOfertaPrivadaGetAll(context, idUsuario);
        }

        @Override
        public void onLoadFinished(Loader<List<Oferta>> loader, List<Oferta> ofertas) {
            ArrayAdapter<Oferta> adapter = new TwolineAdapter(getContext(), ofertas);
            lstView.setAdapter(adapter);

        }

        @Override
        public void onLoaderReset(Loader<List<Oferta>> loader) {

        }
    }
}
