package com.br.jobup.services;



import com.br.jobup.models.servico.Oferta;
import com.br.jobup.models.servico.Proposta;
import com.br.jobup.models.servico.Servico;
import com.br.jobup.models.usuario.Avaliacao;
import com.br.jobup.models.usuario.Especialidade;
import com.br.jobup.models.usuario.Formacao;
import com.br.jobup.models.usuario.SubEspecialidade;
import com.br.jobup.models.usuario.Usuario;

import org.w3c.dom.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * Created by Luiz Carlos Ramos on 22/05/17 03:19
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 21/05/17 21:36
 */

public interface QuickStartService {



    @GET("oferta")
    Call<List<Oferta>> listOfertas(@Query("idUsuario") String idUsuario);

    @GET("proposta")
    Call<List<Proposta>> listPropostas();

    @GET("servicopublico")
    Call<List<Servico>> listServicosPublicos();

    @GET("servicoprivado")
    Call<List<Servico>> listServicosPrivados(@Query("idUsuarioProfissional") String idUsuario);

    @GET("avaliacao")
    Call<List<Avaliacao>> listAvaliacoes(@Query("idUsuario") String idUsuario,
                                         @Query("idEspecialidade") int idEspecialidade);

    @GET("usuario")
    Call<List<Usuario>> listUsuarios();

    @GET("perfilprofissional")
    Call<List<Especialidade>> listPerfisProfissionais(@Query("idUsuario") long idUsuario);

    @GET("especialidade")
    Call<List<Especialidade>> listEspecialidades();

    @GET("subespecialidade")
    Call<List<SubEspecialidade>> listSubEspecialidades();

    @GET("formacao")
    Call<List<Formacao>> listFormacoes(@Query("idUsuario") String idUsuario,
                                       @Query("idEspecialidade") int idEspecialidade);
}
