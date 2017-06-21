package com.br.jobup.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.br.jobup.R;
import com.br.jobup.adapters.CatalogoEspecialidadeAdapter;
import com.br.jobup.fragments.DetalheProfissionalViewPagerActivity;
import com.br.jobup.models.especialidade.ServicoOferta;
import com.br.jobup.models.usuario.Usuario;
import com.br.jobup.preferencesPersistence.PreferencePersistence;
import com.br.jobup.services.parsers.ParserEspecialidadeCatalogo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Created by Luiz Carlos Ramos on 6/7/17 12:01 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/6/17 6:54 PM
 */

public class CatalogoEspecialidadeActivity extends AppCompatActivity {

    public static final String TAG = CatalogoEspecialidadeActivity.class.getSimpleName();
    public static final String ID_ESPECIALIDADE = "idEspecialidade";
    public static final String DETALHE_PROFISSIONAL = "detalheProfissional";
    public static final String ID_PROFISSIONAL = "idProfissional";
    public static final String ESPECIALIDADE_DETALHE = "especialidadeDetalhe";
    public static final String ID_USUARIO = "idUsuario";
    public static final String DETALHE_CONTRATAR_SERVICO = "DetalheContratarServico";
    public static final String NOME_PROFISSIONAL = "nomeProfissional";
    public static final String LISTA_DE_ESPECIALIDADES = "LISTA_DE_ESPECIALIDADES";
    private List<ServicoOferta> especialidadeList;
    private ListView mListCatalogoEspecialidade;
    int idCategoria = 0;
    private String idUsuario;
    private String idProfissinal;
    private String nomeProfissional;
    CatalogoEspecialidadeAdapter catalogoAdapter;
    private RadioGroup rgFiltros;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_especialidade);

        // Set Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Set Title on the ActionBar
        getSupportActionBar().setTitle("Profissionais");

        //Inicializa o RadioGroup
        rgFiltros = (RadioGroup) findViewById(R.id.rd_group_ordenacao);

        rgFiltros.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int botao) {

                switch (botao){
                    //Opcao de ordernar por avaliacao
                    case R.id.rd_bt_avaliacao:
                        if(especialidadeList != null){
                            ordenaPorAvaliacao(especialidadeList);
                            catalogoAdapter = new CatalogoEspecialidadeAdapter(CatalogoEspecialidadeActivity.this, especialidadeList);
                            mListCatalogoEspecialidade.setAdapter(catalogoAdapter);
                            catalogoAdapter.notifyDataSetChanged();
                        }
                        break;
                    //Opcao de ordernar por avaliacao
                    case R.id.rd_bt_nServicos:
                        if(especialidadeList != null){
                            ordenaPorNumeroServicos(especialidadeList);
                            catalogoAdapter = new CatalogoEspecialidadeAdapter(CatalogoEspecialidadeActivity.this, especialidadeList);
                            mListCatalogoEspecialidade.setAdapter(catalogoAdapter);
                            catalogoAdapter.notifyDataSetChanged();
                        }
                        break;
                }
            }
        });

        //Inicializa a ListView
        mListCatalogoEspecialidade = (ListView) findViewById(R.id.catalogo_especialidade_lstView);

        //Recupera o valor do idCAtegoria
        if (savedInstanceState != null) {
            Log.e(TAG, "onCreate antes: idCategoria -> " + idCategoria);
            idCategoria = savedInstanceState.getInt(ID_ESPECIALIDADE);
        } else {
            idCategoria = getIntent().getIntExtra(ID_ESPECIALIDADE, idCategoria);
        }
        Log.e(TAG, "onCreate depois: idCategoria -> " + idCategoria);


        //Seta o subtitle na ActionBar para o da categoria
        getSupportActionBar().setSubtitle(pegaNomeDaCategoriaPeloId(idCategoria));

        //Cria o evento de click longo na ListView
        mListCatalogoEspecialidade.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {

                //Cria um Alerta Dialog com um menu de opcoes
                final CharSequence[] itens = getResources().getStringArray(R.array.especialidade_opcoes);
                AlertDialog.Builder builder = new AlertDialog.Builder(CatalogoEspecialidadeActivity.this);
                builder.setTitle("Escolha a opção");
                builder.setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Pega a especilidade selecionada
                        final ServicoOferta especialidadeDetalhe = (ServicoOferta) mListCatalogoEspecialidade.getItemAtPosition(position);

//                              Pega o id do Profissional da especialidade selecionada
                        idProfissinal = especialidadeDetalhe.getIdUsuario();
                        nomeProfissional = especialidadeDetalhe.getNome();
                        switch (which) {

                            //Ver detalhe do Profissional
                            case 0:
                                final Intent intentDetalheUsuario = new Intent(CatalogoEspecialidadeActivity.this, DetalheProfissionalViewPagerActivity.class);

//
                                Bundle baseBundle = new Bundle();
                                baseBundle.putString(ID_PROFISSIONAL, idProfissinal);
                                baseBundle.putInt(ID_ESPECIALIDADE, idCategoria);
                                baseBundle.putSerializable(ESPECIALIDADE_DETALHE, especialidadeDetalhe);
                                intentDetalheUsuario.putExtra(DETALHE_PROFISSIONAL, baseBundle);
                                startActivity(intentDetalheUsuario);
                                break;

                            case 1:
                                //TODO: AVALIAR PROFISSIONAL

                                final Intent intentAvaliacao = new Intent(CatalogoEspecialidadeActivity.this, AvaliacaoActivity.class);
                                intentAvaliacao.putExtra("ID_PROFISSIONAL", idProfissinal);
<<<<<<< HEAD
                                intentAvaliacao.putExtra("ID_ESPECIALIDADE", idCategoria);
=======
                                intentAvaliacao.putExtra("ID_ESPECIALIDADE",idCategoria);
>>>>>>> origin/master
                                startActivity(intentAvaliacao);






                                break;
                            case 2:
                                //TODO: CONTRATAR SERVICO

                                //Pega informaçoes do usuario corrente nas preferencias
                                //com.br.jobup.models.usuario.Usuario
                                final PreferencePersistence<Usuario> persistence = new PreferencePersistence<>(CatalogoEspecialidadeActivity.this);
                                final Usuario usuarioCorrente = persistence.getObjectSavedInPreferences("UsuarioCorrent",
                                        "com.br.jobup.models.usuario.Usuario");

                                //Cria um novo Intent e passa informacoes do
                                //idUsuario, IdProfissional, IdEspecialidade por um Bundle
                                //
                                final Intent intent = new Intent(CatalogoEspecialidadeActivity.this, OfertarServicoActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString(ID_USUARIO, usuarioCorrente.idUsuario);
                                bundle.putString(ID_PROFISSIONAL, idProfissinal);
                                bundle.putInt(ID_ESPECIALIDADE, idCategoria);
                                bundle.putString(NOME_PROFISSIONAL, nomeProfissional);
                                intent.putExtra(DETALHE_CONTRATAR_SERVICO, bundle);

                                startActivity(intent);
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
                    }
                });
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }


        });

        Log.e("LCFR " + TAG, "onCreate: ");
        //Fim do método onCreate
    }


    //METODOS DA ACTION BAR
    //Infla o Menu com os botoes da action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_catalogo_especialidades, menu);
        return true;
    }

    //Define os métodos para o RadioGroup
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_mapa) {

            Intent intent = new Intent(CatalogoEspecialidadeActivity.this, MapaActivity.class);

            ArrayList<ServicoOferta> arrayListEspecialidades = new ArrayList<>(especialidadeList);
            intent.putParcelableArrayListExtra(LISTA_DE_ESPECIALIDADES, arrayListEspecialidades);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    //FIM DOS MÉTODOS DA ACTION BAR

    @Override
    protected void onStart() {
        super.onStart();
        carregaCatalogoEspecialidade();
        Log.e("LCFR " + TAG, "onStart: -> " + idCategoria);
        getBaseContext();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        carregaCatalogoEspecialidade();
        Log.e("LCFR " + TAG, "onRestart:  -> " + idCategoria);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        Log.e(TAG, "onSaveInstanceState: " + idCategoria);
        outState.putInt(ID_ESPECIALIDADE, idCategoria);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        idCategoria = savedInstanceState.getInt(ID_ESPECIALIDADE);
        Log.e(TAG, "onRestoreInstanceState: " + idCategoria);
    }

    protected String pegaNomeDaCategoriaPeloId(int idCategoria) {
        final Map<Integer, String> categoriaMap = new HashMap<>();
        categoriaMap.put(2, "Adestrador de cães");
        categoriaMap.put(3, "Babá");
        categoriaMap.put(4, "Cozinheira");
        categoriaMap.put(5, "Diarista");
        categoriaMap.put(6, "Limpeza de piscina");
        categoriaMap.put(7, "Motorista");
        categoriaMap.put(8, "Passadeira");
        categoriaMap.put(9, "Passeador de cães");

        String categoria = categoriaMap.get(idCategoria);
        return categoria;
    }

    //Faz a chamada à API e carrega a ListView
    private void carregaCatalogoEspecialidade() {
        Log.e("LCFR " + TAG, "Entrada no método carregaCatalogoEspecialidade: ");

        final ParserEspecialidadeCatalogo parser = new ParserEspecialidadeCatalogo(idCategoria);
        parser.getAll().enqueue(new Callback<List<ServicoOferta>>() {
            @Override
            public void onResponse(Call<List<ServicoOferta>> call, Response<List<ServicoOferta>> response) {
                especialidadeList = response.body();
                ordenaPorAvaliacao(especialidadeList);
                catalogoAdapter = new CatalogoEspecialidadeAdapter(CatalogoEspecialidadeActivity.this, especialidadeList);
                mListCatalogoEspecialidade.setAdapter(catalogoAdapter);
                Log.e("LCFR " + TAG, "Chamada do método onResponse: ");
            }

            @Override
            public void onFailure(Call<List<ServicoOferta>> call, Throwable t) {

            }
        });


    }

    private void ordenaPorAvaliacao(List<ServicoOferta> lista) {

        Collections.sort(lista, new Comparator<ServicoOferta>() {
            @Override
            public int compare(ServicoOferta s1, ServicoOferta s2) {
                if (s1.mediaAvaliacoes > s2.mediaAvaliacoes)
                    return -1;
                else  if (s1.mediaAvaliacoes < s2.mediaAvaliacoes)
                        return 1;
                else return 0;

            }
        });
    }

    private void ordenaPorNumeroServicos(List<ServicoOferta> lista) {

        Collections.sort(lista, new Comparator<ServicoOferta>() {
            @Override
            public int compare(ServicoOferta s1, ServicoOferta s2) {

                if (s1.qtPropostasAceitas > s2.qtPropostasAceitas)
                    return -1;
                else  if (s1.qtPropostasAceitas < s2.qtPropostasAceitas)
                    return 1;
                else return 0;

            }
        });
    }
}
