package br.com.workup.jobup.models;

import java.util.Date;


/**
 * Created by luizramos on 09/04/17.
 */

public class Avaliacoes  {
    private Date dataUltimaAvaliacao;
    private byte nota;
    private String comentario;

    public Avaliacoes() {
    }

    public Date getDataUltimaAvaliacao() {
        return dataUltimaAvaliacao;
    }

    public void setDataUltimaAvaliacao(Date dataUltimaAvaliacao) {
        this.dataUltimaAvaliacao = dataUltimaAvaliacao;
    }

    public byte getNota() {
        return nota;
    }

    public void setNota(byte nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
