package com.br.jobup.services.parsers;

import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.services.RetroFitInicializador;

import java.util.List;

import retrofit2.Call;

/*
 * Created by Luiz Carlos Ramos on 6/21/17 5:08 PM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/21/17 12:57 PM
 */

/**
 * Created by luizramos on 12/05/17.
 */

public class ParserServicoPrivadoCliente2 {
    public static final String TAG = "LCFR/"+ParserServicoPrivadoCliente2.class.getSimpleName();

    private final String idUsuario;

    public ParserServicoPrivadoCliente2( String idUsuario){
        this.idUsuario = idUsuario;
    }
    public Call<List<ServicoOfertaPrivada>> rejeitarServico(){
        Call<List<ServicoOfertaPrivada>> servico = null;

        servico =  new RetroFitInicializador()
                .createServicoPrivadoAPI2()
                .getAll( this.idUsuario);
        return servico;
    }
}
