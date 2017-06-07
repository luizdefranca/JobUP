package com.br.jobup.maps;

/*
 * Created by Luiz Carlos Ramos on 02/06/17 17:22
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 02/06/17 17:22
 */


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by luizramos on 02/06/17.
 */

public class PlayServicesUtils {
    public static final int REQUEST_CODE_ERRO_PLAY_SERVICES = 9000;

    public static boolean googlePlayServicesDisponivel(Activity activity){
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(activity);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(activity, result,
                        REQUEST_CODE_ERRO_PLAY_SERVICES).show();
            }

            return false;
        }

        return true;
    }


    public static void exibirMensagemDeErro(Activity activity, int resultCode){
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        apiAvailability.getErrorDialog(activity, resultCode, REQUEST_CODE_ERRO_PLAY_SERVICES).show();
    }
}
