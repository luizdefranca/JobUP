package com.br.jobup.services.parsers;

/*
 * Created by Luiz Carlos Ramos on 12/05/17 03:08
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 12/05/17 03:08
 */

import android.util.Log;

import com.br.jobup.models.especialidade.EspecialidadeCatalogo;
import com.br.jobup.services.RetroFitInicializador;
import com.br.jobup.services.usuarioFullServices.UsuarioService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Created by luizramos on 12/05/17.
 */

public class ParserEspecialidadeCatalogo {
    public static final String TAG = "LCFR/"+ParserEspecialidadeCatalogo.class.getSimpleName();

    public static final String CONTROLE = "usuariofull";
    private final int idEspecialidade;

    public ParserEspecialidadeCatalogo(int idEspecialidade){
        this.idEspecialidade = idEspecialidade;
    }
    public Call<List<EspecialidadeCatalogo>> getAll(){
        Call<List<EspecialidadeCatalogo>> especialidadesCall = null;

            especialidadesCall =  new RetroFitInicializador()
                    .getEspecialidadesAPI()
                    .getAll(this.idEspecialidade);


        return especialidadesCall;
    }
}
