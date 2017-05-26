package com.br.jobup.models.usuario;

import android.database.Cursor;

import com.br.jobup.util.Constantes;
import com.br.jobup.util.Parsers;
import com.br.jobup.validations.Formatter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/*
 * Created by Luiz Carlos Ramos on 22/05/17 05:41
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 22/05/17 05:24
 */

/**
 * Created by luizramos on 09/04/17.
 */

//@edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"UR_UNINIT_READ", "SA_FIELD_SELF_ASSIGNMENT", "UR_UNINIT_READ", "SA_FIELD_SELF_ASSIGNMENT", "UR_UNINIT_READ", "SA_FIELD_SELF_ASSIGNMENT", "UR_UNINIT_READ", "SA_FIELD_SELF_ASSIGNMENT", "UR_UNINIT_READ", "UR_UNINIT_READ", "SA_FIELD_SELF_ASSIGNMENT", "UR_UNINIT_READ", "SA_FIELD_SELF_ASSIGNMENT", "SA_FIELD_SELF_ASSIGNMENT"})
//@edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2", "EI_EXPOSE_REP", "EI_EXPOSE_REP2"})

public class Usuario implements Serializable{

    public static final String TAG = Usuario.class.getName();




    @SerializedName("ID_USUARIO")
    @Expose
    public String idUsuario;

    @SerializedName("NOME")
    @Expose
    public String nome;

    @SerializedName("Cpf")
    @Expose
    public Cpf cpf;

    @SerializedName("Rg")
    @Expose
    public Rg rg;

    @SerializedName("DT_NASCIMENTO")
    @Expose
    public Date dtNascimento;

    @SerializedName("DT_INCLUSAO")
    @Expose
    public Date dtInclusao;

    @SerializedName("DT_ALTERACAO")
    @Expose
    public Date dtAlteracao;

    @SerializedName("DT_APROVACAO")
    @Expose
    public Date dtAtivacao;

    @SerializedName("DT_ATIVACAO")
    @Expose
    public Date dtOrdenacao;

    @SerializedName("DT_ORDENACAO")
    @Expose
    public Date dtAprovacao;

    @SerializedName("APROVADO")
    @Expose
    public Boolean aprovado;

    @SerializedName("ATIVO")
    @Expose
    public Boolean ativo;

    @SerializedName("BLOQUEADO")
    @Expose
    public Boolean bloqueado;

    @SerializedName("UF")
    @Expose
    public int uf;

    @SerializedName("CEP")
    @Expose
    public String cep;

    @SerializedName("LOGRADOURO")
    @Expose
    public String logradouro;

    @SerializedName("COMPLEMENTO")
    @Expose
    public String complemento;

    @SerializedName("BAIRRO")
    @Expose
    public String bairro;

    @SerializedName("CIDADE")
    @Expose
    public String cidade;

    @SerializedName("Fixo")
    @Expose
    public Telefone fixo;

    @SerializedName("Celular")
    @Expose
    public Telefone celular;

    @SerializedName("Email")
    @Expose
    public Email email;

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Date getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public Date getDtAlteracao() {
        return dtAlteracao;
    }

    public void setDtAlteracao(Date dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    public Date getDtAtivacao() {
        return dtAtivacao;
    }

    public void setDtAtivacao(Date dtAtivacao) {
        this.dtAtivacao = dtAtivacao;
    }

    public Date getDtOrdenacao() {
        return dtOrdenacao;
    }

    public void setDtOrdenacao(Date dtOrdenacao) {
        this.dtOrdenacao = dtOrdenacao;
    }

    public Date getDtAprovacao() {
        return dtAprovacao;
    }

    public void setDtAprovacao(Date dtAprovacao) {
        this.dtAprovacao = dtAprovacao;
    }

    public Boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(Boolean aprovado) {
        this.aprovado = aprovado;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public int getUf() {
        return uf;
    }

    public void setUf(int uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Telefone getFixo() {
        return fixo;
    }

    public void setFixo(Telefone fixo) {
        this.fixo = fixo;
    }

    public Telefone getCelular() {
        return celular;
    }

    public void setCelular(Telefone celular) {
        this.celular = celular;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }




    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }



    //Sera usada uma string json como uma lista de perfis para persistir
    //no banco de dados

//    private String jsonPerfisLista;

    public Usuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String id, String nome, Rg rg, Cpf cpf,Date dtNascimento, Date dtInclusao, Date dtAlteracao,
                   Date dtAprovacao, Date dtOrdenacao, boolean aprovado, boolean ativo,
                   String logradouro, String complemento, String bairro, String cidade, int uf,
                   String cep, Telefone fixo, Telefone celular, Email email) {
            this.idUsuario = id;
            this.nome = nome;
            this.cpf = cpf;
            this.rg = rg;
            this.dtNascimento = dtNascimento;
            this.dtInclusao = dtInclusao;
            this.dtAlteracao = dtAlteracao;
            this.dtAprovacao = dtAprovacao;
            this.dtOrdenacao = dtOrdenacao;
            this.aprovado = aprovado;
            this.logradouro = logradouro;
            this.complemento = complemento;
            this.bairro = bairro;
            this.cidade = cidade;
            this.uf = uf;
            this.cep = cep;
            this.fixo = fixo;
            this.celular = celular;
            this.email = email;

    }

//    public String getJsonPerfisLista() {
//        return jsonPerfisLista;
//    }
//
//    public void setJsonPerfisLista(String jsonPerfisLista) {
//        this.jsonPerfisLista = jsonPerfisLista;
//    }



    public Usuario(String nome, Cpf cpf, Rg rg, Date dtNascimento, Date dtInclusao, Date dtAlteracao,
                   Date dtAtivacao, Date dtOrdenacao, Date dtAprovacao, Boolean aprovado,
                   Boolean ativo, Boolean bloqueado, int uf, String cep, String logradouro,
                   String complemento, String bairro, String cidade, Telefone fixo,
                   Telefone celular, Email email, List<PerfilProfisional> perfisProfissionais,
                   List<Object> ofertaServico, List<Object> propostaServico) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.dtNascimento = dtNascimento;
        this.dtInclusao = dtInclusao;
        this.dtAlteracao = dtAlteracao;
        this.dtAtivacao = dtAtivacao;
        this.dtOrdenacao = dtOrdenacao;
        this.dtAprovacao = dtAprovacao;
        this.aprovado = aprovado;
        this.ativo = ativo;
        this.bloqueado = bloqueado;
        this.uf = uf;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.fixo = fixo;
        this.celular = celular;
        this.email = email;
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
        return dtNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dtNascimento = dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dtNascimento = Parsers.parseStringToDataAPI(dataNascimento);
    }


    public Date getDataInclusao() {
        return dtInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dtInclusao = dataInclusao;
    }
    public void setDataInclusao(String dataInclusao) {
        this.dtInclusao = Parsers.parseStringToDataAPI(dataInclusao);
    }

    public Date getDataAlteracao() {
        return dtAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dtAlteracao = dataAlteracao;
    }
    public void setDataAlteracao(String dataAlteracao) {
        this.dtAlteracao = Parsers.parseStringToDataAPI(dataAlteracao);
    }

    public Date getDataAprovacao() {
        return dtAprovacao;
    }

    public void setDataAprovacao(Date dataAprovacao) {
        this.dtAprovacao = dataAprovacao;
    }
    public void setDataAprovacao(String dataAprovacao) {
        this.dtAprovacao = Parsers.parseStringToDataAPI(dataAprovacao);
    }

    public Date getDataOrdenacao() {
        return dtOrdenacao;
    }

    public void setDataOrdenacao(Date dataOrdenacao) {
        this.dtOrdenacao = dataOrdenacao;
    }
    public void setDataOrdenacao(String dataOrdenacao) {
        this.dtOrdenacao = Parsers.parseStringToDataAPI(dataOrdenacao);
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
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


    public static Usuario getUsuarioFromCursor(Cursor cursor) {


        String id = cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_ID));
        String nome = cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_NOME));

        Date dtNascimento = new Date(cursor.getLong(cursor.getColumnIndex(Constantes.USUARIO_DT_NASCIMENTO)));
        Date dtInclusao = new Date(cursor.getLong(cursor.getColumnIndex(Constantes.USUARIO_DT_INCLUSAO)));
        Date dtAprovacao = new Date(cursor.getLong(cursor.getColumnIndex(Constantes.USUARIO_DT_APROVACAO)));
        Date dtOrdenacao = new Date(cursor.getLong(cursor.getColumnIndex(Constantes.USUARIO_DT_ORDENACAO)));
        Date dtAlteracao = new Date(cursor.getLong(cursor.getColumnIndex(Constantes.USUARIO_DT_ALTERACAO)));
        boolean aprovado = Formatter.parseIntToBoolean( cursor.getInt(cursor.getColumnIndex(Constantes.USUARIO_APROVADO)));
        boolean ativo = Formatter.parseIntToBoolean( cursor.getInt(cursor.getColumnIndex(Constantes.USUARIO_ATIVO)));

        //constroi um objeto USUARIO_CPF
        //public Cpf(String cpf)
        Cpf cpf =  new Cpf(cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_CPF)));


        //Constroi um objeto Rg
        //public Rg(int uf, String nr)
        int uf = cursor.getInt(cursor.getColumnIndex(Constantes.USUARIO_RG_UF));
        String nr = cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_RG_NR));
        Rg rg = new Rg(uf, nr);

        int  Uf = cursor.getInt(cursor.getColumnIndex(Constantes.USUARIO_ENDERECO_UF));
        String cep = cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_ENDERECO_CEP));
        String logradouro = cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_ENDERECO_CEP));
        String complemento = cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_ENDERECO_COMPLEMENTO));
        String bairro = cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_ENDERECO_BAIRRO));
        String cidade = cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_ENDERECO_CIDADE));
        Telefone fixo = new Telefone(cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_FIXO)));
        Telefone celular = new Telefone(cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_CELULAR)));
        Email email = new Email(cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_EMAIL)));

        //Constroi um objeto usuario
        //  public Usuario(String nome, Cpf cpf, Rg rg, Date dataNascimento, Date dataInclusao,
        //  Date dataAlteracao, Date dataAprovacao, Date dataOrdenacao, boolean aprovado,
        //  Endereco endereco, IContatoAPI contato,
        //  List<PerfilProfisional> perfisProfissionais)

        List<PerfilProfisional> perfilProfisionals = null;

        Usuario usuario  = new Usuario(id, nome, rg, cpf, dtNascimento, dtInclusao, dtAlteracao,
                dtAprovacao, dtOrdenacao, aprovado, ativo, logradouro, complemento, bairro, cidade,
                uf, cep, fixo, celular, email);
        return usuario;
    }

}
