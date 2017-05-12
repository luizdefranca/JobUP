package com.br.jobup.services.usuarioFullServices;

import com.br.jobup.models.usuario.Usuario;
import com.br.jobup.services.RetroFitInicializador;
import com.br.jobup.services.interfaces.IUsuarioAPI;

import java.util.List;

import retrofit2.Call;

/**
 * Created by luizramos on 28/04/17.
 */

public class ParserUsuario {
    public static final String CONTROLE = "usuariofull";
    Class  T = IUsuarioAPI.class;

    static public Usuario getAll(){
        Call<List<Usuario>> usuarioCall = new RetroFitInicializador().createUsuarioFullAPI().getAll();
        UsuarioService service = new UsuarioService();
//        usuarioCall.enqueue( service.getAll());

        return null;
    }

}
