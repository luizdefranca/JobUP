package activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.br.jobup.R;

/*
 * Created by Luiz Carlos Ramos on 09/05/17 19:02
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 09/05/17 18:46
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
