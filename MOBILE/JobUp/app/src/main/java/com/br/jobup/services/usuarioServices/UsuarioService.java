package com.br.jobup.services.usuarioServices;

import android.util.Log;

import com.br.jobup.models.Usuario;
import com.br.jobup.services.RetroFitInicializador;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by luizramos on 27/04/17.
 */

public class UsuarioService  {
    public static final String TAG = UsuarioService.class.getSimpleName();

    Call<List<Usuario>> usuariosCall;
    public List<Usuario> getAllUsuarios() {
        final List<Usuario>[] usuarios = new List<Usuario>[1];
        usuariosCall = (Call<List<Usuario>>) new RetroFitInicializador();
        usuariosCall.enqueue(new Callback<List<Usuario>>() {



            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                usuarios[0] = response.body();
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString() );
            }
        });

        return ;
    }

    public Call<Usuario> getUsuario(@Query("id") String id) {
        return null;
    }

    public Call<Usuario> insereUsuario(@Body Usuario usuario) {
        return null;
    }

    public Call<Usuario> atualizaUsuario(@Path("id") String id, @Body Usuario usuario) {
        return null;
    }
}
