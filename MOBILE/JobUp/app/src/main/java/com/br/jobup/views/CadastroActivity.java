package com.br.jobup.views;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.br.jobup.R;
import com.br.jobup.dao.IUsuarioDao;
import com.br.jobup.dao.UsuarioDao;
import com.br.jobup.databinding.ActivityCadastroBinding;
import com.br.jobup.models.Usuario;


import java.util.List;

import io.realm.Realm;


public class CadastroActivity extends AppCompatActivity {
    public static final String TAG = CadastroActivity.class.getName();

    private ActivityCadastroBinding mActivityCadastroBinding;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityCadastroBinding = DataBindingUtil.setContentView(this, R.layout.activity_cadastro);

        mActivityCadastroBinding.btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = mActivityCadastroBinding.edtNome.getText().toString();
                IUsuarioDao usuarioDao = new UsuarioDao();

                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuarioDao.addUsuario(usuario);

                List<Usuario> usuarios = usuarioDao.getAllUsuarios();
                Log.i(TAG, usuarios.get(0).toString());

            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
