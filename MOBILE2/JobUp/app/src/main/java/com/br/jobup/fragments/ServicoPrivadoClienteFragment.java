package com.br.jobup.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.br.jobup.R;
import com.br.jobup.adapters.ServicoPrivadoClienteAdapter;
import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.models.usuario.Usuario;
import com.br.jobup.preferencesPersistence.PreferencePersistence;
import com.br.jobup.services.tasks.TaskServicoPrivadoCliente;

import java.util.List;

/*
 * Created by Luiz Carlos Ramos on 6/16/17 7:48 PM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/15/17 11:10 PM
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicoPrivadoClienteFragment extends Fragment {

    public static final String ARGUMENTO_AVALIACOES_PROFISSIONAIS = "arg_avaliacoes";
    private LoaderManager mLoaderManager;
    private View mView;
    private Bundle mBundle;
    ListView lstView;
    private Button btnAceitar;
    private Button btnRecusar;
    private Button btnAvaliar;
    private ServicoPrivadoClienteAdapter adapter;

    private ListView mLstOfertaPrivada;

    public ServicoPrivadoClienteFragment() {
    }

    /**
     * Cria uma instancia do ServicoPrivadoClienteSemPropostaFragment
     * passando um bundle
     *
     * @param bundle
     * @return
     */
    public static ServicoPrivadoClienteFragment getInstance(Bundle bundle) {

        ServicoPrivadoClienteFragment frag = new ServicoPrivadoClienteFragment();
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

        //Inicializa o LoaderManager
        mLoaderManager = getActivity().getSupportLoaderManager();
        setRetainInstance(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mView = inflater.inflate(R.layout.fragment_servico_privado_cliente_com_proposta, container, false);

        lstView = (ListView) mView.findViewById(R.id.lstViewOfertaPrivadaClienteComProposta);
        lstView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        btnAceitar = (Button) mView.findViewById(R.id.btn_aceitar);
        btnRecusar = (Button) mView.findViewById(R.id.btn_recusar);
        btnAvaliar = (Button) mView.findViewById(R.id.btn_avaliar);
        final PreferencePersistence<Usuario> persistence = new PreferencePersistence<>(getContext());
        final Usuario usuarioCorrente = persistence.getObjectSavedInPreferences("UsuarioCorrent",
                "com.br.jobup.models.usuario.Usuario");

        //Executa o LoaderOfertaPrivadaCliente que vai fazer a chamada
        //a task TaskOfertaPrivadaSemProposta
        mLoaderManager.initLoader(11, null, new LoaderOfertaPrivadaCliente(getContext(), usuarioCorrente.idUsuario));
//        mLstOfertaPrivada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                final ServicoOfertaPrivada servico = (ServicoOfertaPrivada) mLstOfertaPrivada.getItemAtPosition(position);
//                adapter.adicionaDialogo(servico, view);
//            }
//        });

        return mView;
    }


    /**
     * Created by luizramos on 30/04/17.
     */

    public class LoaderOfertaPrivadaCliente implements LoaderManager.LoaderCallbacks<List<ServicoOfertaPrivada>> {

        private final Context context;

        private String idUsuarioCliente;

        public LoaderOfertaPrivadaCliente(Context context) {
            this.context = context;
        }

        public LoaderOfertaPrivadaCliente(Context context, String idUsuarioCliente) {
            this.context = context;
            this.idUsuarioCliente = idUsuarioCliente;
        }

        @Override
        public Loader<List<ServicoOfertaPrivada>> onCreateLoader(int id, Bundle args) {
            return new TaskServicoPrivadoCliente(context, idUsuarioCliente);
        }


        @Override
        public void onLoadFinished(Loader<List<ServicoOfertaPrivada>> loader, List<ServicoOfertaPrivada> ofertas) {

            Log.e("LCFR -> ", "onLoadFinished: " + ofertas.toString());
            if (ofertas != null) {
                adapter = new ServicoPrivadoClienteAdapter(context, ofertas, getActivity());
                lstView.setAdapter(adapter);
            }
        }

        @Override
        public void onLoaderReset(Loader<List<ServicoOfertaPrivada>> loader) {

        }
    }

}
