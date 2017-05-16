package com.br.jobup.models.model_new;

import java.io.Serializable;
import java.util.Date;


/*
 * Created by Luiz Carlos Ramos on 06/05/17 16:22
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 30/04/17 05:56
 */

/**
 * Created by luizramos on 09/04/17.
 */

public class Avaliacoes  implements Serializable {
    private Date dataUltimaAvaliacao;
    private int nota;
    private String comentario;

    public Avaliacoes() {
    }

    public Avaliacoes(Date dataUltimaAvaliacao, int nota, String comentario) {
        this.dataUltimaAvaliacao = dataUltimaAvaliacao;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Date getDataUltimaAvaliacao() {
        return dataUltimaAvaliacao;
    }

    public void setDataUltimaAvaliacao(Date dataUltimaAvaliacao) {
        this.dataUltimaAvaliacao = dataUltimaAvaliacao;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
