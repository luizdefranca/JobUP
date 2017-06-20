package com.br.jobup.helpers;

import android.content.res.Resources;
import android.widget.EditText;
import android.widget.Spinner;

import com.br.jobup.activities.CadastroActivity;
import com.br.jobup.R;
import com.br.jobup.models.usuario.Cpf;
import com.br.jobup.models.usuario.Email;
import com.br.jobup.models.usuario.Rg;
import com.br.jobup.models.usuario.Telefone;
import com.br.jobup.models.usuario.Usuario;
import com.br.jobup.util.Mask;
import com.br.jobup.util.Parsers;
import com.br.jobup.util.Validations.TextHelper;

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

    private Usuario usuario;
    private CadastroActivity activity;

    public CadastroUsuarioHelper(CadastroActivity activity){
        this.activity = activity;
        campoNome = (EditText) activity.findViewById(R.id.nomeCadastroView);
        campoCpf = (EditText) activity.findViewById(R.id.cpfCadastroView);
        campoCpf.addTextChangedListener(Mask.insert("###.###.###-##", campoCpf));
        campoRgNr = (EditText) activity.findViewById(R.id.rgNrCadastroView);
        campoRgUf = (Spinner) activity.findViewById(R.id.rgUfCadastroView);
        campoDtNascimento = (EditText) activity.findViewById(R.id.dtNascimentoCadastroView);
        campoDtNascimento.addTextChangedListener(Mask.insert("##/##/####", campoDtNascimento));
        campoCep = (EditText) activity.findViewById(R.id.cepCadastroView);
        campoCep.addTextChangedListener(Mask.insert("##.###-###", campoCep));
        campoUf = (Spinner) activity.findViewById(R.id.ufCadastroView);
        campoLogradouro = (EditText) activity.findViewById(R.id.logradouroCadastroView);
        campoComplemento = (EditText) activity.findViewById(R.id.complementoEnderecoCadastroView);
        campoBairro = (EditText) activity.findViewById(R.id.bairroCadastroView);
        campoCidade = (EditText) activity.findViewById(R.id.cidadeCadastroView);
        campoFixo = (EditText) activity.findViewById(R.id.fixoCadastroView);
        campoFixo.addTextChangedListener(Mask.insert("(##)####-####", campoFixo));
        campoCelular = (EditText) activity.findViewById(R.id.celularCadastroView);
        campoCelular.addTextChangedListener(Mask.insert("(##)####-####", campoCelular));

        usuario = new Usuario();
    }

    public void preencheCadastroUsuario(Usuario usuario){
        campoNome.setText(usuario.getNome());
        campoCpf.setText( usuario.getCpf().getNr());
        campoRgNr.setText(usuario.getRg().getNr());
        campoRgUf.setTag(usuario.getRg().getUf(),Resources.getSystem().getStringArray(R.array.estados));
        campoDtNascimento.setText(Parsers.parseDataToStringNormal(usuario.getDataNascimento()));
        campoCep.setText(usuario.getCep());
        campoUf.setTag(usuario.getUf(),Resources.getSystem().getStringArray(R.array.estados));
        campoLogradouro.setText(usuario.getLogradouro());
        campoComplemento.setText(usuario.getComplemento());
        campoBairro.setText(usuario.getBairro());
        campoCidade.setText(usuario.getCidade());
        campoFixo.setText(usuario.getFixo().getNr());
        campoCelular.setText(usuario.getCelular().getNr());
    }

    public Usuario getUsuario(){
        usuario.setNome(campoNome.getText().toString());

        usuario.setCpf(new Cpf(TextHelper.GetNumeros(campoCpf.getText().toString())));
        usuario.setRg(new Rg(campoRgUf.getSelectedItemPosition(), TextHelper.GetNumeros(campoRgNr.getText().toString())));
        usuario.setDataNascimento(Parsers
                .parseStringToDataNormal(campoDtNascimento.getText().toString()));
        usuario.cep = TextHelper.GetNumeros(campoCep.getText().toString());
        usuario.logradouro = campoLogradouro.getText().toString();
        usuario.complemento = campoComplemento.getText().toString();
        usuario.bairro = campoBairro.getText().toString();
        usuario.cidade = campoCidade.getText().toString();
        usuario.uf = campoUf.getSelectedItemPosition();
        usuario.fixo = new Telefone(campoFixo.getText().toString());
        usuario.celular = new Telefone(campoCelular.getText().toString());
        usuario.email = new Email(activity.getIntent().getStringExtra("email"));
        usuario.idUsuario = activity.getIntent().getStringExtra("idUsuario");

        return usuario;
    }
}
