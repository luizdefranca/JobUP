package com.br.jobup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.br.jobup.R;
import com.br.jobup.models.servico.Proposta;

import java.util.List;

/**
 * Created by Renevalda Maria on 17/06/2017.
 */

public class PropostaAdapter extends BaseAdapter {

    private final List<Proposta> propostas;
    private final Context context;

    public PropostaAdapter(Context context, List<Proposta> propostas) {

        this.propostas = propostas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return propostas.size();
    }

    @Override
    public Object getItem(int position) {
        return propostas.get(position);
    }

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.activity_list_proposta_adapter, parent, false);
        TextView proposta = (TextView) view.findViewById(R.id.txt_proposta_adapter);
        return view;
    }
}
