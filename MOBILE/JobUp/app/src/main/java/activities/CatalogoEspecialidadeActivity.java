package activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.br.jobup.R;
import com.br.jobup.adapters.CatalogoEspecialidadeAdapter;
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
 */import static android.R.attr.key;
import static android.R.attr.value;

public class CatalogoEspecialidadeActivity extends AppCompatActivity {


    private ListView mListCatalogoEspecialidade;
    int idCategoria = 0;
    CatalogoEspecialidadeAdapter catalogoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_especialidade);
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

        mListCatalogoEspecialidade.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {

                final CharSequence[] itens = getResources().getStringArray(R.array.especialidade_opcoes);
                AlertDialog.Builder builder = new AlertDialog.Builder(CatalogoEspecialidadeActivity.this);
                builder.setTitle("Escolha a opção");
                builder.setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which){

                            //Ver detalhe do Profissional
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


//                            // Pass adObj to the other Activity
//                            ParseObject adObj = adsArray.get(position);
//                            Intent i = new Intent(BrowseAds.this, ShowSingleAd.class);
//                            i.putExtra("objectID", adObj.getObjectId());
//                            startActivity(i);
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
                    }
                });
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }



        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaCatalogoEspecialidade();
    }


    //
    private void carregaCatalogoEspecialidade() {



        final ParserEspecialidadeCatalogo parser = new ParserEspecialidadeCatalogo(idCategoria);
        parser.getAll().enqueue(new Callback<List<EspecialidadeCatalogo>>() {
            @Override
            public void onResponse(Call<List<EspecialidadeCatalogo>> call, Response<List<EspecialidadeCatalogo>> response) {
                List<EspecialidadeCatalogo> especialidadeList = response.body();
                catalogoAdapter = new CatalogoEspecialidadeAdapter(CatalogoEspecialidadeActivity.this, especialidadeList);
                mListCatalogoEspecialidade.setAdapter(catalogoAdapter);
            }

            @Override
            public void onFailure(Call<List<EspecialidadeCatalogo>> call, Throwable t) {

            }
        });

    }
}
