package br.com.workup.jobup.models;


import java.util.Date;
import java.util.List;


/**
 * Created by luizramos on 09/04/17.
 */

public class PerfilProfisional {

    private Date dataAprovacao;
    private boolean aprovado;
    private String resumoCurrriculo;
    private Especialidade especialidade;
    private List<Avaliacoes> avaliacoes;
    private List<Formacao> formacoes;

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

    public List<Avaliacoes> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacoes> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Formacao> getFormacoes() {
        return formacoes;
    }

    public void setFormacoes(List<Formacao> formacoes) {
        this.formacoes = formacoes;
    }
}
