package com.br.jobup.models;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by luizramos on 09/04/17.
 */

public class Especialidade extends RealmObject {
    private String descricao;
    private boolean exigirComprovacao;
    private RealmList<PerfilProfisional> perfisProfisional;

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

    public RealmList<PerfilProfisional> getPerfisProfisional() {
        return perfisProfisional;
    }

    public void setPerfisProfisional(RealmList<PerfilProfisional> perfisProfisional) {
        this.perfisProfisional = perfisProfisional;
    }
}
