package com.br.jobup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.br.jobup.R;
import com.br.jobup.models.especialidade.EspecialidadeCatalogo;

import java.util.List;





/*
 * Created by Luiz Carlos Ramos on 12/05/17 02:23
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 08/05/17 08:31
 */


public class CatalogoEspecialidadeAdapter extends BaseAdapter {

    private final List<EspecialidadeCatalogo> especialidadesList;

    private final Context context;

    public CatalogoEspecialidadeAdapter(Context context, List<EspecialidadeCatalogo> especialidadesCatalogo) {
        this.especialidadesList = especialidadesCatalogo;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EspecialidadeCatalogo especialidade = especialidadesList.get(position);


        View view = LayoutInflater.from(this.context).inflate(R.layout.item_especialidade_catalogo, null);
        TextView txtNome = (TextView) view.findViewById(R.id.item_especialidade_txtNome);
        TextView txtBairro = (TextView) view.findViewById(R.id.item_especialidade_txtBairro);
        RatingBar rtbEstrelas = (RatingBar) view.findViewById(R.id.item_especialidade_rtbEstrelas);
        TextView txtDescEspecialidade = (TextView) view.findViewById(R.id.item_especialidade_txtDescricao);
        TextView txtResumoCurriculo = (TextView) view.findViewById(R.id.item_especialidade_txtCurriculo);


        txtNome.setText(especialidade.getNome());
//        txtBairro.setText(especialidade.getBairro());
        txtResumoCurriculo.setText((especialidade.getResumoCurriculo()));
//        rtbEstrelas.setRating(especialidade.getRating());
        txtDescEspecialidade.setText(especialidade.getDescEspecialidade());
        return view;
    }

    @Override
    public int getCount() {
        return especialidadesList.size();
    }

    @Override
    public Object getItem(int position) {
        return especialidadesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder {
        //views
        TextView txtNome;
        TextView txtBairro;
        RatingBar rtbEstrelas;
        TextView txtDescEspecialidade;
        TextView txtResumoCurriculo;

    }
}