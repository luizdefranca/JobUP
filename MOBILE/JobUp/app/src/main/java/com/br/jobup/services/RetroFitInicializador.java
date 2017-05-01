package com.br.jobup.services;

import com.br.jobup.services.usuarioServices.interfaces.IUsuarioService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by luizramos on 27/04/17.
 */

public class RetroFitInicializador {

    String API_BASE_URL = "http://jobapi.azurewebsites.net/api/";

    private final  Retrofit retrofit;

    public  RetroFitInicializador() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory
                        .create(gson))
                        .build();
    }



    //    public RetroFitInicializador(){
//
//        retrofit = builder
//                        .client(httpClient.build())
//                        .build();
//    }
//
//    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//
//    Retrofit.Builder builder =
//            new Retrofit.Builder()
//                    .baseUrl(API_BASE_URL)
//                    .addConverterFactory(
//                            GsonConverterFactory.create()
//                    );

    //RetroFitInicializador client =  retrofit.create(RetroFitInicializador.class);



// para usar conforme uma classe model

//    IUsuarioService usuarioService = retrofit.create(IUsuarioService.class);

    public IUsuarioService createUsuarioService(){
        return retrofit.create(IUsuarioService.class);
    }


    public IUsuarioRetrofit apiRetrofit(){
        return retrofit.create(IUsuarioRetrofit.class);
    }
}
