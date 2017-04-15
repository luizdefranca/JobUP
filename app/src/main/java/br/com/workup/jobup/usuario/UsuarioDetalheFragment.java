package br.com.workup.jobup.usuario;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.br.jobup.R;


public class UsuarioDetalheFragment extends Fragment {
    public static final String TAG_DETALHE = "tagDetalhe";
    private static final String EXTRA_USUARIO = "usuario";
    TextView mTextNome;
    TextView mTextEmail;
    TextView mTextSenha;
    RatingBar mRatingEstrelas;
    Usuario mUsuario;

    ShareActionProvider mShareActionProvider;

    public static UsuarioDetalheFragment novaInstancia(Usuario usuario) {
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_USUARIO, usuario);
        UsuarioDetalheFragment fragment = new UsuarioDetalheFragment();
        fragment.setArguments(parametros);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUsuario = (Usuario)
                getArguments().getSerializable(EXTRA_USUARIO);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(
                R.layout.fragment_detalhe_usuario, container, false);
        mTextNome = (TextView)layout.findViewById(R.id.txtNome);
        mTextEmail = (TextView)layout.findViewById(R.id.txtEmail);
        mTextSenha = (TextView)layout.findViewById(R.id.txtSenha);
        //mRatingEstrelas = (RatingBar)layout.findViewById(R.id.rtbEstrelas);
        if (mUsuario != null) {
            mTextNome.setText(mUsuario.nome);
            mTextEmail.setText(mUsuario.email);
            mTextSenha.setText(mUsuario.senha);
            //mRatingEstrelas.setRating(mBoletim.estrelas);
        }
        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_usuario_detalhe, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(shareItem);
        String texto = getString(R.string.texto_compartilhar,
                mUsuario.nome, mUsuario.estrelas);
        Intent it = new Intent(Intent.ACTION_SEND);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        it.setType("text/plain");
        it.putExtra(Intent.EXTRA_TEXT, texto);
        mShareActionProvider.setShareIntent(it);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.acao_editar) {
            Activity activity = getActivity();
            if (activity instanceof AoEditarUsuario) {
                AoEditarUsuario aoEditarUsuario = (AoEditarUsuario)activity;
                aoEditarUsuario.aoEditarusuario(mUsuario);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public Usuario getUsuario() {
        return mUsuario;
    }
    public interface AoEditarUsuario {
        void aoEditarusuario(Usuario usuario);
    }
}
