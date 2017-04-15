package br.com.workup.jobup.usuario;
import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.br.jobup.R;

public class UsuarioDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {
    private static final String DIALOG_TAG = "editDialog";
    private static final String EXTRA_USUARIO = "usuario";
    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtSenha;
    private RatingBar rtbEstrelas;
    private Usuario mUsuario;

    public static UsuarioDialogFragment newInstance(Usuario usuario) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_USUARIO, usuario);
        UsuarioDialogFragment dialog = new UsuarioDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUsuario = (Usuario)getArguments().getSerializable(EXTRA_USUARIO);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(
                R.layout.fragment_dialog_usuario, container, false);
        txtNome = (EditText) layout.findViewById(R.id.txtNome);
        txtNome.requestFocus();
        txtEmail = (EditText) layout.findViewById(R.id.txtEmail);
        txtEmail.setOnEditorActionListener(this);
        txtSenha = (EditText) layout.findViewById(R.id.txtSenha);
        txtSenha.setOnEditorActionListener(this);
        rtbEstrelas = (RatingBar)layout.findViewById(R.id.rtbEstrelas);

        if (mUsuario != null) {
            txtNome.setText(mUsuario.nome);
            txtEmail.setText(mUsuario.email);
            txtSenha.setText(mUsuario.senha);
            rtbEstrelas.setRating(mUsuario.estrelas);

        }
        // Exibe o teclado virtual ao exibir o Dialog
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle(R.string.acao_novo);
        return layout;
    }
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            Activity activity = getActivity();
            if (activity instanceof AoSalvarUsuario) {
                if (mUsuario == null) {
                    mUsuario = new Usuario(
                            txtNome.getText().toString(),
                            txtEmail.getText().toString(),
                            txtSenha.getText().toString(),
                            rtbEstrelas.getRating());

                } else {
                    mUsuario.nome = txtNome.getText().toString();
                    mUsuario.email = txtEmail.getText().toString();
                    mUsuario.senha = txtSenha.getText().toString();
                    mUsuario.estrelas = rtbEstrelas.getRating();
                }
                AoSalvarUsuario listener = (AoSalvarUsuario) activity;
                listener.salvouUsuario(mUsuario);
                // Feche o dialog
                dismiss();
                return true;
            }
        }
        return false;
    }
    public void abrir(FragmentManager fm) {
        if (fm.findFragmentByTag(DIALOG_TAG) == null) {
            show(fm, DIALOG_TAG);
        }
    }
    public interface AoSalvarUsuario {
        void salvouUsuario(Usuario usuario);
    }
}
