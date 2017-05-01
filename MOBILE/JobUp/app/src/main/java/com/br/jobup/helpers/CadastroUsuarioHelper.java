package com.br.jobup.helpers;

import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.br.jobup.CadastroActivity;
import com.br.jobup.R;
import com.br.jobup.models.Contato;
import com.br.jobup.models.Cpf;
import com.br.jobup.models.Email;
import com.br.jobup.models.Endereco;
import com.br.jobup.models.Rg;
import com.br.jobup.models.Telefone;
import com.br.jobup.models.Usuario;
import com.br.jobup.util.Parsers;

/**
 * Created by luizramos on 22/04/17.
 */

public class CadastroUsuarioHelper {

    private final EditText campoNome;
    private final EditText campoCpf;
    private final EditText campoRgNr;
    private final Spinner   campoRgUf;
    private final EditText campoDtNascimento;
    private final EditText campoCep;
    private final Spinner   campoUf;
    private final EditText campoLogradouro;
    private final EditText campoComplemento;
    private final EditText campoBairro;
    private final EditText campoCidade;
    private final EditText campoFixo;
    private final EditText campoCelular;
    private final EditText campoEmail;

    private Usuario usuario;

    public CadastroUsuarioHelper(CadastroActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.nomeCadastroView);
        campoCpf = (EditText) activity.findViewById(R.id.cpfCadastroView);
        campoRgNr = (EditText) activity.findViewById(R.id.rgNrCadastroView);
        campoRgUf = (Spinner) activity.findViewById(R.id.rgUfCadastroView);
        campoDtNascimento = (EditText) activity.findViewById(R.id.dtNascimentoCadastroView);
        campoCep = (EditText) activity.findViewById(R.id.cepCadastroView);
        campoUf = (Spinner) activity.findViewById(R.id.ufCadastroView);
        campoLogradouro = (EditText) activity.findViewById(R.id.logradouroCadastroView);
        campoComplemento = (EditText) activity.findViewById(R.id.complementoEnderecoCadastroView);
        campoBairro = (EditText) activity.findViewById(R.id.bairroCadastroView);
        campoCidade = (EditText) activity.findViewById(R.id.cidadeCadastroView);
        campoFixo = (EditText) activity.findViewById(R.id.fixoCadastroView);
        campoCelular = (EditText) activity.findViewById(R.id.celularCadastroView);
        campoEmail = (EditText) activity.findViewById(R.id.emailCadastroView);
        usuario = new Usuario();
    }

    public void preencheCadastroUsuario(Usuario usuario){
        campoNome.setText(usuario.getNome());
        campoCpf.setText( usuario.getCpf().getNr());
        campoRgNr.setText(usuario.getRg().getNr());
        campoRgUf.setTag(usuario.getRg().getUf(),Resources.getSystem().getStringArray(R.array.estados));
        campoDtNascimento.setText(Parsers.parseDataToStringNormal(usuario.getDataNascimento()));
        campoCep.setText(usuario.getEndereco().getCep());
        campoUf.setTag(usuario.getEndereco().getUf(),Resources.getSystem().getStringArray(R.array.estados));
        campoLogradouro.setText(usuario.getEndereco().getLogradouro());
        campoComplemento.setText(usuario.getEndereco().getComplemento());
        campoBairro.setText(usuario.getEndereco().getBairro());
        campoCidade.setText(usuario.getEndereco().getCidade());
        campoFixo.setText(usuario.getContato().getFixo().getNr());
        campoCelular.setText(usuario.getContato().getCelular().getNr());
        campoEmail.setText(usuario.getContato().getEmail().getEmail());
    }

    public Usuario getUsuario(){
        usuario.setNome(campoNome.getText().toString());

        usuario.setCpf(new Cpf(campoCpf.getText().toString()));
        usuario.setRg(new Rg(campoRgUf.getSelectedItemPosition(), campoRgNr.getText().toString()));
        usuario.setDataNascimento(Parsers
                .parseStringToDataNormal(campoDtNascimento.getText().toString()));

        // public Endereco(String idUsuario,
        // int uf,
        // String cep,
        // String logradouro,
        // String complemento,
        // String bairro,
        // String cidade)
        usuario.setEndereco(new Endereco(
                usuario.getIdUsuario(),
                campoUf.getSelectedItemPosition(),
                campoCep.getText().toString(),
                campoLogradouro.getText().toString(),
                campoComplemento.getText().toString(),
                campoBairro.getText().toString(),
                campoCidade.getText().toString()
        ));

        // public IContato(String idUsuario, Telefone fixo, Telefone celular, Email email)
        usuario.setContato(new Contato(
                usuario.getIdUsuario(),
                new Telefone(campoFixo.getText().toString()),
                new Telefone(campoCelular.getText().toString()),
                new Email(campoEmail.getText().toString())
        ));

        return usuario;
    }
}
