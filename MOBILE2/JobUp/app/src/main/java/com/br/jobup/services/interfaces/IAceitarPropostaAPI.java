package com.br.jobup.services.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/*
 * Created by Luiz Carlos Ramos on 6/21/17 11:12 AM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/21/17 9:52 AM
 */

/**
 * Created by luizramos on 21/06/17.
 */

public interface IAceitarPropostaAPI {
    public static final String PATH = "AceitarProposta/";

    @GET(PATH + "{id}")
    Call<Void> aceitarProposta(
            @Path("id") String idServico,
            @Query("ID_USUARIO") String idUsuario
    );
}
