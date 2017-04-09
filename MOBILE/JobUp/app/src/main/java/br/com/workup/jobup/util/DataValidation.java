package br.com.workup.jobup.util;

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
            System.out.println(ex);
            return false;
        }
    }
}
