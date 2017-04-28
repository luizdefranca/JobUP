package com.br.jobup.models.builder;

import com.br.jobup.models.Avaliacoes;
import com.br.jobup.models.Especialidade;
import com.br.jobup.models.Formacao;
import com.br.jobup.models.PerfilProfisional;
import com.br.jobup.util.Parsers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luizramos on 27/04/17.
 */

public class PerfilProfisionalBuilder {

    private PerfilProfisional perfilProfisional;

    private Date dataAprovacao;
    private boolean aprovado;
    private String resumoCurriculo;
    private Especialidade especialidade;
    private List<Avaliacoes> avaliacoes;
    private List<Formacao> formacoes;

    public PerfilProfisionalBuilder(){
        List<Avaliacoes> avaliacoes = new ArrayList<>();
        List<Formacao> formacoes = new ArrayList<>();
    }

    public PerfilProfisionalBuilder DtAprovacao(String data){
        this.dataAprovacao = Parsers.parseStringToDataNormal(data);
        return this;
    }

    public PerfilProfisionalBuilder Aprovado(boolean aprovado){
        this.aprovado = aprovado;
        return this;
    }
    public PerfilProfisionalBuilder ResumoCurriculo(String resumoCurriculo){
        this.resumoCurriculo = resumoCurriculo;
        return this;
    }
    public PerfilProfisionalBuilder Especialidade(String descricao, boolean exigirComprovacao){
        this.especialidade = new Especialidade(descricao, exigirComprovacao);
        return this;
    }

    /*
    private Date dataUltimaAvaliacao;
    private byte nota;
    private String comentario;
     */
    public PerfilProfisionalBuilder Avaliacoes(String data, int nota, String comentario){
        Date dataFormatada = Parsers.parseStringToDataNormal(data);
        this.avaliacoes.add(new Avaliacoes(dataFormatada, nota, comentario ));
        return this;
    }

    public PerfilProfisionalBuilder Formacoes(Formacao formacao){
        FormacaoBuilder f = new FormacaoBuilder();
        this.formacoes.add(formacao);
        return this;
    }

    public PerfilProfisional build(){
        return perfilProfisional;
    }
}
