package com.br.jobup.activities;




import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.LoaderManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.jobup.R;
import com.br.jobup.dao.usuario.IUsuarioDao;
import com.br.jobup.dao.usuario.UsuarioDao;
import com.br.jobup.fragments.ServicoPrivadoClienteViewPagerActivity;
import com.br.jobup.models.usuario.Usuario;
import com.br.jobup.services.usuarioFullServices.loaders.LoaderUsuarioFullGetAll;
import com.br.jobup.services.usuarioFullServices.parsers.ParserUsuarioFull;
import com.br.jobup.util.Validations.TextHelper;
import com.github.hynra.gsonsharedpreferences.GSONSharedPreferences;
import com.github.hynra.gsonsharedpreferences.ParsingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
 * Created by Luiz Carlos Ramos on 6/7/17 12:02 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/6/17 5:07 PM
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = MainActivity.class.getSimpleName() + " LCFR";
    LoaderManager mLoaderManager;
    /* Views */

    GridView aGrid;
    TextView txtNavNomeUsuario;



    @Override
    protected void onStart() {
        super.onStart();
        ajustaNomeUsuarioNoHeader();
    }

    private void ajustaNomeUsuarioNoHeader() {
        Usuario usuarioCorrente = null;
        GSONSharedPreferences gsonSharedPrefs = new GSONSharedPreferences(this, "UsuarioCorrent");
        try {
             usuarioCorrente = (Usuario) gsonSharedPrefs.getObject(new Usuario());
        } catch (ParsingException e) {
            e.printStackTrace();
        }

        //Pega uma instância do NavigationView
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        //Pega uma instância do header do NavigationView
        final View header = mNavigationView.getHeaderView(0);
        //Cria uma instância do txtview com o nome do usuario
        txtNavNomeUsuario = (TextView) header.findViewById(R.id.navNomeUsuario);
        if(usuarioCorrente != null) {
            txtNavNomeUsuario.setText(usuarioCorrente.nome);
        } else {
            txtNavNomeUsuario.setText("Nome do Usuário");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Inicializa o TextView com o Nome do Usuario
        txtNavNomeUsuario = (TextView) findViewById(R.id.navNomeUsuario);

        //Inicializa - a ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
           setSupportActionBar(toolbar);

        mLoaderManager = getSupportLoaderManager();



        //Inicializa - Menu lateral
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


       // Init the Categories GridView
        initCatGridView();

        //Fim do método onCreate
    }


    // MARK: - SHOW CATEGORIES
    void initCatGridView() {

        // ARRAY COM AS ESPECIALIDADE


        final Map<Integer, String> categoriaMap = new HashMap<>();
        categoriaMap.put(2, "Adestrador de cães");
        categoriaMap.put(3, "Babá");
        categoriaMap.put(4, "Cozinheira");
        categoriaMap.put(5, "Diarista");
        categoriaMap.put(6, "Limpeza de piscina");
        categoriaMap.put(7, "Motorista");
        categoriaMap.put(8, "Passadeira");
        categoriaMap.put(9, "Passeador de cães");


        final List<String> catArray = new ArrayList<>(categoriaMap.values());


        // CUSTOM GRID ADAPTER
        class GridAdapter extends BaseAdapter {
            private Context context;
            public GridAdapter(Context context, List<String> objects) {
                super();
                this.context = context;
            }


            // CONFIGURE CELL
            @Override
            public View getView(int position, View cell, ViewGroup parent) {
                if (cell == null) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    cell = inflater.inflate(R.layout.cat_cell, null);
                }

                // Get category image
                ImageView catImg = (ImageView)cell.findViewById(R.id.chCatImage);

                // Remove os sinais de acentos e pega o nome da categoria
                String categoria = catArray.get(position);
                String catName = TextHelper.RemoverAcentos(categoria) ;
                String resName = "";

                if (catName.contains(" ")) {
                    String[] separated = catName.toLowerCase().split(" ");
                    resName = separated[0];
                    for(int i = 1; i < separated.length; i++){
                      resName += "_" + separated[i];
                    }

                } else {
                    resName = catName.toLowerCase();
                }
                int resID = getResources().getIdentifier(resName, "drawable", getPackageName());
                catImg.setImageResource(resID);


                // Get category's name
                TextView catTitleTxt = (TextView)cell.findViewById(R.id.chCatTitleTxt);
                catTitleTxt.setText(categoria);


                return cell;
            }

            @Override
            public int getCount() { return catArray.size(); }
            @Override
            public Object getItem(int position) { return catArray.get(position); }
            @Override
            public long getItemId(int position) { return position; }
        }


        // Init GridView and set its adapter
        GridView aGrid = (GridView) findViewById(R.id.catGridView);
        aGrid.setAdapter(new GridAdapter(MainActivity.this, catArray));

        // Set number of Columns accordingly to the device used
        float scalefactor = getResources().getDisplayMetrics().density * 120; // 120 is the cell's width
        int number = getWindowManager().getDefaultDisplay().getWidth();
        int columns = (int) ((float) number / (float) scalefactor);
        aGrid.setNumColumns(columns);

        aGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String cat = catArray.get(position);
                List <Integer>   catkeys = new ArrayList<>(categoriaMap.keySet());
                int idEspecialidade = catkeys.get(position);

                Intent i = new Intent(MainActivity.this, CatalogoEspecialidadeActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("idEspecialidade", idEspecialidade);
                i.putExtras(extras);
                startActivity(i);
            }});

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }


    //Menu da Action Bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent AgendamentoActivity = new Intent(MainActivity.this, ListaNovaDeUsuariosActivity.class);
            startActivity(AgendamentoActivity);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    //Menu do Navigation Bar
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_comprar_creditos) {
//            Intent CadastroActivity = new Intent(MainActivity.this, MapsActivity.class);
//            startActivity(CadastroActivity);
        } else if (id == R.id.nav_seja_um_profissional) {
            Intent AgendamentoActivity = new Intent(MainActivity.this, ListaNovaDeUsuariosActivity.class);
            startActivity(AgendamentoActivity);


        } else if (id == R.id.nav_minhas_ofertas_servicos) {
            Intent ListaPropostaActivity = new Intent(MainActivity.this, ListaOfertaActivity.class);
            startActivity(ListaPropostaActivity);


        //Inicializa Activity de Oferta Privada
        } else if (id == R.id.nav_meus_servicos_criados) {
            Intent ofertaPrivadaIntent = new Intent(MainActivity.this, ServicoPrivadoClienteViewPagerActivity.class);
            startActivity(ofertaPrivadaIntent);

        } else if (id == R.id.nav_ativar_destaque) {
//            Intent DetalheServicoActivity = new Intent(MainActivity.this, DetalheServicoActivity.class);
//            startActivity(DetalheServicoActivity);
            IUsuarioDao dao = new UsuarioDao(this);


            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            Usuario uu = gson.fromJson("{\"ID_USUARIO\":\"ab146703-6303-4023-b83a-1159715b9bb2\",\"NOME\":\"Realizado com Sucesso Por Luiz\",\"Cpf\":{\"NR\":\"74390110491\"},\"Rg\":{\"UF\":16,\"NR\":\"7343904\"},\"DT_NASCIMENTO\":\"2017-04-13T00:00:00\",\"DT_INCLUSAO\":\"2017-04-12T22:58:23\",\"DT_ALTERACAO\":\"2017-04-23T06:36:15\",\"DT_APROVACAO\":\"2017-04-24T23:55:06\",\"DT_ATIVACAO\":null,\"DT_ORDENACAO\":\"2017-04-12T22:58:23\",\"APROVADO\":false,\"ATIVO\":true,\"BLOQUEADO\":false,\"ENDERECO\":{\"ID_USUARIO\":\"ab146703-6303-4023-b83a-1159715b9bb2\",\"UF\":16,\"CEP\":\"51180260\",\"LOGRADOURO\":\"av Luxemburgo\",\"COMPLEMENTO\":null,\"BAIRRO\":\"Imbiribeira\",\"CIDADE\":\"Recife\"},\"CONTATO\":null,\"PERFIS_PROFISSIONAIS\":[],\"OFERTAS_SERVICO\":null,\"PROPOSTAS_SERVICO\":null}", Usuario.class);
            //   dao.addUsuario(uu, MainActivity.this);

            final Call<Usuario> usuarioCall = new ParserUsuarioFull().post(uu);
            usuarioCall.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    Log.e(TAG, "onResponse: "+ response.message() ); ;
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t );

                }
            });
            Log.e(TAG, "onNavigationItemSelected: "+ "mensagem enviada" );

        } else if (id == R.id.nav_perfil) {
            mLoaderManager.initLoader(0, null, new LoaderUsuarioFullGetAll(this));


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;




    }

    public static boolean isNetworkAvailable(Context ct) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ct.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null &&
                activeNetworkInfo.isConnectedOrConnecting();
    }


//    BindQuickStartAsyncTask.Simple<List<Usuario>> asyncTask = new BindQuickStartAsyncTask.Simple<List<Usuario>>(BindAsyncTaskType.READ_WRITE) {
//
//        List<Usuario> userList;
//
//        @Override
//        public List<Usuario> onExecute(BindQuickStartDataSource dataSource) throws Throwable {
//            userList = dataSource.getUsuarioDao().selectAll();
//
////            if (isNetworkAvailable(MainActivity.this) && userList.size() == 0) {
////                userList = QuickStartApplication.service.listUsuarios().execute().body();
////                dataSource.execute(new BindQuickStartDataSource.SimpleTransaction() {
////
////                    @Override
////                    public boolean onExecute(BindQuickStartDaoFactory daoFactory) {
////                        UsuarioDaoImpl dao = daoFactory.getUsuarioDao();
////
////                        for (Usuario item : userList) {
////                            dao.insert(item);
////                        }
////                        return true;
////                    }
////                });
////
////                return dataSource.getUsuarioDao().selectAll();
////            } else {
////                return userList;
////            }
////
//            return null;
//        }
//
//        @Override
//        public void onFinish(List<Usuario> result) {
////                mAdapter.update(result);
////                mAdapter.notifyDataSetChanged();
//        }
//    };
}
