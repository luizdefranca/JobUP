package com.br.jobup.services.parsers;

import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.services.RetroFitInicializador;

import java.io.IOException;
import java.util.List;

/*
 * Created by Luiz Carlos Ramos on 6/16/17 12:22 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/15/17 11:10 PM
 */

/**
 * Created by luizramos on 28/05/17.
 */

public class ParserServicoPrivadoCliente {
    public static final String TAG = "LCFR/" + ParserServicoPrivadoCliente.class.getSimpleName();

    private final String idUsuarioCliente;

    public ParserServicoPrivadoCliente(String idUsuarioCliente) {
        this.idUsuarioCliente = idUsuarioCliente;
    }


    public List<ServicoOfertaPrivada> getAllPorIdUsuarioCliente() {
        List<ServicoOfertaPrivada> ofertaPrivadas = null;

        try {
            ofertaPrivadas = new RetroFitInicializador()
                    .createServicoPrivadoClienteAPI()
                    .getAll(this.idUsuarioCliente)
                    .execute()
                    .body();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ofertaPrivadas;
    }


}
