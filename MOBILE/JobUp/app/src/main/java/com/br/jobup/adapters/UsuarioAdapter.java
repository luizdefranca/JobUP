package com.br.jobup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.jobup.R;
import com.br.jobup.models.Usuario;

import java.util.List;

/**
 * Created by luizramos on 22/04/17.
 */

public class UsuarioAdapter extends BaseAdapter {

    private final List<Usuario> usuarios;

    private final Context context;



    public UsuarioAdapter(Context context, List<Usuario> usuarios) {

        this.usuarios = usuarios;
        this.context = context;
    }


    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarios.get(position);
    }

    @Override
    public long getItemId(int position) {


        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.activity_list_usuario_adapter,
                parent, false);
        ImageView img = (ImageView) view.findViewById(R.id.image_usuario_adapter);
        TextView usuario = (TextView) view.findViewById(R.id.txt_usuario_adapter);
        //TODO Setar uma imagem para o Adapter

        //img.setImageBitmap();


        return view;
    }
}
