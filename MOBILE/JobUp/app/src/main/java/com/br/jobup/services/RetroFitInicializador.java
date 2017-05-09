package com.br.jobup.services;

import com.br.jobup.services.interfaces.IAprovarAPI;
import com.br.jobup.services.interfaces.IAvaliacaoAPI;
import com.br.jobup.services.interfaces.IClienteAPI;
import com.br.jobup.services.interfaces.IDesativarAPI;
import com.br.jobup.services.interfaces.IUsuarioSignInAPI;
import com.br.jobup.services.interfaces.IUsuarioAPI;
import com.br.jobup.services.interfaces.IUsuarioFullAPI;
import com.br.jobup.services.interfaces.IUsuarioSignUpAPI;
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

//    IUsuarioAPI usuarioService = retrofit.create(IUsuarioAPI.class);

    public IUsuarioAPI createUsuarioAPI(){
        return retrofit.create(IUsuarioAPI.class);
    }

    public IAprovarAPI createAprovarAPI(){
        return retrofit.create(IAprovarAPI.class);
    }

    public IAvaliacaoAPI createAvaliacaoAPI(){
        return retrofit.create(IAvaliacaoAPI.class);
    }

    public IClienteAPI createClienteAPI(){
        return retrofit.create(IClienteAPI.class);
    }

    public IDesativarAPI createDesativarAPI(){
        return retrofit.create(IDesativarAPI.class);
    }

    public IUsuarioFullAPI createUsuarioFullAPI(){
        return retrofit.create(IUsuarioFullAPI.class);
    }

    public IUsuarioRetrofit apiRetrofit(){
        return retrofit.create(IUsuarioRetrofit.class);
    }

    public IUsuarioSignInAPI loginAPI(){
        return retrofit.create(IUsuarioSignInAPI.class);
    }

    public IUsuarioSignUpAPI signUpAPI(){ return retrofit.create(IUsuarioSignUpAPI.class);}
}
