package br.com.workup.jobup.models;


import java.util.List;

/**
 * Created by luizramos on 09/04/17.
 */

public class Especialidade {
    private String descricao;
    private boolean exigirComprovacao;
    private List<PerfilProfisional> perfisProfisional;

    public Especialidade() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isExigirComprovacao() {
        return exigirComprovacao;
    }

    public void setExigirComprovacao(boolean exigirComprovacao) {
        this.exigirComprovacao = exigirComprovacao;
    }

    public List<PerfilProfisional> getPerfisProfisional() {
        return perfisProfisional;
    }

    public void setPerfisProfisional(List<PerfilProfisional> perfisProfisional) {
        this.perfisProfisional = perfisProfisional;
    }
}
