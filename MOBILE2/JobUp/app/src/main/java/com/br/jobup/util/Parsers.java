package com.br.jobup.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luizramos on 23/04/17.
 */

public class Parsers {

    public static Date parseStringToDataNormal(String data){
        DateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");

        //usa-se o método setLenient como false para que faça a conversao restrita
        dataFormat.setLenient(false);
        try {
            Date dateFormatada = dataFormat.parse(data);
            return dateFormatada;
        } catch (ParseException e) {
            return null;
        }

    }

    public static Date parseStringToDataAPI(String data){
        DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");

        //usa-se o método setLenient como false para que faça a conversao restrita
        dataFormat.setLenient(false);
        try {
            Date dateFormatada = dataFormat.parse(data);
            return dateFormatada;
        } catch (ParseException e) {
            return null;
        }
    }

    public static String parseDataToStringNormal(Date data){

        DateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");

        return dataFormat.format(data);
    }

}

