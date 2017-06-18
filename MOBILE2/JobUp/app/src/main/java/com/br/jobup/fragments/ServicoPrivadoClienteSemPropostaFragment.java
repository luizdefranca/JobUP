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
import android.widget.ListView;

import com.br.jobup.R;
import com.br.jobup.adapters.ServicoPrivadoClienteSemPropostaAdapter;
import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.models.usuario.Usuario;
import com.br.jobup.preferencesPersistence.PreferencePersistence;
import com.br.jobup.services.tasks.TaskOfertaPrivadaClientesSemPropostaGetAll;

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
public class ServicoPrivadoClienteSemPropostaFragment extends Fragment {

    public static final String ARGUMENTO_AVALIACOES_PROFISSIONAIS = "arg_avaliacoes";
    private LoaderManager mLoaderManager;
    private View mView;
    private Bundle mBundle;
    ListView lstView;

    private ListView mLstOfertaPrivada;

    public ServicoPrivadoClienteSemPropostaFragment() {
    }

    /**
     * Cria uma instancia do ServicoPrivadoClienteSemPropostaFragment
     * passando um bundle
     * @param bundle
     * @return
     */
    public static ServicoPrivadoClienteSemPropostaFragment getInstance(Bundle bundle) {

        ServicoPrivadoClienteSemPropostaFragment frag = new ServicoPrivadoClienteSemPropostaFragment();

    //ESTAS INSTRUÇÕES ABAIXO SÓ SÃO NECESSÁRIAS SE FOR PASSADA ALGUMA INFORMAÇÃO VIA BUNDLE
//        Bundle args = new Bundle();
//        args.putBundle(ARGUMENTO_AVALIACOES_PROFISSIONAIS, bundle);
//
//        frag.setArguments(args);

        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ESTAS INSTRUÇÕES ABAIXO SÓ SÃO NECESSÁRIAS SE FOR PASSADA ALGUMA INFORMAÇÃO VIA BUNDLE
        //SÃO NECESSÁRIAS PARA PEGAR AS INFORMAÇÕES PASSADAS
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


        mView = inflater.inflate(R.layout.fragment_servico_privado_cliente_sem_proposta, container, false);

        lstView = (ListView) mView.findViewById(R.id.lstViewOfertaPrivadaClienteSemProposta);
        final PreferencePersistence<Usuario> persistence = new PreferencePersistence<>(getContext());
        final Usuario usuarioCorrente = persistence.getObjectSavedInPreferences("UsuarioCorrent",
                "com.br.jobup.models.usuario.Usuario");

        //Executa o LoaderOfertaPrivadaCliente que vai fazer a chamada
        //a task TaskOfertaPrivadaSemProposta
        mLoaderManager.initLoader(12, null, new LoaderOfertaPrivadaClienteSemProposta(getContext(), usuarioCorrente.idUsuario));
        return mView;
    }




    /**
     * Created by luizramos on 30/04/17.
     */

    public class LoaderOfertaPrivadaClienteSemProposta implements LoaderManager.LoaderCallbacks<List<ServicoOfertaPrivada>>{

        private final Context context;

        private String idUsuarioCliente;

        public LoaderOfertaPrivadaClienteSemProposta(Context context){
            this.context = context;
        }
        public LoaderOfertaPrivadaClienteSemProposta(Context context, String idUsuarioCliente){
            this.context = context;
            this.idUsuarioCliente = idUsuarioCliente;
        }
        @Override
        public Loader<List<ServicoOfertaPrivada>> onCreateLoader(int id, Bundle args) {
            return new TaskOfertaPrivadaClientesSemPropostaGetAll(context, idUsuarioCliente);
        }



        @Override
        public void onLoadFinished(Loader<List<ServicoOfertaPrivada>> loader, List<ServicoOfertaPrivada> Ofertas) {

            if(Ofertas != null) {
                ServicoPrivadoClienteSemPropostaAdapter adapter = new ServicoPrivadoClienteSemPropostaAdapter(context, Ofertas);
                lstView.setAdapter(adapter);
            }
        }

        @Override
        public void onLoaderReset(Loader<List<ServicoOfertaPrivada>> loader) {

        }
    }

}
