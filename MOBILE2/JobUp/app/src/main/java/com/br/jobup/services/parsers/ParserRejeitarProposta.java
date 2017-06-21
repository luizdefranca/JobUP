package com.br.jobup.services.parsers;

import com.br.jobup.services.RetroFitInicializador;

import retrofit2.Call;

import static android.R.attr.id;

/*
 * Created by Luiz Carlos Ramos on 6/21/17 8:27 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/21/17 8:27 AM
 */

/**
 * Created by luizramos on 12/05/17.
 */

public class ParserRejeitarProposta {
    public static final String TAG = "LCFR/"+ParserRejeitarProposta.class.getSimpleName();

    private final String idProposta;
    private final String idUsuario;

    public ParserRejeitarProposta(String idProposta, String idUsuario){
        this.idProposta = idProposta;
        this.idUsuario = idUsuario;
    }
    public Call<Void> rejeitarServico(){
        Call<Void> servico = null;

        servico =  new RetroFitInicializador()
                .createRejeitarPropostaAPI()
                .rejeitarProposta(this.idProposta, this.idUsuario);
        return servico;
    }
}
