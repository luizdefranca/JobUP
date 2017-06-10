package com.br.jobup.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.br.jobup.R;

/*
 * Created by Luiz Carlos Ramos on 6/7/17 12:02 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 5/16/17 3:49 AM
 */

public class ListUsuarioActivity extends AppCompatActivity {

//    private ListView mListUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_usuario_adapter);

//        mListUsuarios = (ListView) findViewById(R.id.ListViewUsuario);

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        carregaListaUsuarios();
//    }
//
//    private void carregaListaUsuarios(){
//        IUsuarioDao dao = new UsuarioDao(this);
//        List<Usuario> usuarios = dao.getAll();
//        dao.close();
//        UsuarioAdapter adapter = new UsuarioAdapter(this, usuarios);
//        mListUsuarios.setAdapter(adapter);
//    }

}
