package com.br.jobup.fragments;

/*
 * Created by Luiz Carlos Ramos on 6/6/17 5:39 PM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/6/17 5:39 PM
 */

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.br.jobup.models.especialidade.ServicoOferta;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luizramos on 06/06/17.
 */

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    public static final String LISTA_DE_ESPECIALIDADES = "LISTA_DE_ESPECIALIDADES";
    public static final String BUNDLE_LISTA_DE_ESPECIALIDADES = "BUNDLE_LISTA_DE_ESPECIALIDADES";
    private ArrayList<ServicoOferta> listaEspecialidades;
    private GoogleMap mapa;

    public static MapaFragment getInstance(Bundle bundle) {
        MapaFragment frag = new MapaFragment();
//        Bundle args = bundle;
//        args.putBundle(BUNDLE_LISTA_DE_ESPECIALIDADES, bundle);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getMapAsync(this);

        Bundle arguments = this.getArguments();
        listaEspecialidades = arguments.getParcelableArrayList(LISTA_DE_ESPECIALIDADES);


    }

    public void centralizaEm(LatLng coordenada) {
        if (mapa != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(coordenada, 11);
            mapa.moveCamera(update);
        }
    }

    private String pegaEndereco(ServicoOferta profissional) {
        String endereco = profissional.bairro + ", " + profissional.cidade + "/" + profissional.estado;

        return endereco;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng posicaoUsuario = pegaCoordenadaDoEndereco("Várzea, Recife, PE");
        if (posicaoUsuario != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoUsuario, 11);
            googleMap.moveCamera(update);
        }

        for (ServicoOferta profissional : listaEspecialidades) {
            String enderecoDoProfissional = pegaEndereco(profissional);
            LatLng coordenada = pegaCoordenadaDoEndereco(enderecoDoProfissional);
            if (coordenada != null) {
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(coordenada);
                marcador.title(profissional.getNome());
                marcador.snippet(profissional.bairro);
                googleMap.addMarker(marcador);
            }
        }
    }


    protected LatLng pegaCoordenadaDoEndereco(String endereco) {
        LatLng posicao = null;

        try {
            Geocoder geocoder = new Geocoder(getContext());
            final List<Address> resultados = geocoder.getFromLocationName(endereco, 1);
            if (!resultados.isEmpty()) {
                posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posicao;
    }
}
