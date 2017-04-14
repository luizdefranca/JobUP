package com.br.jobup.models;





import org.joda.time.LocalDate;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by luizramos on 09/04/17.
 */

public class PerfilProfisional extends RealmObject{

    private Date dataAprovacao;
    private boolean aprovado;
    private String resumoCurrriculo;
    private Especialidade especialidade;
    private RealmList<Avaliacoes> avaliacoes;
    private RealmList<Formacao> formacoes;

    public PerfilProfisional() {
    }

    public Date getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(Date dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public String getResumoCurrriculo() {
        return resumoCurrriculo;
    }

    public void setResumoCurrriculo(String resumoCurrriculo) {
        this.resumoCurrriculo = resumoCurrriculo;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public RealmList<Avaliacoes> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(RealmList<Avaliacoes> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public RealmList<Formacao> getFormacoes() {
        return formacoes;
    }

    public void setFormacoes(RealmList<Formacao> formacoes) {
        this.formacoes = formacoes;
    }
}
