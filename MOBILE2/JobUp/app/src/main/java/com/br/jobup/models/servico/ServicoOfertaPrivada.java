package com.br.jobup.models.servico;

/*
 * Created by Luiz Carlos Ramos on 28/05/17 20:16
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 28/05/17 20:16
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by luizramos on 28/05/17.
 */

public class ServicoOfertaPrivada implements Serializable, Parcelable{

    @SerializedName("ID_SERVICO")
    protected String idServico;

    @SerializedName("ID_USUARIO")
    protected String idUsuario;

    @SerializedName("ID_PROFISSIONAL")
    protected String idProfissional;

    @SerializedName("NOME")
    protected String nome;

    @SerializedName("ID_ESPECIALIDADE")
    protected int idEspecialidade;

    @SerializedName("DESC_ESPECIALIDADE")
    protected String descEspecialidade;

    @SerializedName("DT_CADASTRO")
    protected Date dtCadastro;

    @SerializedName("DS_TITULO")
    protected String titulo;

    @SerializedName("DS_OBSERVACOES")
    protected String observacoes;

    @SerializedName("TEMPO_SERVICO")
    protected int tpServico;

    @SerializedName("VL_SUGERIDO")
    protected double valorSugerido;

    @SerializedName("TEMPO_SERVICO_DESC")
    protected String desTempoServico;

    @SerializedName("PROPOSTAS")
    protected List<Proposta> propostas;

    public ServicoOfertaPrivada(String idUsuario,
                                String idProfissional,
                            //    String nome,
                                int idEspecialidade,
                                String titulo,
                                String observacoes,
                                int tpServico,
                                double valorSugerido) {
        this.idUsuario = idUsuario;
        this.idProfissional = idProfissional;
        this.idEspecialidade = idEspecialidade;
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.tpServico = tpServico;
        this.valorSugerido = valorSugerido;
     //   this.nome = nome;
    }



    protected ServicoOfertaPrivada(Parcel in) {
        idServico = in.readString();
        idUsuario = in.readString();
        idProfissional = in.readString();
        idEspecialidade = in.readInt();
        titulo = in.readString();
        observacoes = in.readString();
        tpServico = in.readInt();
        valorSugerido = in.readDouble();
    }

    public static final Creator<ServicoOfertaPrivada> CREATOR = new Creator<ServicoOfertaPrivada>() {
        @Override
        public ServicoOfertaPrivada createFromParcel(Parcel in) {
            return new ServicoOfertaPrivada(in);
        }

        @Override
        public ServicoOfertaPrivada[] newArray(int size) {
            return new ServicoOfertaPrivada[size];
        }
    };

    public ServicoOfertaPrivada() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescEspecialidade() {
        return descEspecialidade;
    }

    public void setDescEspecialidade(String descEspecialidade) {
        this.descEspecialidade = descEspecialidade;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public String getDesTempoServico() {
        return desTempoServico;
    }

    public void setDesTempoServico(String desTempoServico) {
        this.desTempoServico = desTempoServico;
    }

    public List<Proposta> getPropostas() {
        return propostas;
    }

    public void setPropostas(List<Proposta> propostas) {
        this.propostas = propostas;
    }

    public String getIdServico() {
        return idServico;
    }

    public void setIdServico(String idServico) {
        this.idServico = idServico;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(String idProfissional) {
        this.idProfissional = idProfissional;
    }

    public int getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getTpServico() {
        return tpServico;
    }

    public void setTpServico(int tpServico) {
        this.tpServico = tpServico;
    }

    public double getValorSugerido() {
        return valorSugerido;
    }

    public void setValorSugerido(double valorSugerido) {
        this.valorSugerido = valorSugerido;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idServico);
        dest.writeString(idUsuario);
        dest.writeString(idProfissional);
        dest.writeInt(idEspecialidade);
        dest.writeString(titulo);
        dest.writeString(observacoes);
        dest.writeInt(tpServico);
        dest.writeDouble(valorSugerido);
    }
}
