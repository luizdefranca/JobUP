package com.br.jobup.adapters;

/*
 * Created by Luiz Carlos Ramos on 26/05/17 23:43
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 26/05/17 23:43
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.br.jobup.R;
import com.br.jobup.models.servico.Servico;
import com.br.jobup.models.usuario.Usuario;

import java.util.List;

/**
 * Created by luizramos on 26/05/17.
 */

public class UltimosServicosAdapter extends BaseAdapter {
    private final List<Servico> servicos;
    private final Context context;

    public UltimosServicosAdapter(Context context, List<Servico> servicos) {

        this.servicos = servicos;
        this.context = context;
    }
    @Override
    public int getCount() {
        return servicos.size();
    }

    @Override
    public Object getItem(int position) {
        return servicos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Servico servico = servicos.get(position);

        View view = LayoutInflater.from(this.context).inflate(R.layout.lista_ultimos_servicos, null);
        TextView txtQuando = (TextView) view.findViewById(R.id.ultimosServicosQuando);
        TextView txtObs = (TextView) view.findViewById(R.id.ultimosServicosObservacoes);

//        txtObs.setText(servico.);



        return null;
    }
}
