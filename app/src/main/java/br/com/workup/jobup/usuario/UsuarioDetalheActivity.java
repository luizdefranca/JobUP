package br.com.workup.jobup.usuario;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.br.jobup.R;

import br.com.workup.jobup.dao.UsuarioRepositorio;

public class UsuarioDetalheActivity extends AppCompatActivity
        implements UsuarioDetalheFragment.AoEditarUsuario,
        UsuarioDialogFragment.AoSalvarUsuario {

    public static final String EXTRA_USUARIO = "usuario";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_detalhe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Usuario usuario = (Usuario)intent.getSerializableExtra(EXTRA_USUARIO);
        exibirUsuarioFragment(usuario);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void exibirUsuarioFragment(Usuario usuario) {
        UsuarioDetalheFragment fragment = UsuarioDetalheFragment.novaInstancia(usuario);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.detalhe, fragment, UsuarioDetalheFragment.TAG_DETALHE);
        ft.commit();
    }
    @Override
    public void aoEditarusuario(Usuario usuario) {
        UsuarioDialogFragment editNameDialog = UsuarioDialogFragment.newInstance(usuario);
        editNameDialog.abrir(getSupportFragmentManager());
    }
    @Override
    public void salvouUsuario(Usuario usuario) {
        UsuarioRepositorio repo = new UsuarioRepositorio(this);
        repo.salvar(usuario);
        exibirUsuarioFragment(usuario);
        setResult(RESULT_OK);
    }


}