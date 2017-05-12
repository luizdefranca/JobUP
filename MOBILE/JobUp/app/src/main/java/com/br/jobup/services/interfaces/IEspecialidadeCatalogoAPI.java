package com.br.jobup.services.interfaces;

/*
 * Created by Luiz Carlos Ramos on 12/05/17 03:01
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 12/05/17 03:01
 */

import com.br.jobup.models.especialidade.EspecialidadeCatalogo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by luizramos on 12/05/17.
 */

public interface IEspecialidadeCatalogoAPI {
    public static final String PATH = "perfilprofissional/";


    @GET(PATH)
    Call<List<EspecialidadeCatalogo>> getAll(
            @Query("idEspecialidade") int idEspecialidade
    );
}
