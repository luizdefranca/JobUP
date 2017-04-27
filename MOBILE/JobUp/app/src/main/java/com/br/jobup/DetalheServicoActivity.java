package com.br.jobup;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.br.jobup.adapters.UsuarioAdapter;
import com.br.jobup.dao.usuario.IUsuarioDao;
import com.br.jobup.dao.usuario.UsuarioDao;
import com.br.jobup.models.Usuario;

import java.util.List;

public class DetalheServicoActivity extends AppCompatActivity {


    private ListView mListUsuarios;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mListUsuarios = (ListView) findViewById(R.id.ListViewUsuario);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        carregaListaUsuarios();
    }

    private void carregaListaUsuarios(){
        IUsuarioDao dao = new UsuarioDao(this);
        List<Usuario> usuarios = dao.getAllUsuarios();
        dao.close();
        UsuarioAdapter adapter = new UsuarioAdapter(this, usuarios);
        mListUsuarios.setAdapter(adapter);
    }
}
