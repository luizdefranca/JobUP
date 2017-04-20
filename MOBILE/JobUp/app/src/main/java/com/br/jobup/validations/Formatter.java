package com.br.jobup.validations;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Luiz em 19/04/2017.
 */
public class Formatter {
    public static String formatCurrency(double amount){

        NumberFormat baseFormat = NumberFormat.getCurrencyInstance();
        String moneyString = baseFormat.format(amount);
        return moneyString;
    }

    public static String formatDate(long date){

        String displayDate = new SimpleDateFormat("MMM dd").format(new Date(date));
        return displayDate;
    }

    public static String parseDateApiFormato(Date data){
        DateFormat FormatadadorParaApi = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:MM:ss\'.\'S");
        String dataFormatadaParaApi = FormatadadorParaApi.format(data);
        return dataFormatadaParaApi;
    }

    public static boolean parseIntToBoolean(int value){
        if(value == 0) return false;
        else return true;
    }


}
