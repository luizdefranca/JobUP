package com.br.jobup.models;

import org.joda.time.LocalDate;

import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


/**
 * Created by luizramos on 09/04/17.
 */

public class Usuario extends RealmObject{

    public static final String TAG = Usuario.class.getName();
    public static final String ID = "br.com.workoup.jobup.models.RealmObject.ID";


    @PrimaryKey
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
    private RealmList<PerfilProfisional> perfisProfissionais;

    public Usuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(
                       String nome,
                       Cpf cpf,
                       Rg rg,
                       Date dataNascimento,
                       Date dataInclusao,
                       Date dataAlteracao,
                       Date dataAprovacao,
                       Date dataOrdenacao,
                       boolean aprovado,
                       Endereco endereco,
                       Contato contato,
                       RealmList<PerfilProfisional> perfisProfissionais) {

        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.dataInclusao = dataInclusao;
        this.dataAlteracao = dataAlteracao;
        this.dataAprovacao = dataAprovacao;
        this.dataOrdenacao = dataOrdenacao;
        this.aprovado = aprovado;
        this.endereco = endereco;
        this.contato = contato;
        this.perfisProfissionais = perfisProfissionais;
    }

    public Usuario(){};

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public Rg getRg() {
        return rg;
    }

    public void setRg(Rg rg) {
        this.rg = rg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Date getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(Date dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public Date getDataOrdenacao() {
        return dataOrdenacao;
    }

    public void setDataOrdenacao(Date dataOrdenacao) {
        this.dataOrdenacao = dataOrdenacao;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<PerfilProfisional> getPerfisProfissionais() {
        return perfisProfissionais;
    }

    public void setPerfisProfissionais(RealmList<PerfilProfisional> perfisProfissionais) {
        this.perfisProfissionais = perfisProfissionais;
    }

    @Override
    public String toString() {
        return "{ \"usuario\": \"" + this.getNome() + "\",  \"id\": \""
                + (this.getIdUsuario() == null? "": getIdUsuario() ) + "\"}";
    }
}
