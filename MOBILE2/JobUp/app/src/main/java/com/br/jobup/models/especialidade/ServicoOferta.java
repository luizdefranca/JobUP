package com.br.jobup.models.especialidade;

/*
 * Created by Luiz Carlos Ramos on 12/05/17 02:56
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 12/05/17 02:56
 */

/*
  Created by luizramos on 12/05/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.br.jobup.models.servico.Proposta;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ServicoOferta implements Serializable, Parcelable {

    @SerializedName("ID_USUARIO")
    @Expose
    public String idUsuario;
    @SerializedName("NOME")
    @Expose
    public String nome;
    @SerializedName("DT_NASCTO")
    @Expose
    public Date dtNascimento;
    @SerializedName("ID_ESPECIALIDADE")
    @Expose
    public int idEspecialidade;
    @SerializedName("DESC_ESPECIALIDADE")
    @Expose
    public String descEspecialidade;
    @SerializedName("RESUMO_CURRICULO")
    @Expose
    public String resumoCurriculo;

    @SerializedName("DT_INCLUSAO")
    @Expose
    public Date dtInclusao;

    @SerializedName("BAIRRO")
    @Expose
    public String bairro;

    @SerializedName("CIDADE")
    @Expose
    public String cidade;

    @SerializedName("ESTADO")
    @Expose
    public String estado;

    @SerializedName("AVALIACAO")
    @Expose
    public int avaliacao;

    @Expose
    @SerializedName("PROPOSTAS")
    public List<Proposta> propostas;

    @SerializedName("MEDIA_AVALIACOES_FEITAS")
    public double mediaAvaliacoes;

    @SerializedName("QTD_PROPOSTAS_ACEITAS")
    public int qtPropostasAceitas;

    public ServicoOferta(String idUsuario, String nome, Date dtNascimento, int idEspecialidade, String descEspecialidade, String resumoCurriculo, String bairro, String cidade, String estado, double mediaAvaliacoes, int qtPropostasAceitas) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.idEspecialidade = idEspecialidade;
        this.descEspecialidade = descEspecialidade;
        this.resumoCurriculo = resumoCurriculo;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.mediaAvaliacoes = mediaAvaliacoes;
        this.qtPropostasAceitas = qtPropostasAceitas;
    }

    protected ServicoOferta(Parcel in) {
        idUsuario = in.readString();
        nome = in.readString();
        idEspecialidade = in.readInt();
        descEspecialidade = in.readString();
        resumoCurriculo = in.readString();
        bairro = in.readString();
        cidade = in.readString();
        estado = in.readString();
        mediaAvaliacoes = in.readDouble();
        qtPropostasAceitas = in.readInt();
    }

    public static final Creator<ServicoOferta> CREATOR = new Creator<ServicoOferta>() {
        @Override
        public ServicoOferta createFromParcel(Parcel in) {
            return new ServicoOferta(in);
        }

        @Override
        public ServicoOferta[] newArray(int size) {
            return new ServicoOferta[size];
        }
    };

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public int getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getDescEspecialidade() {
        return descEspecialidade;
    }

    public void setDescEspecialidade(String descEspecialidade) {
        this.descEspecialidade = descEspecialidade;
    }

    public String getResumoCurriculo() {
        return resumoCurriculo;
    }

    public void setResumoCurriculo(String resumoCurriculo) {
        this.resumoCurriculo = resumoCurriculo;
    }

    public Date getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }

    public void setMediaAvalaicoes() {
        this.mediaAvaliacoes = mediaAvaliacoes;
    }

    public int getQtPropostasAceitas() {
        return qtPropostasAceitas;
    }

    public void setQtPropostasAceitas() {
        this.qtPropostasAceitas = qtPropostasAceitas;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idUsuario);
        dest.writeString(nome);
        dest.writeInt(idEspecialidade);
        dest.writeString(descEspecialidade);
        dest.writeString(resumoCurriculo);
        dest.writeString(bairro);
        dest.writeString(cidade);
        dest.writeString(estado);
        dest.writeInt(qtPropostasAceitas);
        dest.writeDouble(mediaAvaliacoes);
    }
}