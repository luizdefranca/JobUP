package com.br.jobup.models.builder;

import com.br.jobup.models.usuario.Cpf;
import com.br.jobup.models.usuario.PerfilProfisional;
import com.br.jobup.models.usuario.Rg;
import com.br.jobup.models.usuario.Telefone;
import com.br.jobup.models.usuario.Usuario;
import com.br.jobup.models.usuario.Email;
import com.br.jobup.util.Parsers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public int uf;
    public String cep;
    public String logradouro;
    public String complemento;
    public String bairro;
    public String cidade;
    public Telefone fixo;
    public Telefone celular;
    public Email email;

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

    public UsuarioBuilder Uf(int uf){
        this.uf = uf;
        return this;
    }

    public UsuarioBuilder Cep(String cep){
        this.cep = cep;
        return this;
    }

    public UsuarioBuilder Logradouro(String logradouro){
        this.logradouro = logradouro;
        return this;
    }

    public UsuarioBuilder Complemento(String complemento){
        this.complemento = complemento;
        return this;
    }

    public UsuarioBuilder Bairro(String bairro){
        this.bairro = bairro;
        return this;
    }

    public UsuarioBuilder Cidade(String cidade){
        this.cidade = cidade;
        return this;
    }

    public UsuarioBuilder Fixo(Telefone fixo){
        this.fixo = fixo;
        return this;
    }

    public UsuarioBuilder Celular(Telefone celular){
        this.celular = celular;
        return this;
    }

    public UsuarioBuilder Email(Email email){
        this.email = email;
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
    private List<Avaliacao> avaliacoes;
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
