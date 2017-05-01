package com.br.jobup.models;

import android.database.Cursor;

import com.br.jobup.util.Constantes;
import com.br.jobup.util.Parsers;
import com.br.jobup.validations.Formatter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Created by luizramos on 09/04/17.
 */

public class Usuario implements Serializable{

    public static final String TAG = Usuario.class.getName();


    @SerializedName("ID_USUARIO")
    private String idUsuario;
    @SerializedName("NOME")
    private String nome;
    @SerializedName("CPF")
    private Cpf cpf;
    @SerializedName("RG")
    private Rg rg;
    @SerializedName("DT_NASCIMENTO")
    private Date dataNascimento;
    @SerializedName("DT_INCLUSAO")
    private Date dataInclusao;
    @SerializedName("DT_ALTERACAO")
    private Date dataAlteracao;
    @SerializedName("DT_APROVACAO")
    private Date dataAprovacao;
    @SerializedName("DT_ORDENACAO")
    private Date dataOrdenacao;
    @SerializedName("APROVADO")
    private boolean aprovado;
    @SerializedName("ENDERECO")
    private Endereco endereco;
    @SerializedName("CONTATO")
    private Contato contato;
    @SerializedName("ATIVO")
    private boolean ativo;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    //Esta propriedade nao sera persistida
    private List<PerfilProfisional> perfisProfissionais;

    //Sera usada uma string json como uma lista de perfis para persistir
    //no banco de dados

//    private String jsonPerfisLista;

    public Usuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String id, String nome, Rg rg, Date dtNascimento, Date dtInclusao, Date dtAlteracao, Date dtAprovacao, Date dtOrdenacao, boolean aprovado, boolean ativo, Endereco endereco, Contato contato, List<PerfilProfisional> perfilProfisionals) {
            this.idUsuario = id;
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
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = Parsers.parseStringToDataAPI(dataNascimento);
    }


    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
    public void setDataInclusao(String dataInclusao) {
        this.dataInclusao = Parsers.parseStringToDataAPI(dataInclusao);
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao = Parsers.parseStringToDataAPI(dataAlteracao);
    }

    public Date getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(Date dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }
    public void setDataAprovacao(String dataAprovacao) {
        this.dataAprovacao = Parsers.parseStringToDataAPI(dataAprovacao);
    }

    public Date getDataOrdenacao() {
        return dataOrdenacao;
    }

    public void setDataOrdenacao(Date dataOrdenacao) {
        this.dataOrdenacao = dataOrdenacao;
    }
    public void setDataOrdenacao(String dataOrdenacao) {
        this.dataOrdenacao = Parsers.parseStringToDataAPI(dataOrdenacao);
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


        //Constroi um objeto RG
        //public Rg(int uf, String nr)
        int uf = cursor.getInt(cursor.getColumnIndex(Constantes.USUARIO_RG_UF));
        String nr = cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_RG_NR));
        Rg rg = new Rg(uf, nr);

        //Constroi um objeto endereco
        //public Endereco(String idUsuario, String uf, String cep, String logradouro, String complemento, String bairro, String cidade)
        int enderecoUf = cursor.getInt(cursor.getColumnIndex(Constantes.USUARIO_ENDERECO_UF));
        String cep = cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_ENDERECO_CEP));
        String logradouro = cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_ENDERECO_CEP));
        String complemento = cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_ENDERECO_COMPLEMENTO));
        String bairro = cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_ENDERECO_BAIRRO));
        String cidade = cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_ENDERECO_CIDADE));

        Endereco endereco = new Endereco(id, enderecoUf,cep, logradouro, complemento, bairro, cidade);

        //Constroi um objeto contato
        //public IContato(String idUsuario, Telefone fixo, Telefone celular, Email email)
        //Constroi um objeto telefone_fixo
        Telefone fixo = new Telefone(cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_FIXO)));
        Telefone celular = new Telefone(cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_CELULAR)));
        Email email = new Email(cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_EMAIL)));
        Contato contato = new Contato(id, fixo, celular, email);

        //Constroi um objeto usuario
        //  public Usuario(String nome, Cpf cpf, Rg rg, Date dataNascimento, Date dataInclusao,
        //  Date dataAlteracao, Date dataAprovacao, Date dataOrdenacao, boolean aprovado,
        //  Endereco endereco, IContato contato,
        //  List<PerfilProfisional> perfisProfissionais)

        List<PerfilProfisional> perfilProfisionals = null;

        Usuario usuario  = new Usuario(id, nome, rg, dtNascimento, dtInclusao, dtAlteracao,
                dtAprovacao, dtOrdenacao, aprovado, ativo, endereco, contato, perfilProfisionals);
        return usuario;
    }

}
