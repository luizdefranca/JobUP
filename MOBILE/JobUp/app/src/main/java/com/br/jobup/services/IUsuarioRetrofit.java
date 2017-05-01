package com.br.jobup.services;

import com.br.jobup.models.Usuario;

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
 * Created by luizramos on 29/04/17.
 */

interface IUsuarioRetrofit {


    @GET("{controle}")
    Call<List<Usuario>> getAllUsuarios(
            @Path("controle") String controle
    );

    @GET("{controle}/")
    Call<Usuario> getUsuario(
            @Path("controle") String controle,
            @Query("id") String id
    );

    @POST("{controle}")
    Call<Usuario> insereUsuario(
            @Path("controle") String controle,
            @Body Usuario usuario
    );

    @PUT("{controle}/{id}")
    Call<Usuario> atualizaUsuario(
            @Path("controle") String controle,
            @Path("id") String id,
            @Body Usuario usuario
    );

    @DELETE("{controle}/{id}")
    Call<Usuario> deletaUsuario(
            @Path("controle") String controle,
            @Path("id") String id

    );
}
