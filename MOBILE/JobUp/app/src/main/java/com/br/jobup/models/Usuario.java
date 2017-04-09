package com.br.jobup.models;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by luizramos on 09/04/17.
 */

public class Usuario {

    private String nome;
    private Cpf cpf;
    private Rg rg;
    private LocalDate dataNascimento;
    private LocalDate dataInclusao;
    private LocalDate dataAlteracao;
    private LocalDate dataAprovacao;
    private LocalDate dataOrdenacao;
    private boolean aprovado;
    private Endereco endereco;
    private Contato contato;
    private List<PerfilProfisional> perfisProfissionais;


}
