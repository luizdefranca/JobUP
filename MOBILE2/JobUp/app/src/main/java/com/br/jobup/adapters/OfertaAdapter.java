package com.br.jobup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.br.jobup.R;
import com.br.jobup.models.servico.Oferta;

import java.util.List;

/**
 * Created by Renevalda Maria on 17/06/2017.
 */

public class OfertaAdapter extends BaseAdapter {

    private final List<Oferta> ofertas;
    private final Context context;

    public OfertaAdapter(Context context, List<Oferta> ofertas) {

        this.ofertas = ofertas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ofertas.size();
    }

    @Override
    public Object getItem(int position) {
        return ofertas.get(position);
    }

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.activity_list_oferta_adapter, parent, false);
        TextView oferta = (TextView) view.findViewById(R.id.txt_oferta_adapter);
        return view;
    }
}
