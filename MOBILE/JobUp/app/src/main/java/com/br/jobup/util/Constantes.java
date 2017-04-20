package com.br.jobup.util;

/**
 * Created by luizramos on 16/04/17.
 */

public interface Constantes {
    //NOME DAS TABELAS
    public static final String TABELA_USUARIO = "usuario";

    //CAMPOS DA TABELA USUARIO

    public static final String _ID = "ID";
    public static final String NOME = "NOME";
    public static final String CPF = "CPF";
    public static final String RG_UF = "RG_UF";
    public static final String RG_NR = "RG_NR";
    public static final String DT_NASCIMENTO = "DT_NASCIMENTO";
    public static final String DT_INCLUSAO = "DT_INCLUSAO";
    public static final String DT_ALTERACAO = "DT_ALTERACAO";
    public static final String DT_APROVACAO = "DT_APROVACAO";
    public static final String DT_ORDENACAO = "DT_ORDENACAO";
    public static final String APROVADO = "APROVADO";
    public static final String ATIVO = "ATIVO";
    public static final String ENDERECO_UF = "ENDERECO_UF";
    public static final String ENDERECO_CEP = "ENDERECO_CEP";
    public static final String _ENDERECO_LOGRADOURO = "ENDERECO_LOGRADOURO";
    public static final String ENDERECO_COMPLEMENTO = "ENDERECO_COMPLEMENTO";
    public static final String ENDERECO_BAIRRO = "ENDERECO_BAIRRO";
    public static final String ENDERECO_CIDADE = "ENDERECO_CIDADE";
    public static final String FIXO = "FIXO";
    public static final String CELULAR = "CELULAR";
    public static final String EMAIL = "EMAIL";
    //CREATE DAS TABELAS
    public static final String CREATE_USUARIO =
            "CREATE TABLE " + TABELA_USUARIO +"(\n" +
                    "\t " + _ID + " Text NOT NULL PRIMARY KEY,\n" +
                    "\t " + NOME + " Text NOT NULL,\n" +
                    "\t " + CPF + " Text NOT NULL,\n" +
                    "\t " + RG_UF + " Integer NOT NULL,\n" +
                    "\t " + RG_NR + " Text NOT NULL,\n" +
                    "\t " + DT_NASCIMENTO +" DateTime NOT NULL,\n" +
                    "\t " + DT_INCLUSAO + " DateTime NOT NULL,\n" +
                    "\t " + DT_ALTERACAO + " DateTime,\n" +
                    "\t " + DT_APROVACAO + " DateTime,\n" +
                    "\t " + DT_ORDENACAO + " DateTime,\n" +
                    "\t " + APROVADO  + " Boolean DEFAULT FALSE,\n" +
                    "\t " + ATIVO + " Boolean NOT NULL DEFAULT TRUE,\n" +
                    "\t " + ENDERECO_UF + " Text NOT NULL,\n" +
                    "\t " + ENDERECO_CEP +" Text NOT NULL,\n" +
                    "\t " +_ENDERECO_LOGRADOURO +" Text NOT NULL,\n" +
                    "\t " + ENDERECO_COMPLEMENTO +" Text,\n" +
                    "\t " + ENDERECO_BAIRRO + " Text NOT NULL,\n" +
                    "\t " + ENDERECO_CIDADE + " Text NOT NULL,\n" +
                    "\t " + FIXO + " Text,\n" +
                    "\t " + CELULAR + " Text NOT NULL,\n" +
                    "\t " + EMAIL + " Text,\n" +
                    "CONSTRAINT \"unique__ID\" UNIQUE ( "+ _ID +" ),\n" +
                    "CONSTRAINT \"unique_CPF\" UNIQUE ( "+ CPF +" ) );";


}
