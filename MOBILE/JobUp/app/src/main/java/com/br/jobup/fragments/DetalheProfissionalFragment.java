package com.br.jobup.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.br.jobup.R;
import com.br.jobup.adapters.OutrasEspecializacoesAdapter;
import com.br.jobup.models.especialidade.EspecialidadeCatalogo;
import com.br.jobup.models.usuario.PerfilProfissional;
import com.br.jobup.services.parsers.ParserPerfilProfissional;
import com.br.jobup.util.Parsers;

import java.util.ArrayList;
import java.util.List;

import activities.CatalogoEspecialidadeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public static final String BUNDLE_DETALHE_PROFISSIONAL = "ARG_DETALHE";
    private static final String TAG = DetalheProfissionalFragment.class.getSimpleName();
    private View mView;
    private Bundle mBundle;
    private String idProfissional;
    private int idEspecialidade;
    private EspecialidadeCatalogo especialidadeDetalhe;
    private ListView listViewOutrasEspecializacoes;
    protected ArrayList<PerfilProfissional> perfis;


    public DetalheProfissionalFragment() {
        // Required empty public constructor
    }

    public static DetalheProfissionalFragment getInstance(Bundle bundle) {

        DetalheProfissionalFragment frag = new DetalheProfissionalFragment();
        Bundle args = new Bundle();
        args.putBundle(BUNDLE_DETALHE_PROFISSIONAL, bundle);

        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments().getBundle(BUNDLE_DETALHE_PROFISSIONAL);
        idProfissional = mBundle.getString(CatalogoEspecialidadeActivity.ID_PROFISSIONAL);
        idEspecialidade = mBundle.getInt(CatalogoEspecialidadeActivity.ID_ESPECIALIDADE);
        especialidadeDetalhe = (EspecialidadeCatalogo) mBundle.getSerializable(CatalogoEspecialidadeActivity.ESPECIALIDADE_DETALHE);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_detalhe_profissional, container, false);

        //Inicializa campos da View
        TextView mNome = (TextView) mView.findViewById(R.id.detalhe_profissional_txtNome);
        TextView mDescricao = (TextView) mView.findViewById(R.id.detalhe_profissional_txtDescricao);
        TextView mDtCadastro = (TextView) mView.findViewById(R.id.detalhe_profissional_txtDtCadastro);
        TextView mCurriculo = (TextView) mView.findViewById(R.id.detalhe_profissional_txtCurriculo);
        TextView mBairro = (TextView) mView.findViewById(R.id.detalhe_profissional_txtBairro);
        TextView mCidade = (TextView) mView.findViewById(R.id.detalhe_profissional_txtCidade);
        TextView mEstado = (TextView) mView.findViewById(R.id.detalhe_profissional_txtEstado);

        //Set the text of the fields
        mNome.setText(especialidadeDetalhe.getNome());
        mDescricao.setText((especialidadeDetalhe.getDescEspecialidade()));
        mDtCadastro.setText(Parsers.parseDataToStringNormal(especialidadeDetalhe.getDtInclusao()));
        mCurriculo.setText(especialidadeDetalhe.getResumoCurriculo());
        mBairro.setText(especialidadeDetalhe.getBairro());
        mCidade.setText(especialidadeDetalhe.getCidade());
        mEstado.setText(especialidadeDetalhe.getEstado());

        //Inicializa a ListView ltviewOutrasEspecialidade
        listViewOutrasEspecializacoes = (ListView) mView.findViewById(R.id.ltviewOutrasEspecializacoes);

        //chama o parser pra ser criada uma CallBack
        final ParserPerfilProfissional parser = new ParserPerfilProfissional(idProfissional);
        parser.getComIdUsuario().enqueue(new Callback<List<PerfilProfissional>>() {
            @Override
            public void onResponse(Call<List<PerfilProfissional>> call, Response<List<PerfilProfissional>> response) {



                //cria um adapter para o ListView
                if(savedInstanceState == null) {
                    //Pega a Lista de perfisprofissional do response
                    perfis = new ArrayList<PerfilProfissional>(response.body());

                } else{
                    //Pega a lista da Instância salva
                    perfis = savedInstanceState.getParcelableArrayList("perfis");
                }
                OutrasEspecializacoesAdapter adapter = new OutrasEspecializacoesAdapter(getContext(), perfis);
                //seta o adapter para o ListView
                listViewOutrasEspecializacoes.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<PerfilProfissional>> call, Throwable t) {

            }
        });

        return mView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putParcelableArrayList("perfis", perfis);
        super.onSaveInstanceState(outState);
    }
}
