package com.br.jobup.models.builder;

import com.br.jobup.models.usuario.Formacao;
import com.br.jobup.util.Parsers;

import java.util.Date;

/**
 * Created by luizramos on 27/04/17.
 */

public class FormacaoBuilder {

    private Formacao formacao;

    private String instituicao;
    private String nomeCurso;
    private int anoFormacao;
    private Date dataAprovacao;
    private boolean aprovado;


    public FormacaoBuilder(){}

    public FormacaoBuilder Instituicao(String instituicao){
        this.instituicao = instituicao;
        return this;
    }

    public FormacaoBuilder NomeCurso(String nomeCurso){
        this.nomeCurso = nomeCurso;
        return this;
    }
    public FormacaoBuilder AnoFormacao(int anoFormacao) {
        this.anoFormacao = anoFormacao;
        return this;
    }
    public FormacaoBuilder DataAprovacao(String dataAprovacao){
        this.dataAprovacao = Parsers.parseStringToDataNormal(dataAprovacao);;
        return this;
    }
    public FormacaoBuilder Aprovado(boolean aprovado){
        this.aprovado = aprovado;
        return this;
    }

    public Formacao build(){
        return formacao;
    }


}
