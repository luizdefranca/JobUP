package activities;

import android.app.LoaderManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.br.jobup.R;
import com.br.jobup.adapters.UsuarioAdapter;
import com.br.jobup.dao.usuario.IUsuarioDao;
import com.br.jobup.dao.usuario.UsuarioDao;
import com.br.jobup.models.usuario.Usuario;

import java.util.List;

/*
 * Created by Luiz Carlos Ramos on 12/05/17 02:34
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 09/05/17 19:03
 */

public class CatalogoEspecialidadeActivity extends AppCompatActivity {


    private ListView mListCatalogoEspecialidade;
    private LoaderManager loaderManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mListCatalogoEspecialidade = (ListView) findViewById(R.id.catalogo_especialidade_lstView);
        int idCategoria = 0;
        getIntent().getIntExtra("idCategoria", idCategoria);
        loaderManager = getLoaderManager();

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaCatalogoEspecialidade();
    }

    private void carregaCatalogoEspecialidade() {


        IUsuarioDao dao = new UsuarioDao(this);
        List<Usuario> usuarios = dao.getAllUsuarios();
        dao.close();
        UsuarioAdapter adapter = new UsuarioAdapter(this, usuarios);
        mListCatalogoEspecialidade.setAdapter(adapter);
    }
}
