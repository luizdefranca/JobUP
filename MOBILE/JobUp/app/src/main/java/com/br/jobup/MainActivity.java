package com.br.jobup;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.LoaderManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.jobup.dao.usuario.IUsuarioDao;
import com.br.jobup.dao.usuario.UsuarioDao;
import com.br.jobup.models.Login;
import com.br.jobup.models.Usuario;
import com.br.jobup.services.usuarioFullServices.loaders.LoaderUsuarioFullGetAll;
import com.br.jobup.services.usuarioFullServices.parsers.ParserLogin;
import com.br.jobup.services.usuarioFullServices.parsers.ParserUsuarioFull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.xml.sax.Parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.br.jobup.R.id.email;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = MainActivity.class.getSimpleName() + " LCFR";
    LoaderManager mLoaderManager;
    /* Views */
    EditText keywTxt;
    GridView aGrid;

    @Override
    protected void onStart() {
        super.onStart();
        keywTxt.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);


        mLoaderManager = getSupportLoaderManager();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        //Inicializa - Menu lateral
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Init TabBar buttons
        Button tab_fav = (Button) findViewById(R.id.tab_fav);
        Button tab_account = (Button) findViewById(R.id.tab_account);

        tab_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(Home.this, Favorites.class));
            }
        });

        tab_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (ParseUser.getCurrentUser().getUsername() != null) {
//                    startActivity(new Intent(Home.this, AccountScreen.class));
//                } else {
//                    startActivity(new Intent(Home.this, Login.class));
//                }
            }
        });


        // Init views
        keywTxt = (EditText) findViewById(R.id.keywordTxt);


        // MARK: - SEARCH BY KEYWORDS ------------------------------------------
        keywTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    dismisskeyboard();

                    // Pass strings for query to BrowseAds.java
//                    Intent i = new Intent(Home.this, BrowseAds.class);
//                    Bundle extras = new Bundle();
//                    extras.putString("keywordsStr", keywTxt.getText().toString().toLowerCase());
//                    i.putExtras(extras);
//                    startActivity(i);

                    return true;
                }
                return false;
            }
        });


        // Init the Categories GridView
        initCatGridView();
    }


    // MARK: - SHOW CATEGORIES
    void initCatGridView() {

        // ARRAY COM AS ESPECIALIDADE
        final String[] categoriesArray = new String[] {
                "Pintor",
                "Eletricista",
                "Encanador",
                "Chaveiro",
                "Diarista",
                "Carpinteiro",
                "Marcineiro",
                "Pets",
                "Free stuff"

                // You can add more Categories here....
        };

        final List<String> catArray = new ArrayList<String>(Arrays.asList(categoriesArray));


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

                String catName = catArray.get(position);
                String resName = "";

                if (catName.contains(" ")) {
                    String[] separated = catName.toLowerCase().split(" ");
                    separated[0].toString();
                    separated[1].toString();
                    resName = separated[0] + "_" + separated[1];
                } else {
                    resName = catName.toLowerCase();
                }
                int resID = getResources().getIdentifier(resName, "drawable", getPackageName());
                catImg.setImageResource(resID);


                // Get category's name
                TextView catTitleTxt = (TextView)cell.findViewById(R.id.chCatTitleTxt);
                catTitleTxt.setText(catName);


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

//                Intent i = new Intent(Home.this, BrowseAds.class);
//                Bundle extras = new Bundle();
//                extras.putString("categoryStr", cat);
//                i.putExtras(extras);
//                startActivity(i);
            }});

    }





    // MARK: - DISMISS KEYBOARD
    void dismisskeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(keywTxt.getWindowToken(), 0);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent AgendamentoActivity = new Intent(MainActivity.this, ListaNovaDeUsuariosActivity.class);
            startActivity(AgendamentoActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent CadastroActivity = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(CadastroActivity);
        } else if (id == R.id.nav_gallery) {
            Intent AgendamentoActivity = new Intent(MainActivity.this, ListaNovaDeUsuariosActivity.class);
            startActivity(AgendamentoActivity);
        } else if (id == R.id.nav_slideshow) {
            final Login login = new Login("luizramospe@gmail.com", "Lc1234");
            ParserLogin parse = new ParserLogin(login);
             Call<com.squareup.okhttp.Response> loginCall = parse.get();
            loginCall.enqueue(new Callback<com.squareup.okhttp.Response>() {
                @Override
                public void onResponse(Call<com.squareup.okhttp.Response> call, Response<com.squareup.okhttp.Response> response) {
                    if(response.body().code() == 200 && response.body().message().equals("Success")){
                        Log.e(TAG, "onResponse: " + "Login efetuado com sucesso" );
                    }else if(response.message()
                            .equals("You must have a confirmed email to log on. The confirmation"
                                    + " token has been resent to your email account.") 
                            && response.code() == 400){
                        Log.e(TAG, "onResponse: " + "You must have a confirmed email to log on. The confirmation token has been resent to your email account." );
                    }else if (response.code() == 400 && response.message().equals("LockedOut"))
                        Log.e(TAG, "onResponse: usuario bloqueado");
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<com.squareup.okhttp.Response> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage() );
                    Toast.makeText(MainActivity.this, "onFailure: " + t.getMessage() , Toast.LENGTH_LONG).show();
                }
            });


        } else if (id == R.id.nav_manage) {
//            Intent MensagensActivity = new Intent(MainActivity.this, MensagensActivity.class);
//            startActivity(MensagensActivity);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            Usuario uu = gson.fromJson("{\n" +
                    "        \"ID_USUARIO\": \"ab146703-6303-4023-b83a-1159715b9bb2\",\n" +
                    "        \"NOME\": \"Frederico Rico\",\n" +
                    "        \"Cpf\": {\n" +
                    "            \"NR\": \"74390110491\"\n" +
                    "        },\n" +
                    "        \"Rg\": {\n" +
                    "            \"UF\": 16,\n" +
                    "            \"NR\": \"7343904\"\n" +
                    "        },\n" +
                    "        \"DT_NASCIMENTO\": \"2017-04-13T00:00:00\",\n" +
                    "        \"DT_INCLUSAO\": \"2017-04-12T22:58:23\",\n" +
                    "        \"DT_ALTERACAO\": \"2017-04-23T06:36:15\",\n" +
                    "        \"DT_APROVACAO\": \"2017-04-24T23:55:06\",\n" +
                    "        \"DT_ATIVACAO\": null,\n" +
                    "        \"DT_ORDENACAO\": \"2017-04-12T22:58:23\",\n" +
                    "        \"APROVADO\": false,\n" +
                    "        \"ATIVO\": true,\n" +
                    "        \"BLOQUEADO\": false,\n" +
                    "        \"ENDERECO\": {\n" +
                    "            \"ID_USUARIO\": \"ab146703-6303-4023-b83a-1159715b9bb2\",\n" +
                    "            \"UF\": 16,\n" +
                    "            \"CEP\": \"51180260\",\n" +
                    "            \"LOGRADOURO\": \"av Luxemburgo\",\n" +
                    "            \"COMPLEMENTO\": null,\n" +
                    "            \"BAIRRO\": \"Imbiribeira\",\n" +
                    "            \"CIDADE\": \"Recife\"\n" +
                    "        },\n" +
                    "        \"CONTATO\": null,\n" +
                    "        \"PERFIS_PROFISSIONAIS\": [],\n" +
                    "        \"OFERTAS_SERVICO\": null,\n" +
                    "        \"PROPOSTAS_SERVICO\": null\n" +
                    "    }", Usuario.class);


            Log.e(TAG, "onNavigationItemSelected: "+ "mensagem enviada" );
        } else if (id == R.id.nav_share) {
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

        } else if (id == R.id.nav_send) {
            mLoaderManager.initLoader(0, null, new LoaderUsuarioFullGetAll(this));


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
