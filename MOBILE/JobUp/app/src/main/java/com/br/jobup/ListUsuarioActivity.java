package com.br.jobup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
//        List<Usuario> usuarios = dao.getAllUsuarios();
//        dao.close();
//        UsuarioAdapter adapter = new UsuarioAdapter(this, usuarios);
//        mListUsuarios.setAdapter(adapter);
//    }

}
