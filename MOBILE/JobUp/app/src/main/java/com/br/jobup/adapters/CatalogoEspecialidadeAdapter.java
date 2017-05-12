package com.br.jobup.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.br.jobup.R;
import com.br.jobup.models.especialidade.EspecialidadeCatalogo;
import com.br.jobup.models.usuario.Usuario;

import java.util.List;





/*
 * Created by Luiz Carlos Ramos on 12/05/17 02:23
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 08/05/17 08:31
 */


public class CatalogoEspecialidadeAdapter extends BaseAdapter {

    private final List<EspecialidadeCatalogo> especialidadeCatalogo;

    private final Context context;

    public CatalogoEspecialidadeAdapter(Context context, List<EspecialidadeCatalogo> especialidadesCatalogo) {
        this.especialidadeCatalogo = especialidadesCatalogo;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.activity_catalogo_especialidade,
                parent, false);
        TextView txtNome = (TextView) view.findViewById(R.id.item_especialidade_txtNome);
        TextView txtBairro = (TextView) view.findViewById(R.id.item_especialidade_txtBairro);
        RatingBar rtbEstrelas = (RatingBar) view.findViewById(R.id.item_especialidade_rtbEstrelas);
        return view;
    }
    @Override
    public int getCount() {
        return especialidadeCatalogo.size();
    }

    @Override
    public Object getItem(int position) {
        return especialidadeCatalogo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}