package com.br.jobup.services.usuarioServices.interfaces;

/*
 * Created by Luiz Carlos Ramos on 01/05/17 00:40 at $today.hour24:40:33.
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 01/05/17 00:40
 */

import com.br.jobup.models.Especialidade;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by luizramos on 01/05/17.
 */

public interface IEspecialidade {
    public static final String PATH = "especialide/";


    @GET(PATH)
    Call<List<Especialidade>> getAll(
    );

    @GET(PATH)
    Call<Especialidade> get(
            @Query("ID_ESPECIALIDADE") int idEspecialidade
    );

    @POST(PATH)
    Call<Especialidade> post(
            @Body Especialidade especialidade
    );

    @PUT(PATH+"{id}")
    Call<Especialidade> put(
            @Path("ID_ESPECIALIDADE") int idEspecialidade,
            @Body Especialidade especialide
    );

    @DELETE(PATH+"{id}")
    Call<Especialidade> delete(
            @Path("ID_ESPECIALIDADE") int idEspecialidade

    );
}
