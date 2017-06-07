package com.br.jobup.services.parsers;

/*
 * Created by Luiz Carlos Ramos on 28/05/17 20:56
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 28/05/17 20:56
 */

import android.util.Log;

import com.br.jobup.models.servico.Oferta;
import com.br.jobup.models.servico.ServicoOfertaPrivada;
import com.br.jobup.services.RetroFitInicializador;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Created by luizramos on 28/05/17.
 */

public class ParserOfertaPrivada {
    public static final String TAG = "LCFR/"+ParserOfertaPrivada.class.getSimpleName();

    private final String idUsuario;

    public ParserOfertaPrivada(String idUsuario){
        this.idUsuario = idUsuario;
    }



    public List<Oferta> getAllPorIdUsuario(){
        List<Oferta> ofertaPrivadas = null;

        try {
            ofertaPrivadas =  new RetroFitInicializador()
                    .createOfertaAPI()
                    .getOfertas(this.idUsuario)
                    .execute()
                    .body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "getAllPorIdUsuario: ",e );
        }


        return ofertaPrivadas;
    }

    public Call<Void> post(ServicoOfertaPrivada ofertaPrivada) {
        final Call<Void> callPost = new RetroFitInicializador()
                .createServicoPrivadoAPI()
                .post(ofertaPrivada);

        return callPost;
    }


}
