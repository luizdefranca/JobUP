package activities;

import android.app.LoaderManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.br.jobup.R;
import com.br.jobup.adapters.CatalogoEspecialidadeAdapter;
import com.br.jobup.adapters.UsuarioAdapter;
import com.br.jobup.dao.usuario.IUsuarioDao;
import com.br.jobup.dao.usuario.UsuarioDao;
import com.br.jobup.models.especialidade.EspecialidadeCatalogo;
import com.br.jobup.models.usuario.Usuario;
import com.br.jobup.services.parsers.ParserEspecialidadeCatalogo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Created by Luiz Carlos Ramos on 12/05/17 02:34
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 09/05/17 19:03
 */

public class CatalogoEspecialidadeActivity extends AppCompatActivity {


    private ListView mListCatalogoEspecialidade;
    int idCategoria = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mListCatalogoEspecialidade = (ListView) findViewById(R.id.catalogo_especialidade_lstView);

        idCategoria = getIntent().getIntExtra("idCategoria", idCategoria);


    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaCatalogoEspecialidade();
    }

    private void carregaCatalogoEspecialidade() {

        final ParserEspecialidadeCatalogo parser = new ParserEspecialidadeCatalogo(idCategoria);
        parser.getAll(idCategoria).enqueue(new Callback<List<EspecialidadeCatalogo>>() {
            @Override
            public void onResponse(Call<List<EspecialidadeCatalogo>> call, Response<List<EspecialidadeCatalogo>> response) {
                final List<EspecialidadeCatalogo> especialidadeList = response.body();
                final CatalogoEspecialidadeAdapter catalogoAdapter =
                        new CatalogoEspecialidadeAdapter(CatalogoEspecialidadeActivity.this, especialidadeList);
                mListCatalogoEspecialidade.setAdapter(catalogoAdapter);
            }

            @Override
            public void onFailure(Call<List<EspecialidadeCatalogo>> call, Throwable t) {

            }
        });

    }
}
