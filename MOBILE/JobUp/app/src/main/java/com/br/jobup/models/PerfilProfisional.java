package com.br.jobup.models;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by luizramos on 09/04/17.
 */

class PerfilProfisional {

    private LocalDate dataAprovacao;
    private boolean aprovado;
    private String resumoCurrriculo;
    private Especialidade especialidade;
    private List<Avaliacoes> avaliacoes;
    private List<Formacao> formacoes;

}
