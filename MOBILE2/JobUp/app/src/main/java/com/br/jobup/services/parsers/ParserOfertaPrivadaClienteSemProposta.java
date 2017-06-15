package com.br.jobup.services.parsers;

import android.util.Log;

import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.services.RetroFitInicializador;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/*
 * Created by Luiz Carlos Ramos on 6/15/17 12:41 PM 
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/10/17 11:59 AM
 */

/**
 * Created by luizramos on 28/05/17.
 */

public class ParserOfertaPrivadaClienteSemProposta {
    public static final String TAG = "LCFR/"+ParserOfertaPrivadaClienteSemProposta.class.getSimpleName();

    private final String idUsuarioCliente;

    public ParserOfertaPrivadaClienteSemProposta(String idUsuarioCliente){
        this.idUsuarioCliente = idUsuarioCliente;
    }



    public List<ServicoOfertaPrivada> getAllPorIdUsuarioCliente(){
        List<ServicoOfertaPrivada> ofertaPrivadas = null;

            ofertaPrivadas =  new RetroFitInicializador()
                    .createServicoPrivadoClienteSemPropostaAPI()
                    .getAll(this.idUsuarioCliente);

        return ofertaPrivadas;
    }




}
