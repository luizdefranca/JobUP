package com.br.jobup.services.parsers;

/*
 * Created by Luiz Carlos Ramos on 16/06/17 00:25
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 16/06/17 00:25
 */

import android.util.Log;

import com.br.jobup.models.servico.Oferta;
import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.services.RetroFitInicializador;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Created by andre on 16/06/2017.
 */

public class ParserAvaliarProfissional {

    public static final String TAG = "LCFR/"+ParserAvaliarProfissional.class.getSimpleName();

    private final String idUsuario;

    public ParserAvaliarProfissional(String idUsuario){
        this.idUsuario = idUsuario;
    }



    public Call<Void> post(ServicoOfertaPrivada avaliacao) {
        final Call<Void> callPost = new RetroFitInicializador()
                .createServicoPrivadoAPI()
                .post(avaliacao);

        return callPost;
    }


}
