package com.br.jobup.util;

/**
 * Created by luizramos on 16/04/17.
 */

public interface Constantes {
    //NOME DAS TABELAS
    public static final String TABELA_USUARIO = "usuario";
    public final String TABELA_ESPECIALIDADE = "especialidade";
    public static final String TABELA_PERFIL_PROFISSIONAL = "perfil_profissional";
    public static final String TABELA_AVALIACAO = "avaliacao" ;

    //CAMPOS DA TABELA USUARIO

    public static final String USUARIO_ID = "ID";
    public static final String USUARIO_NOME = "USUARIO_NOME";
    public static final String USUARIO_CPF = "USUARIO_CPF";
    public static final String USUARIO_RG_UF = "USUARIO_RG_UF";
    public static final String USUARIO_RG_NR = "USUARIO_RG_NR";
    public static final String USUARIO_DT_NASCIMENTO = "USUARIO_DT_NASCIMENTO";
    public static final String USUARIO_DT_INCLUSAO = "USUARIO_DT_INCLUSAO";
    public static final String USUARIO_DT_ALTERACAO = "USUARIO_DT_ALTERACAO";
    public static final String USUARIO_DT_APROVACAO = "USUARIO_DT_APROVACAO";
    public static final String USUARIO_DT_ORDENACAO = "USUARIO_DT_ORDENACAO";
    public static final String USUARIO_APROVADO = "USUARIO_APROVADO";
    public static final String USUARIO_ATIVO = "USUARIO_ATIVO";
    public static final String USUARIO_ENDERECO_UF = "USUARIO_ENDERECO_UF";
    public static final String USUARIO_ENDERECO_CEP = "USUARIO_ENDERECO_CEP";
    public static final String USUARIO_ENDERECO_LOGRADOURO = "ENDERECO_LOGRADOURO";
    public static final String USUARIO_ENDERECO_COMPLEMENTO = "USUARIO_ENDERECO_COMPLEMENTO";
    public static final String USUARIO_ENDERECO_BAIRRO = "USUARIO_ENDERECO_BAIRRO";
    public static final String USUARIO_ENDERECO_CIDADE = "USUARIO_ENDERECO_CIDADE";
    public static final String USUARIO_FIXO = "USUARIO_FIXO";
    public static final String USUARIO_CELULAR = "USUARIO_CELULAR";
    public static final String USUARIO_EMAIL = "USUARIO_EMAIL";

    //CAMPOS DA TABELA ESPECIALIDADE
    public static String ID_ESPECIALIDADE = "ID_ESPECIALIDADE";
    public static String DESCRICAO = "DESCRICAO";
    public static String EXIGIR_COMPROVACAO = "EXIGIR_COMPROVACAO";

    //CAMPOS DA TABELA PERFIL_PROFISSIONAL

    public static String PERFIL_ID_PERFIL_PROFISSIONAL = "ID_PERFIL";

    //CAMPOS DA TABELA AVALIACAO
    public static final String ID_CLIENTE = "id_cliente";
    public static final String DT_ULT_AVALIACAO = "dt_ultima_avaliacao";
    public static final String NOTA = "nota";
    public static final String COMENTARIO = "comentario";

    //CAMPOS DA TABELA APROVACAO
    public static final String  DATA_APROVACAO = "dt_aprovacao";
    public static final String APROVADO = "aprovado";
    public static final String RESUMO_CURRICULO = "resumo_curriculo";

    //CAMPOS DA TABELA FORMACAO
    public static final String TABELA_FORMACAO = "formacao";

    public static final String ID_FORMACAO = "id_formacao";
    public static final String INSTITUICAO = "instituicao";
    public static final String NOME_CURSO = "nome_curso";
    public static final String ANO_FORMACAO = "ano_formacao";
    public static final String DT_APROVACAO = "dt_aprovacao";

    //CREATE DAS TABELAS
    public static final String CREATE_USUARIO =
//            "DROP TABLE TABELA_USUARIO; \n"+
            "CREATE TABLE " + TABELA_USUARIO +"(\n" +
                    "\t " + USUARIO_ID + " Text NOT NULL PRIMARY KEY,\n" +
                    "\t " + USUARIO_NOME + " Text NOT NULL,\n" +
                    "\t " + USUARIO_CPF + " Text NOT NULL,\n" +
                    "\t " + USUARIO_RG_UF + " Integer NOT NULL,\n" +
                    "\t " + USUARIO_RG_NR + " Text NOT NULL,\n" +
                    "\t " + USUARIO_DT_NASCIMENTO +" DateTime NOT NULL,\n" +
                    "\t " + USUARIO_DT_INCLUSAO + " DateTime DEFAULT CURRENT_TIMESTAMP NOT NULL,\n" +
                    "\t " + USUARIO_DT_ALTERACAO + " DateTime,\n" +
                    "\t " + USUARIO_DT_APROVACAO + " DateTime,\n" +
                    "\t " + USUARIO_DT_ORDENACAO + " DateTime,\n" +
                    "\t " + USUARIO_APROVADO + " Boolean DEFAULT FALSE,\n" +
                    "\t " + USUARIO_ATIVO + " Boolean NOT NULL DEFAULT TRUE,\n" +
                    "\t " + USUARIO_ENDERECO_UF + " Text NOT NULL,\n" +
                    "\t " + USUARIO_ENDERECO_CEP +" Text NOT NULL,\n" +
                    "\t " + USUARIO_ENDERECO_LOGRADOURO +" Text NOT NULL,\n" +
                    "\t " + USUARIO_ENDERECO_COMPLEMENTO +" Text,\n" +
                    "\t " + USUARIO_ENDERECO_BAIRRO + " Text NOT NULL,\n" +
                    "\t " + USUARIO_ENDERECO_CIDADE + " Text NOT NULL,\n" +
                    "\t " + USUARIO_FIXO + " Text,\n" +
                    "\t " + USUARIO_CELULAR + " Text NOT NULL,\n" +
                    "\t " + USUARIO_EMAIL + " Text,\n" +
                    "\t " + PERFIL_ID_PERFIL_PROFISSIONAL + " Text,\n" +
                    "CONSTRAINT \"unique__ID\" UNIQUE ( "+ USUARIO_ID +" ),\n" +
                    "CONSTRAINT \"unique_CPF\" UNIQUE ( "+ USUARIO_CPF +" ) );";

    public static final String CREATE_ESPECIALIDADE =
            "CREATE TABLE " + TABELA_ESPECIALIDADE + "(\n" +
                    ID_ESPECIALIDADE +" Integer NOT NULL PRIMARY KEY,\n" +
                    DESCRICAO + " Text NOT NULL,\n" +
                    EXIGIR_COMPROVACAO + " Boolean DEFAULT 'FALSE',\n" +
                    "CONSTRAINT \"unique_ID_ESPECIALIDADE\" UNIQUE ( " + ID_ESPECIALIDADE + " ) );";

    public static final String CREATE_PERFIL_PROFISSIONAL =
            "CREATE TABLE " +  TABELA_PERFIL_PROFISSIONAL + " ( \n" +
                    USUARIO_ID +  " TEXT NOT NULL UNIQUE, \n" +
                    ID_ESPECIALIDADE + " INTEGER NOT NULL, \n" +
                    DATA_APROVACAO + " INTEGER NOT NULL, \n" +
                    APROVADO + " BOOLEAN DEFAULT 0, \n" +
                    RESUMO_CURRICULO + " TEXT, \n" +
                    "PRIMARY KEY(" + USUARIO_ID + " ," + ID_ESPECIALIDADE + " ), \n" +
                    "FOREIGN KEY(" + USUARIO_ID + " ) REFERENCES " +  TABELA_USUARIO + "(" + USUARIO_ID + " ),\n" +
                    "FOREIGN KEY(" + ID_ESPECIALIDADE + " ) REFERENCES " + TABELA_ESPECIALIDADE + "(" + ID_ESPECIALIDADE + ")) ;";


    public static final String CREATE_AVALIACAO =
            "CREATE TABLE " + TABELA_AVALIACAO + " (\n" +
                    USUARIO_ID + "TEXT NOT NULL,\n" +
                    ID_ESPECIALIDADE + " INTEGER NOT NULL,\n" +
                    ID_CLIENTE + " TEXT NOT NULL,\n" +
                    DT_ULT_AVALIACAO +  " DATETIME,\n" +
                    NOTA+ " INTEGER DEFAULT 0,\n" +
                    COMENTARIO + " TEXT NOT NULL,\n" +
                    " PRIMARY KEY(" + USUARIO_ID + " ," + ID_ESPECIALIDADE + " ," + ID_CLIENTE + " )\n" +
                    " FOREIGN KEY(" + USUARIO_ID + " ," + ID_ESPECIALIDADE +  " ) REFERENCES " +
                    TABELA_PERFIL_PROFISSIONAL + "( " + USUARIO_ID + ", " + ID_ESPECIALIDADE + ") );";



    public static final String CREATE_FORMACAO =
            "CREATE TABLE " + TABELA_FORMACAO + " (\n" +
                    USUARIO_ID + " TEXT NOT NULL UNIQUE, \n" +
                    ID_ESPECIALIDADE + " INTEGER NOT NULL, \n" +
                    ID_FORMACAO + " INTEGER NOT NULL, \n" +
                    INSTITUICAO +" TEXT NOT NULL, \n" +
                    NOME_CURSO + " TEXT NOT NULL, \n" +
                    ANO_FORMACAO + " NUMERIC NOT NULL, \n" +
                    DT_APROVACAO + " DATE, \n" +
                    APROVADO + " BOOLEAN, \n" +
                    " PRIMARY KEY(" + USUARIO_ID + " ," + ID_ESPECIALIDADE + " ," + ID_FORMACAO +  ")\n" +
                    " FOREIGN KEY(" + USUARIO_ID + " ," + ID_ESPECIALIDADE + " ) REFERENCES " +
                    TABELA_PERFIL_PROFISSIONAL + "( " + USUARIO_ID + " ," +  ID_ESPECIALIDADE + " ) );";


}
