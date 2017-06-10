package com.br.jobup.validations;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luizramos on 04/04/17.
 */

public class DataValidation {
    public static Boolean isDateValid(String date){

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient (false); // aqui o pulo do gato
        try {
            Date dateValid = df.parse(date);
            // data válida
            return true;

        } catch (ParseException ex) {
            Log.i("luiz", "Erro " + ex);
            return false;
        } catch (RuntimeException ex){
            Log.i("luiz", "RuntimeException " + ex);
            return false;
        }
    }
}
