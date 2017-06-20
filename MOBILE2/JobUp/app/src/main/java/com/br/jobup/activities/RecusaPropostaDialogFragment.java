package com.br.jobup.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.br.jobup.R;

import static android.R.attr.value;

/*
 * Created by Luiz Carlos Ramos on 6/19/17 11:19 PM
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 6/4/17 10:10 AM
 */

public class RecusaPropostaDialogFragment extends DialogFragment implements
    DialogInterface.OnClickListener {
  private View form=null;

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    form=
        getActivity().getLayoutInflater()
                     .inflate(R.layout.activity_dialog_recusar_proposta, null);

    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

    return(builder.setTitle("Recusar Proposta").setView(form)
                  .setPositiveButton(android.R.string.ok, this)
                  .setNegativeButton(android.R.string.cancel, null).create());
  }

  @Override
  public void onClick(DialogInterface dialog, int which) {

    EditText name=(EditText)form.findViewById(R.id.editTextJustificativa);

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
