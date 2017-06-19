package com.br.jobup.models.especialidade;

/*
 * Created by Luiz Carlos Ramos on 6/10/17 11:32 PM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/10/17 11:32 PM
 */

import android.os.Parcel;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by luizramos on 10/06/17.
 */

public class ServicoOfertaOrdenadoPorAvaliacao extends ServicoOferta implements Comparable<ServicoOfertaOrdenadoPorAvaliacao> {
    public ServicoOfertaOrdenadoPorAvaliacao(String idUsuario, String nome, Date dtNascimento, int idEspecialidade, String descEspecialidade, String resumoCurriculo, String bairro, String cidade, String estado, double mediaAvaliacoes , int qtPropostasAceitas) {
        super(idUsuario, nome, dtNascimento, idEspecialidade, descEspecialidade, resumoCurriculo, bairro, cidade, estado, mediaAvaliacoes , qtPropostasAceitas);
    }

    protected ServicoOfertaOrdenadoPorAvaliacao(Parcel in) {
        super(in);
    }

    @Override
    public int compareTo(@NonNull ServicoOfertaOrdenadoPorAvaliacao o) {
        if (this.avaliacao > o.avaliacao)
            return 1;
        else if (this.avaliacao < o.avaliacao)
            return -1;
        else return 0;
    }

}