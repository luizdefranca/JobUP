package br.com.jobup.util;

/**
 * Created by luizramos on 16/04/17.
 */

public interface Constantes {
    //USUARIO_NOME DAS TABELAS
    public static final String TABELA_USUARIO = "usuario";
    public final String TABELA_ESPECIALIDADE = "especialidade";

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



}
