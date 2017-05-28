package com.br.jobup.adapters;

/*
 * Created by Luiz Carlos Ramos on 27/05/17 00:40
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 27/05/17 00:40
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.br.jobup.R;
import com.br.jobup.models.usuario.PerfilProfissional;

import java.util.List;

/**
 * Created by luizramos on 27/05/17.
 */

public class OutrasEspecializacoesAdapter extends BaseAdapter {

    private final List<PerfilProfissional> perfis;
    private final Context context;

    public OutrasEspecializacoesAdapter(Context context, List<PerfilProfissional> perfis) {
        this.context = context;
        this.perfis = perfis;
    }

    @Override
    public int getCount() {
        return perfis.size();
    }

    @Override
    public Object getItem(int position) {
        return perfis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PerfilProfissional perfil = perfis.get(position);

        View v = LayoutInflater.from(this.context).inflate(R.layout.lista_outras_especialidades, null);
        TextView txtEspecialidade = (TextView) v.findViewById(R.id.outrasEspecialidadesEspecialidade);
        TextView txtResumo = (TextView) v.findViewById(R.id.outrasEspecialidadesCurriculo);

        txtEspecialidade.setText(perfil.getEspecialidade().getDescricao());
        txtResumo.setText(perfil.getResumoCurriculo());

        return v;
    }
}
