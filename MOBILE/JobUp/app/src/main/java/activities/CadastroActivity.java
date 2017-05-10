package activities;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.br.jobup.R;
import com.br.jobup.dao.usuario.IUsuarioDao;
import com.br.jobup.dao.usuario.UsuarioDao;
import com.br.jobup.helpers.CadastroUsuarioHelper;
import com.br.jobup.models.Usuario;
import com.br.jobup.validations.Util;

/*
 * Created by Luiz Carlos Ramos on 09/05/17 19:02
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 09/05/17 19:00
 */

public class CadastroActivity extends AppCompatActivity {
    public static final String TAG = CadastroActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Spinner spinner = (Spinner) findViewById(R.id.rgUfCadastroView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_salvar) {

            CadastroUsuarioHelper usuarioHelper = new CadastroUsuarioHelper(this);
             IUsuarioDao dao = new UsuarioDao(this);
             Usuario usuario = usuarioHelper.getUsuario();
            usuario.setIdUsuario(Util.getUUID());


            dao.addUsuario(usuario);
            dao.close();
            Intent detalheServico = new Intent(CadastroActivity.this, ListaNovaDeUsuariosActivity.class);
            startActivity(detalheServico);
//            usuario.salvaUsuario(usuario);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
