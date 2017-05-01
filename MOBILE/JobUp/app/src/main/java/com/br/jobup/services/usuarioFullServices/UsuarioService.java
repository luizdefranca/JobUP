package com.br.jobup.services.usuarioFullServices;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.br.jobup.models.Usuario;
import com.br.jobup.services.RetroFitInicializador;

import java.util.ArrayList;
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

public class UsuarioService   {
    public static final String TAG = UsuarioService.class.getSimpleName();

    Activity activity;
    Call<List<Usuario>> usuariosCall;
    Call<Usuario> usuarioCall;
    public static final String USUARIO_URL = "usuariofull";

    public void getAllUsuarios(Context context) {
        final List<Usuario> usuarios = new ArrayList<>();
        usuariosCall =  new RetroFitInicializador().createUsuarioAPI().getAll();
        usuariosCall.enqueue(new Callback<List<Usuario>>() {



            @Override
            public void onResponse( Call<List<Usuario>> call, Response<List<Usuario>> response) {
                List<Usuario> body = response.body();
                usuarios.addAll(response.body());
                Usuario u = usuarios.get(0);

            }


            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString() );
            }
        });


    }

    public Usuario getUsuario(@Query("id") String id) {

        final Usuario[] usuario = new Usuario[1];
        usuarioCall = (Call<Usuario>) new RetroFitInicializador();
        usuarioCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                usuario[0] = response.body();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });

        return usuario[0];
    }

    public Call<Usuario> insereUsuario(@Body Usuario usuario) {
        return null;
    }

    public Call<Usuario> atualizaUsuario(@Path("id") String id, @Body Usuario usuario) {
        return null;
    }

}



//






























































//    Call<List<Usuario>> usuarioService = new RetroFitInicializador().createUsuarioService().getAll();
//        usuarioService.enqueue(new Callback<List<Usuario>>() {
//@Override
//public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
//        Log.d(TAG, "onResponse - metodo para confirmar: " + response.message());
//
//        List<Usuario> usuarios = response.body();
//        Usuario usuario = usuarios.get(0);
//        Log.e(TAG, "usuario: " + usuario.getNome());
//        }
//
//@Override
//public void onFailure(Call call, Throwable t) {
//
//        }
//        });