package com.br.jobup.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.br.jobup.R;
import com.br.jobup.activities.RecusaPropostaDialogFragment;
import com.br.jobup.models.servico.Proposta;
import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.models.usuario.Avaliacao;
import com.br.jobup.services.parsers.ParserRejeitarServico;
import com.br.jobup.util.Parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

/*
 * Created by Luiz Carlos Ramos on 6/16/17 7:12 PM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/15/17 11:10 PM
 */

/**
 * Created by luizramos on 08/06/17.
 */

public class ServicoPrivadoClienteAdapter extends BaseAdapter {

    private final List<ServicoOfertaPrivada> servicoOfertaPrivadasComProposta;
    private final Context context;
    private Button btnAceitar;
    private Button btnRecusar;
    private Button btnAvaliar;
    private Activity activity;
    public ServicoPrivadoClienteAdapter(Context context, List<ServicoOfertaPrivada> servicoOfertaPrivadasComProposta, Activity activity) {
        this.servicoOfertaPrivadasComProposta = servicoOfertaPrivadasComProposta;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return servicoOfertaPrivadasComProposta.size();
    }

    @Override
    public Object getItem(int position) {
        return servicoOfertaPrivadasComProposta.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ServicoOfertaPrivada servico = servicoOfertaPrivadasComProposta.get(position);

        final View view = LayoutInflater.from(this.context).inflate(R.layout.row_servico_privado_cliente,
                parent, false);



        TextView txtDesEspecialidade = (TextView) view.findViewById(R.id.text_view_des_especialidade);
        TextView txtTitulo = (TextView) view.findViewById(R.id.text_view_titulo);
        TextView txtObservacao = (TextView) view.findViewById(R.id.text_view_observacao);
        TextView txtValor = (TextView) view.findViewById(R.id.text_view_valor_sugerido);
        TextView txtDtProposta = (TextView) view.findViewById(R.id.text_view_dt_proposta);
        TextView txtValorProposta = (TextView) view.findViewById(R.id.text_view_valor_proposta);
        TextView txtDuracaoServico = (TextView) view.findViewById(R.id.text_view_duracao_servico);
        TextView txtJustificativa = (TextView) view.findViewById(R.id.text_view_justificativa);
        btnAceitar = (Button) view.findViewById(R.id.btn_aceitar);
        btnRecusar = (Button) view.findViewById(R.id.btn_recusar);
        btnAvaliar = (Button) view.findViewById(R.id.btn_avaliar);

        if (getProposta(servico).getAceita()) {
            btnAceitar.setVisibility(View.GONE);
            btnRecusar.setVisibility(View.GONE);
            btnAvaliar.setVisibility(View.VISIBLE);
//            btnAceitar.setEnabled(false);
        }else {
            btnAvaliar.setVisibility(View.GONE);
        }

        txtDesEspecialidade.setText(getDescricaoEspecialidadePorId(servico.getIdEspecialidade()));
        txtObservacao.setText(servico.getObservacoes());
        txtTitulo.setText(servico.getTitulo());
        String valorSugerido = String.format("%.2f", servico.getValorSugerido());
        txtValor.setText("R$ " + String.valueOf(valorSugerido));
        txtDtProposta.setText(Parsers.parseDataToStringNormal(servico.getDtCadastro()));
        String valorProposta = String.format("%.2f", getProposta(servico).getVlProposta());
        txtValorProposta.setText("R$ " + valorProposta);
        txtDuracaoServico.setText(getProposta(servico).getValorDuracaoServico()
                + " "
                + pegaDescricaoDuracaoServico(view, getProposta(servico).getDuracaoServico()));
        txtJustificativa.setText(getProposta(servico).getJustificativa());

        adicionaDialogo(servico, view);






        btnRecusar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                RecusaPropostaDialogFragment recusaFragmentDialog = new RecusaPropostaDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("ID_PROPOSTA", "id_proposta");
                recusaFragmentDialog.setArguments(bundle);

                recusaFragmentDialog.show(activity.getFragmentManager(), "RECUSAR_TAG");
//                ParserRejeitarServico parser = new ParserRejeitarServico(servico.getIdServico());
//                parser.rejeitarServico().enqueue(new Callback<Void>() {
//                    @Override
//                    public void onResponse(Call<Void> call, Response<Void> response) {
//                        if(response.isSuccessful()){
//                            mostraDialog("Serviço Recusado",
//                                    "O serviço: " + servico.getTitulo() + "foi recusado.", false);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Void> call, Throwable t) {
//                        Toast.makeText(v.getContext(), "Falha de comunicação com o servidor.", Toast.LENGTH_LONG).show();
//                    }
//                });

                btnAceitar.setVisibility(View.GONE);
                btnRecusar.setVisibility(View.GONE);

            }
        });


        btnAceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.layout.activity_avaliacao);
                builder.show();
//                mostraDialog("Serviço Aceito",
//                        "O serviço: " + servico.getTitulo() + "foi aceito.", false);
            }
        });


        btnAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.layout.activity_avaliacao);
                builder.show();
//                Intent intent = new Intent(v.getContext(), Avaliacao.class);
//                v.getContext().startActivity(intent);

            }
        });


        return view;
    }

    public void adicionaDialogo(ServicoOfertaPrivada servico, final View view) {
        final CharSequence[] opcaoAceitaRejeita = {"Aceitar proposta", "Recusar proposta"};
        final CharSequence[] opcaoAvaliar = {"Avaliar profissional"};
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        if(servico.getPropostas().get(0).getAceita()){
            builder.setItems(opcaoAvaliar, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(view.getContext(), Avaliacao.class);
                    view.getContext().startActivity(intent);
                }
            });
        } else {
            builder.setItems(opcaoAceitaRejeita, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int opcao) {
                    switch (opcao){
                        case 0:

                            break;
                        case 1:

                            break;
                    }
                }
            });
        }
    }

    private Proposta getProposta(ServicoOfertaPrivada servico) {
        final List<Proposta> propostas = servico.getPropostas();
        final Proposta proposta = propostas.get(0);
        return proposta;
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

    private String pegaDescricaoDuracaoServico(View view, int tempo) {
        final String[] descricoes = view.getResources().getStringArray(R.array.duracao_servico);
        final String descricao = descricoes[tempo];
        return descricao;
    }

    private void mostraDialog(String title, String descricao, final boolean ativaBtnAvaliacao ){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title).setMessage(descricao).setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                btnAceitar.setVisibility(View.GONE);
                btnRecusar.setVisibility(View.GONE);
                if(ativaBtnAvaliacao){
                    btnAvaliar.setVisibility(View.VISIBLE);
                }
            }
        }).show();
    }

}
