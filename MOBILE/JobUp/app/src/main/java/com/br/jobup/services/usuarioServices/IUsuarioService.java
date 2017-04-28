package com.br.jobup.services.usuarioServices;

import com.br.jobup.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by luizramos on 27/04/17.
 */

public interface IUsuarioService {
    public static final String USUARIO_URL = "usuario";

    @GET("/usuario")
    Call<List<Usuario>> getAllUsuarios(
    );

    @GET("/usuario/")
    Call<Usuario> getUsuario(
      @Query("id") String id
    );

    @POST("/usuario")
    Call<Usuario> insereUsuario(
            @Body Usuario usuario
    );

    @PUT("/usuario/{id}")
    Call<Usuario> atualizaUsuario(
            @Path("id") String id,
            @Body Usuario usuario
    );
}
