package com.br.jobup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.br.jobup.R;
import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.util.Parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Created by Luiz Carlos Ramos on 6/15/17 8:25 PM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/8/17 1:55 PM
 */

/**
 * Created by luizramos on 08/06/17.
 */

public class ServicoPrivadoClienteSemPropostaAdapter extends BaseAdapter {

    private final List<ServicoOfertaPrivada> servicoOfertaPrivadas;
    private final Context context;


    public ServicoPrivadoClienteSemPropostaAdapter(Context context, List<ServicoOfertaPrivada> servicoOfertaPrivadas) {
        this.servicoOfertaPrivadas = servicoOfertaPrivadas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return servicoOfertaPrivadas.size();
    }

    @Override
    public Object getItem(int position) {
        return servicoOfertaPrivadas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ServicoOfertaPrivada servico = servicoOfertaPrivadas.get(position);

        View view = LayoutInflater.from(this.context).inflate(R.layout.row_servico_privado_cliente_sem_proposta,
                parent, false);

        TextView txtDesEspecialidade = (TextView) view.findViewById(R.id.text_view_des_especialidade);
        TextView txtTitulo = (TextView) view.findViewById(R.id.text_view_titulo);
        TextView txtObservacao = (TextView) view.findViewById(R.id.text_view_observacao);
        TextView txtValor = (TextView) view.findViewById(R.id.text_view_valor_sugerido);
        TextView txtDtProposta = (TextView) view.findViewById(R.id.text_view_dt_proposta);

        txtDesEspecialidade.setText(getDescricaoEspecialidadePorId(servico.getIdEspecialidade()));
        txtObservacao.setText(servico.getObservacoes());
        txtTitulo.setText(servico.getTitulo());
        String valor = String.format("%.2f", servico.getValorSugerido());
        txtValor.setText("R$ " + String.valueOf(valor));
        txtDtProposta.setText(Parsers.parseDataToStringNormal(servico.getDtCadastro()));
        return view;
    }


    private String getDescricaoEspecialidadePorId(int idEspecialidade) {

        final Map<Integer, String> categoriaMap = new HashMap<>();
        categoriaMap.put(2, "Adestrador de cães");
        categoriaMap.put(3, "Babá");
        categoriaMap.put(4, "Cozinheira");
        categoriaMap.put(5, "Diarista");
        categoriaMap.put(6, "Limpeza de piscina");
        categoriaMap.put(7, "Motorista");
        categoriaMap.put(8, "Passadeira");
        categoriaMap.put(9, "Passeador de cães");

        return categoriaMap.get(idEspecialidade);
    }
}
