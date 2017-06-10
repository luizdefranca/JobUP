package com.br.jobup.services.parsers;

/*
 * Created by Luiz Carlos Ramos on 27/05/17 02:36
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 27/05/17 02:36
 */

import com.br.jobup.models.builder.PerfilProfisionalBuilder;
import com.br.jobup.models.usuario.PerfilProfissional;
import com.br.jobup.services.RetroFitInicializador;

import java.util.List;

import retrofit2.Call;

/**
 * Created by luizramos on 27/05/17.
 */

public class ParserPerfilProfissional {

    public static final String TAG = "LCFR / " + ParserPerfilProfissional.class.getSimpleName();
    private final String idUsuario;

    public ParserPerfilProfissional(String idUsuario){
        this.idUsuario = idUsuario;
    }

    public Call<List<PerfilProfissional>> getComIdUsuario(){
        Call<List<PerfilProfissional>> perfis = new RetroFitInicializador()
                .createPerfilProfissionalAPI()
                .getPorIdUsuario(this.idUsuario);

        return perfis;
    }

}
