package com.br.jobup.adapters;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.br.jobup.R;
import com.br.jobup.models.especialidade.ServicoOferta;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by Luiz Carlos Ramos on 12/05/17 02:23
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 08/05/17 08:31
 */

public class CatalogoEspecialidadeAdapter extends BaseAdapter implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private final List<ServicoOferta> especialidadesList;

    private final Context context;

    LatLng ptOrigem;
    LatLng ptFinal;

    GoogleApiClient mGoogleApiClient;

    DialogFragment errorFragment;
    private final int REQUEST_RESOLVE_GOOGLE_CLIENT_ERROR = 1;
    boolean mResolvingError;

    public CatalogoEspecialidadeAdapter(Context context, List<ServicoOferta> especialidadesCatalogo) {
        if(especialidadesCatalogo == null) {
            this.especialidadesList = new ArrayList<>();
        } else {
            this.especialidadesList = especialidadesCatalogo;
        }
        this.context = context;
        setupGoogleApiClient(context);
        obterUltimaLocalizacao();
    }

    protected synchronized void setupGoogleApiClient(Context context) {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ServicoOferta especialidade = especialidadesList.get(position);


        View view = LayoutInflater.from(this.context).inflate(R.layout.row_catalogo_especialidade, null);
        TextView txtNome = (TextView) view.findViewById(R.id.item_especialidade_txtNome);
//        TextView txtDtNascimento        = (TextView) view.findViewById(R.id.item_especialidade_txtDtNascimento);
        TextView txtDescEspecialidade = (TextView) view.findViewById(R.id.item_especialidade_txtDescricao);
        TextView txtResumoCurriculo = (TextView) view.findViewById(R.id.item_especialidade_txtCurriculo);
//        TextView txtDtInclusao          = (TextView) view.findViewById(R.id.item_especialidade_txtUsuarioDesde) ;
        TextView txtBairro = (TextView) view.findViewById(R.id.item_especialidade_txtBairro);
        TextView txtCidade = (TextView) view.findViewById(R.id.item_especialidade_txtCidade);
        TextView txtEstado = (TextView) view.findViewById(R.id.item_especialidade_txtEstado);
        TextView txtNumCon = (TextView) view.findViewById(R.id.item_especialidade_txtNumCon);
        TextView txtMedAv = (TextView) view.findViewById(R.id.item_especialidade_txtMediaAv);

        txtNome.setText(especialidade.getNome());
//        txtDtNascimento.setText(Parsers.parseDataToStringNormal(especialidade.getDtNascimento()));
        txtDescEspecialidade.setText(especialidade.getDescEspecialidade());
        txtResumoCurriculo.setText((especialidade.getResumoCurriculo()));
//        txtDtInclusao.setText(Parsers.parseDataToStringNormal(especialidade.getDtInclusao()));
        txtBairro.setText(especialidade.getBairro());
        txtCidade.setText(especialidade.getCidade());
        txtEstado.setText(especialidade.getEstado());
        txtNumCon.setText(especialidade.getQtPropostasAceitas());
        txtMedAv.setText(Double.toString(especialidade.getMediaAvaliacoes()));

        return view;
    }


    @Override
    public int getCount() {
        return especialidadesList.size();
    }

    @Override
    public Object getItem(int position) {
        return especialidadesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(context,
                connectionResult.toString(),
                Toast.LENGTH_LONG).show();
        Log.e("Falha na conecao ", "onConnectionFailed: " + connectionResult.toString());
    }

    public void onStart() {
        mGoogleApiClient.connect();

    }


    public void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    private void obterUltimaLocalizacao() {

        try {
            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (location != null) {
                ptOrigem = new LatLng(location.getLatitude(), location.getLongitude());
                Log.e("Localizacao", "obterUltimaLocalizacao: -->" + ptOrigem.toString());
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }


    public class ViewHolder {
        //views
        TextView txtNome;
        //        TextView txtDtNascimento;
        TextView txtDescEspecialidade;
        TextView txtResumoCurriculo;
        //        TextView txtDtInclusao;
        TextView txtBairro;
        TextView txtCidade;
        TextView txtEstado;
        TextView txtNumCon;
        TextView txtMedAv;


    }

    GoogleApiClient.ConnectionCallbacks mConnectionCallbacks =
            new GoogleApiClient.ConnectionCallbacks() {
                @Override
                public void onConnected(Bundle bundle) {

                }

                @Override
                public void onConnectionSuspended(int i) {
                }
            };


    public LatLng pegaCoordenadaDoEndereco(String endereco) {
        try {
            Geocoder geocoder = new Geocoder(context);
            final List<Address> resultados = geocoder.getFromLocationName(endereco, 1);
            if (!resultados.isEmpty()) {
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;
            }
        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }

}