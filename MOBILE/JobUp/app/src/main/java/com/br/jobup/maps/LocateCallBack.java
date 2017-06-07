package com.br.jobup.maps;

/*
 * Created by Luiz Carlos Ramos on 03/06/17 12:22
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 03/06/17 12:22
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by luizramos on 03/06/17.
 */

public class LocateCallBack implements GoogleApiClient.ConnectionCallbacks, OnConnectionFailedListener {
    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }




}
