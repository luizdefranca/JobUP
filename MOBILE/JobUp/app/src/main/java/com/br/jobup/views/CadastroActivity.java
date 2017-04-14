package com.br.jobup;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.br.jobup.databinding.ActivityCadastroBinding;
import com.br.jobup.models.Usuario;

import java.util.UUID;

import io.realm.Realm;


public class CadastroActivity extends AppCompatActivity {

    ActivityCadastroBinding mActivityCadastroBinding;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityCadastroBinding = DataBindingUtil.setContentView(this, R.layout.activity_cadastro);

        mActivityCadastroBinding.btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm = Realm.getDefaultInstance();

                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        Usuario usuario = new Usuario(1);
                        usuario.setNome( mActivityCadastroBinding.edtNome.getText().toString());
                    }
                });

            }
        });
//        realm = Realm.getDefaultInstance();
//
//        RealmResults<Usuario> usuarios = realm.where(Usuario.class).findAll();
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
