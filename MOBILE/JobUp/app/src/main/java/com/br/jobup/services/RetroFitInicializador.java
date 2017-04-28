package com.br.jobup.services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by luizramos on 27/04/17.
 */

public class RetroFitInicializador {

    String API_BASE_URL = "http://jobapi.azurewebsites.net/api/";

    private final Retrofit retrofit;
    public RetroFitInicializador(){

        retrofit = builder
                        .client(httpClient.build())
                        .build();
    }

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    );

// para usar conforme uma classe model

//    IUsuarioService usuarioService = retrofit.create(IUsuarioService.class);
}
