package com.br.jobup.services.usuarioServices;

import com.br.jobup.models.Usuario;
import com.br.jobup.services.RetroFitInicializador;
import com.br.jobup.services.usuarioServices.interfaces.IUsuarioService;

import java.util.List;

import retrofit2.Call;

/**
 * Created by luizramos on 28/04/17.
 */

public class ParserUsuario {
    public static final String CONTROLE = "usuariofull";
    Class  T = IUsuarioService.class;

    static public Usuario getAll(){
        Call<List<Usuario>> usuarioCall = new RetroFitInicializador().createUsuarioService().getAllUsuarios();
        UsuarioService service = new UsuarioService();
//        usuarioCall.enqueue( service.getAllUsuarios());

        return null;
    }

}
