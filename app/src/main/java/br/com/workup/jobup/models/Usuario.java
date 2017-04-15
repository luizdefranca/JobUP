package br.com.workup.jobup.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Created by luizramos on 09/04/17.
 */

public class Usuario {

    public static final String TAG = Usuario.class.getName();



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

    //Esta propriedade nao sera persistida
    private List<PerfilProfisional> perfisProfissionais;

    //Sera usada uma string json como uma lista de perfis para persistir
    //no banco de dados

//    private String jsonPerfisLista;

    public Usuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

//    public String getJsonPerfisLista() {
//        return jsonPerfisLista;
//    }
//
//    public void setJsonPerfisLista(String jsonPerfisLista) {
//        this.jsonPerfisLista = jsonPerfisLista;
//    }

    public List<PerfilProfisional> getPerfisProfissionais() {
        return perfisProfissionais;
    }

    public void setPerfisProfissionais(List<PerfilProfisional> perfisProfissionais) {
        this.perfisProfissionais = perfisProfissionais;
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
                       List<PerfilProfisional> perfisProfissionais) {

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

//    public List<PerfilProfisional> getPerfisProfissionais() {
//        Gson gson = new Gson();
//        String serializedPerfisList = getJsonPerfisLista();
//        List<PerfilProfisional> perfilProfisionalList = gson.
//                <ArrayList<PerfilProfisional>>fromJson(serializedPerfisList,
//                        new TypeToken<ArrayList<PerfilProfisional>>(){}.getType());
//
//        return perfilProfisionalList;
//    }
//
//    public void setPerfisProfissionais(List<PerfilProfisional> perfisProfissionais) {
//        Gson gson = new Gson();
//        String perfilListJson = gson.toJson(perfisProfissionais);
//        this.setJsonPerfisLista(perfilListJson);
//
//    }

    @Override
    public String toString() {
        return "{ \"usuario\": \"" + this.getNome() + "\",  \"id\": \""
                + (this.getIdUsuario() == null? "": getIdUsuario() ) + "\"}";
    }
}
