package com.br.jobup.services.parsers;

import com.br.jobup.models.especialidade.ServicoOferta;
import com.br.jobup.services.RetroFitInicializador;

import java.util.List;

import retrofit2.Call;

/*
 * Created by Luiz Carlos Ramos on 6/18/17 1:20 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/10/17 11:59 AM
 */

/**
 * Created by luizramos on 12/05/17.
 */

public class ParserRejeitarServico {
    public static final String TAG = "LCFR/"+ParserRejeitarServico.class.getSimpleName();

    private final String id;

    public ParserRejeitarServico(String id){
        this.id = id;
    }
    public Call<Void> rejeitarServico(){
        Call<Void> servico = null;

        servico =  new RetroFitInicializador()
                .createRejeitarServicoAPI()
                .rejeitarServico(this.id);
        return servico;
    }
}
