package com.br.jobup.models.usuario;


import android.os.Parcel;
import android.os.Parcelable;

import com.br.jobup.util.Parsers;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/*
 * Created by Luiz Carlos Ramos on 12/05/17 02:52
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 03/05/17 18:11
 */

/**
 * Created by luizramos on 09/04/17.
 */

public class PerfilProfissional  implements Serializable, Parcelable {

    @SerializedName("ID_USUARIO")
    @Expose
    private String idUsuario;

    @SerializedName("ID_ESPECIALIDADE")
    @Expose
    private int idEspecialidade;

    @SerializedName("DT_APROVACAO")
    @Expose
    private Date dataAprovacao;

    @SerializedName("APROVADO")
    @Expose
    private boolean aprovado;

    @SerializedName("RESUMO_CURRICULO")
    @Expose
    private String resumoCurriculo;


    @SerializedName("ESPECIALIDADE")
    @Expose
    private Especialidade especialidade;




    public PerfilProfissional() {
    }

    protected PerfilProfissional(Parcel in) {
        idUsuario = in.readString();
        idEspecialidade = in.readInt();
        aprovado = in.readByte() != 0;
        resumoCurriculo = in.readString();
    }

    public static final Creator<PerfilProfissional> CREATOR = new Creator<PerfilProfissional>() {
        @Override
        public PerfilProfissional createFromParcel(Parcel in) {
            return new PerfilProfissional(in);
        }

        @Override
        public PerfilProfissional[] newArray(int size) {
            return new PerfilProfissional[size];
        }
    };

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public Date getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(Date dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }
    public void setDataAprovacao(String dataAprovacao) {
        this.dataAprovacao = Parsers.parseStringToDataAPI(dataAprovacao);
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public String getResumoCurriculo() {
        return resumoCurriculo;
    }

    public void setResumoCurriculo(String resumoCurriculo) {
        this.resumoCurriculo = resumoCurriculo;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idUsuario);
        dest.writeInt(idEspecialidade);
        dest.writeByte((byte) (aprovado ? 1 : 0));
        dest.writeString(resumoCurriculo);
    }
}
