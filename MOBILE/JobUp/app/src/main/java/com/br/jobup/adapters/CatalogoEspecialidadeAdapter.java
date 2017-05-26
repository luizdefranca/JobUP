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
import com.br.jobup.util.Parsers;

import org.w3c.dom.Text;

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
        TextView txtNome                = (TextView) view.findViewById(R.id.item_especialidade_txtNome);
//        TextView txtDtNascimento        = (TextView) view.findViewById(R.id.item_especialidade_txtDtNascimento);
        TextView txtDescEspecialidade   = (TextView) view.findViewById(R.id.item_especialidade_txtDescricao);
        TextView txtResumoCurriculo     = (TextView) view.findViewById(R.id.item_especialidade_txtCurriculo);
//        TextView txtDtInclusao          = (TextView) view.findViewById(R.id.item_especialidade_txtUsuarioDesde) ;
        TextView txtBairro              = (TextView) view.findViewById(R.id.item_especialidade_txtBairro);
        TextView txtCidade              = (TextView) view.findViewById(R.id.item_especialidade_txtCidade) ;
        TextView txtEstado              = (TextView) view.findViewById(R.id.item_especialidade_txtEstado) ;


        txtNome.setText(especialidade.getNome());
//        txtDtNascimento.setText(Parsers.parseDataToStringNormal(especialidade.getDtNascimento()));
        txtDescEspecialidade.setText(especialidade.getDescEspecialidade());
        txtResumoCurriculo.setText((especialidade.getResumoCurriculo()));
//        txtDtInclusao.setText(Parsers.parseDataToStringNormal(especialidade.getDtInclusao()));
        txtBairro.setText(especialidade.getBairro());
        txtCidade.setText(especialidade.getCidade());
        txtEstado.setText(especialidade.getEstado());
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
//        TextView txtDtNascimento;
        TextView txtDescEspecialidade;
        TextView txtResumoCurriculo;
//        TextView txtDtInclusao;
        TextView txtBairro;
        TextView txtCidade;
        TextView txtEstado;
    }
}