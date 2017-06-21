package com.br.jobup.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.br.jobup.R;
import com.br.jobup.services.parsers.ParserRejeitarProposta;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Created by Luiz Carlos Ramos on 6/19/17 11:19 PM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/4/17 10:10 AM
 */

public class RecusaPropostaDialogFragment extends DialogFragment implements
        DialogInterface.OnClickListener {
    private View form = null;
    public static final String TAG = "LCFR";
    private Bundle mBundle;
    private String idProposta;
    private String idUsuario;
    private Activity activity;

    public static RecusaPropostaDialogFragment getInstance(Bundle bundle, Activity activity) {

        RecusaPropostaDialogFragment frag = new RecusaPropostaDialogFragment();
        Bundle args = new Bundle();
        args.putBundle("RECUSAR_BUNDLE", bundle);
        frag.activity = activity;
        frag.setArguments(args);

        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        form =
                getActivity().getLayoutInflater()
                        .inflate(R.layout.activity_dialog_recusar_proposta, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        mBundle = getArguments().getBundle("RECUSAR_BUNDLE");
        mBundle = getArguments();
        idProposta = mBundle.getString("ID_PROPOSTA");
        idUsuario = mBundle.getString("ID_USUARIO");
        Log.e(TAG, "idProposta ->" + idProposta);
        return (builder.setTitle("Recusar Proposta").setView(form)
                .setPositiveButton("Enviar", this)
                .setNegativeButton("Fechar", null).create());

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        EditText name = (EditText) form.findViewById(R.id.editTextJustificativa);
        ParserRejeitarProposta parser = new ParserRejeitarProposta(idProposta, idUsuario);
        parser.rejeitarServico().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(activity, "Serviço Recusado", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(activity, "Erro ao recusar o serviço", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "Falha de comunicação com o servidor. Tente de novo mais tarde", Toast.LENGTH_LONG).show();
            }
        });
        Log.e(TAG, idProposta);

    }

    @Override
    public void onDismiss(DialogInterface unused) {
        super.onDismiss(unused);

        Log.d(getClass().getSimpleName(), "Goodbye!");
    }

    @Override
    public void onCancel(DialogInterface unused) {
        super.onCancel(unused);

    }
}
