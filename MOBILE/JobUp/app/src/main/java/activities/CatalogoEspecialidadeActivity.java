package activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
<<<<<<< HEAD
import android.util.Log;
=======
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.br.jobup.R;
import com.br.jobup.adapters.CatalogoEspecialidadeAdapter;
<<<<<<< HEAD
import com.br.jobup.fragments.DetalheProfissionalViewPagerActivity;
import com.br.jobup.models.especialidade.EspecialidadeCatalogo;
=======
import com.br.jobup.models.especialidade.EspecialidadeCatalogo;
import com.br.jobup.models.usuario.Usuario;
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae
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
 */import static android.R.attr.key;
<<<<<<< HEAD
import static android.R.attr.logo;
=======
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae
import static android.R.attr.value;

public class CatalogoEspecialidadeActivity extends AppCompatActivity {

<<<<<<< HEAD
    public static final String TAG = CatalogoEspecialidadeActivity.class.getSimpleName();
    public static final String ID_ESPECIALIDADE = "idEspecialidade";
    public static final String DETALHE_PROFISSIONAL = "detalheProfissional";
    public static final String ID_PROFISSIONAL = "idProfissional";
    public static final String ESPECIALIDADE_DETALHE = "especialidadeDetalhe";
=======

>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae
    private ListView mListCatalogoEspecialidade;
    int idCategoria = 0;
    CatalogoEspecialidadeAdapter catalogoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_especialidade);
<<<<<<< HEAD

        //TODO: Ver como implementar o BackButton sem destruir a Activity
        // Set Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Set Title on the ActionBar
        getSupportActionBar().setTitle("Profissionais por Especialidade");

        mListCatalogoEspecialidade = (ListView) findViewById(R.id.catalogo_especialidade_lstView);

        //Recupera o valor do idCAtegoria
        if(savedInstanceState != null) {
            Log.e(TAG, "onCreate antes: idCategoria -> " + idCategoria);
            idCategoria = savedInstanceState.getInt(ID_ESPECIALIDADE);
        } else{
            idCategoria = getIntent().getIntExtra(ID_ESPECIALIDADE, idCategoria);
        }
        Log.e(TAG, "onCreate depois: idCategoria -> "+ idCategoria );
=======
        mListCatalogoEspecialidade = (ListView) findViewById(R.id.catalogo_especialidade_lstView);

        idCategoria = getIntent().getIntExtra("idEspecialidade", idCategoria);


        mListCatalogoEspecialidade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final CharSequence[] itens = getResources().getStringArray(R.array.especialidade_opcoes);
                AlertDialog.Builder builder = new AlertDialog.Builder(CatalogoEspecialidadeActivity.this);
                builder.setTitle("Escolha a opção");
                builder.setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }


//TODO: VER COMO RETIRAR ESTE METODO DAQUI
                  /*  @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which){
                            case 0:
                                final Intent intentDetalheUsuario = new Intent(CatalogoEspecialidadeActivity.this, PerfilActivity.class);

//                              Pega a especilidade selecionada
                                final EspecialidadeCatalogo especialidadeDetalhe = (EspecialidadeCatalogo) mListCatalogoEspecialidade.getItemAtPosition(position);

//                              Pega o id do Profissional da especialidade selecionada
                                String idProfissinal = especialidadeDetalhe.getIdUsuario();
                                Bundle baseBundle = new Bundle();
                                baseBundle.putString("idProfissional", idProfissinal );
                                intentDetalheUsuario.putExtras(baseBundle);
                                startActivity(intentDetalheUsuario);
                                break;
                            case 1:
                                //TODO: ADICIONAR A FAVORITOS
                                break;
                            case 2:
                                //TODO: CONTRATAR SERVICO
                                break;
                            case 3:
                                //TODO: fechar o AlertDialog
                                try {
                                    this.finalize();
                                } catch (Throwable throwable) {
                                    throwable.printStackTrace();
                                }

                                break;


                        }
                    }*/
                });
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }


        });

>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae
        mListCatalogoEspecialidade.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {

                final CharSequence[] itens = getResources().getStringArray(R.array.especialidade_opcoes);
                AlertDialog.Builder builder = new AlertDialog.Builder(CatalogoEspecialidadeActivity.this);
                builder.setTitle("Escolha a opção");
                builder.setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which){
<<<<<<< HEAD

                            //Ver detalhe do Profissional
                            case 0:
                                final Intent intentDetalheUsuario = new Intent(CatalogoEspecialidadeActivity.this, DetalheProfissionalViewPagerActivity.class);
=======
                            case 0:
                                final Intent intentDetalheUsuario = new Intent(CatalogoEspecialidadeActivity.this, PerfilActivity.class);
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae

//                              Pega a especilidade selecionada
                                final EspecialidadeCatalogo especialidadeDetalhe = (EspecialidadeCatalogo) mListCatalogoEspecialidade.getItemAtPosition(position);

//                              Pega o id do Profissional da especialidade selecionada
                                String idProfissinal = especialidadeDetalhe.getIdUsuario();
                                Bundle baseBundle = new Bundle();
<<<<<<< HEAD
                                baseBundle.putString(ID_PROFISSIONAL, idProfissinal );
                                baseBundle.putInt(ID_ESPECIALIDADE, idCategoria);
                                baseBundle.putSerializable(ESPECIALIDADE_DETALHE, especialidadeDetalhe );
                                intentDetalheUsuario.putExtra(DETALHE_PROFISSIONAL, baseBundle);
                                startActivity(intentDetalheUsuario);
                                break;

=======
                                baseBundle.putString("idProfissional", idProfissinal );
                                intentDetalheUsuario.putExtras(baseBundle);
                                startActivity(intentDetalheUsuario);
                                break;
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae
                            case 1:
                                //TODO: ADICIONAR A FAVORITOS
                                break;
                            case 2:
                                //TODO: CONTRATAR SERVICO
                                break;
                            case 3:
                                //TODO: fechar o AlertDialog
                                try {
                                    this.finalize();
                                } catch (Throwable throwable) {
                                    throwable.printStackTrace();
                                }

                                break;
<<<<<<< HEAD
=======


>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae
                        }
                    }
                });
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }



        });
<<<<<<< HEAD

        Log.e("LCFR " + TAG, "onCreate: " );
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregaCatalogoEspecialidade();
        Log.e("LCFR " + TAG, "onStart: -> "+ idCategoria );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregaCatalogoEspecialidade();
        Log.e("LCFR " + TAG, "onRestart:  -> "+ idCategoria );
    }

    //Faz a chamada à API e carrega a ListView
    private void carregaCatalogoEspecialidade() {
        Log.e("LCFR " + TAG, "Entrada no método carregaCatalogoEspecialidade: " );
=======
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaCatalogoEspecialidade();
    }

    private void carregaCatalogoEspecialidade() {
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae

        final ParserEspecialidadeCatalogo parser = new ParserEspecialidadeCatalogo(idCategoria);
        parser.getAll().enqueue(new Callback<List<EspecialidadeCatalogo>>() {
            @Override
            public void onResponse(Call<List<EspecialidadeCatalogo>> call, Response<List<EspecialidadeCatalogo>> response) {
                List<EspecialidadeCatalogo> especialidadeList = response.body();
                catalogoAdapter = new CatalogoEspecialidadeAdapter(CatalogoEspecialidadeActivity.this, especialidadeList);
                mListCatalogoEspecialidade.setAdapter(catalogoAdapter);
<<<<<<< HEAD
                Log.e("LCFR " + TAG, "Chamada do método onResponse: " );
=======
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae
            }

            @Override
            public void onFailure(Call<List<EspecialidadeCatalogo>> call, Throwable t) {

            }
        });

<<<<<<< HEAD

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        Log.e(TAG, "onSaveInstanceState: " + idCategoria );
        outState.putInt(ID_ESPECIALIDADE, idCategoria);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        idCategoria = savedInstanceState.getInt(ID_ESPECIALIDADE);
        Log.e(TAG, "onRestoreInstanceState: " + idCategoria );
=======
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae
    }
}
