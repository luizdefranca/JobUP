package com.dell.juliana.filme;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dell.juliana.filme.fragments.PageFragment;
import com.dell.juliana.filme.model.Categoria;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class ViewpagerActivity extends AppCompatActivity {

    private PageFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        String[] titles = {"Pagina 1", "Pagina 2", "Pagina 3"};
        adapter = new PageFragmentAdapter(getSupportFragmentManager(), titles );
        viewPager.setAdapter(adapter);

        new AsyncTask<Void, Void, List<Categoria>>() {

            public ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                dialog = ProgressDialog.show(ViewpagerActivity.this,"Aguarde...","listando filmes.",true,true);
            }

            @Override
            protected List<Categoria> doInBackground(Void... params) {
                //Uma requisição a API Filme
                List<Categoria> categorias = null;

                try {
                    URL url = new URL("http://api.androidhive.info/json/movies.json");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.connect();

                    Scanner scanner = new Scanner(connection.getInputStream());
                    String json = scanner.next();

                    Gson gson = new Gson();
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    Type type = new TypeToken<List<Categoria>>(){}.getType();
                    categorias = gson.fromJson(jsonArray.toString(),type);



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return categorias;
            }

            @Override
            protected void onPostExecute(List<Categoria> categorias) {
                dialog.dismiss();

                PageFragment fragment1 = (PageFragment) adapter.getItem(0);
                PageFragment fragment2 = (PageFragment) adapter.getItem(1);
                fragment1.categorias = categorias.subList(0,10);
                fragment2.categorias = categorias.subList(11,20);

                adapter.notifyDataSetChanged();

                fragment1.arrayAdapter.notifyDataSetChanged();
                fragment2.arrayAdapter.notifyDataSetChanged();

            }
        }.execute();



    }
}


