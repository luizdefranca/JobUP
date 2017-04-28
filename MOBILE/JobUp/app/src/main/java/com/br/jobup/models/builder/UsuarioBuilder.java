package com.br.jobup.models.builder;

import com.br.jobup.models.Avaliacoes;
import com.br.jobup.models.Contato;
import com.br.jobup.models.Cpf;
import com.br.jobup.models.Email;
import com.br.jobup.models.Endereco;
import com.br.jobup.models.Formacao;
import com.br.jobup.models.PerfilProfisional;
import com.br.jobup.models.Rg;
import com.br.jobup.models.Telefone;
import com.br.jobup.models.Usuario;
import com.br.jobup.util.Parsers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.id;
import static com.br.jobup.util.Parsers.parseStringToDataNormal;

/**
 * Created by luizramos on 27/04/17.
 */

public class UsuarioBuilder {

    private Usuario usuario;

    private String idUsuario;
    private String nome;
    private Cpf cpf;
    private Rg rg;
    private Date dataNascimento;
    private Date dataInclusao;
    private Date dataAlteracao;
    private Date dataAprovacao;
    private Date dataOrdenacao;
    private boolean aprovado;
    private Endereco endereco;
    private Contato contato;
    private boolean ativo;
    private List<PerfilProfisional> perfisProfissionais;

    public UsuarioBuilder(){
        this.perfisProfissionais = new ArrayList<>();
    }

    public UsuarioBuilder ID(String id){
        this.idUsuario = id;
        return this;
    }
    public UsuarioBuilder Nome(String nome){
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder CPF(String cpf){


        this.cpf = new Cpf(cpf);
        return this;
    }

    public UsuarioBuilder Rg(int uf, String nr) throws Exception {

        if (uf > 25) throw new Exception("Valor inválido para o estado");
        this.rg = new Rg(uf, nr);

        return this;
    }

    public UsuarioBuilder DtNascimento(String data){
        this.dataNascimento = Parsers.parseStringToDataNormal(data);
        return this;
    }

    public UsuarioBuilder DtInclusao(String data){

        this.dataInclusao = parseStringToDataNormal(data);
        return this;
    }

    public UsuarioBuilder DtAprovacao(String data){
        this.dataInclusao = parseStringToDataNormal(data);
        return this;
    }
    public UsuarioBuilder DtOrdenacao(String data){
        this.dataInclusao = parseStringToDataNormal(data);
        return this;
    }

    public UsuarioBuilder Aprovado(boolean aprovado){
        this.aprovado = aprovado;
        return this;
    }

    /*
    private int Uf;
    private String Cep;
    private String logradouro;
    private String Complemento;
    private String Bairro;
    private String Cidade;
     */

    public UsuarioBuilder Endereco(String idUsuario, int uf, String cep, String logradouro, String complemento,
                                   String bairro, String cidade){
        this.endereco = new Endereco(idUsuario, uf, cep, logradouro, complemento,bairro, cidade );;
        return this;
    }

    public UsuarioBuilder Contato(String idUsuario, String fixo, String celular, String email){
        this.contato = new Contato(idUsuario, new Telefone(fixo), new Telefone(celular), new Email(email) );
        return this;
    }

    public UsuarioBuilder Ativo(boolean ativo){
        this.ativo = ativo;
        return this;
    }

    /*
      private Date dataAprovacao;
    private boolean aprovado;
    private String resumoCurriculo;
    private Especialidade especialidade;
    private List<Avaliacoes> avaliacoes;
    private List<Formacao> formacoes;
     */
    public UsuarioBuilder PerfilProfissional(PerfilProfisional perfilProfisional){
       this.perfisProfissionais.add(perfilProfisional);
        return this;
    }

    public Usuario build(){
        return usuario;
    }
}
