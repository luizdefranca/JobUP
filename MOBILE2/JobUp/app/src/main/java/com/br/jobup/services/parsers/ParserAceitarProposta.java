package com.br.jobup.services.parsers;

import com.br.jobup.services.RetroFitInicializador;

import retrofit2.Call;

/*
 * Created by Luiz Carlos Ramos on 6/21/17 11:13 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/21/17 8:34 AM
 */

/**
 * Created by luizramos on 12/05/17.
 */

public class ParserAceitarProposta {
    public static final String TAG = "LCFR/"+ParserAceitarProposta.class.getSimpleName();

    private final String idProposta;
    private final String idUsuario;

    public ParserAceitarProposta(String idProposta, String idUsuario){
        this.idProposta = idProposta;
        this.idUsuario = idUsuario;
    }
    public Call<Void> rejeitarServico(){
        Call<Void> servico = null;

        servico =  new RetroFitInicializador()
                .createAceitarPropostaAPI()
                .aceitarProposta(this.idProposta, this.idUsuario);
        return servico;
    }
}
