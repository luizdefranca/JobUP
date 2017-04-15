package br.com.workup.jobup.validations.EntitiesValidations;

/**
 * Created by luizramos on 08/04/17.
 */
import java.util.regex.*;

import static java.util.regex.Pattern.compile;


public class CpfValidation {

    static public boolean isACpfValid(String cpf){

        String padrao = "^(\\d{11})|(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$";
        Pattern regex = Pattern.compile(padrao);

        int d1 = 0;
        int d2 = 0;
        int digito1 = 0;
        int digito2 = 0;
        int resto = 0;
        int digitoCPF;
        String digVerific, digResult;
        String[] cpfInvalidos = {"00000000000", "11111111111", "22222222222", "33333333333",
            "44444444444", "55555555555", "66666666666", "77777777777", "88888888888",
                "99999999999"};
        String parteNumerica = cpf.replaceAll("\\.", "").replaceAll("\\-", "");
        for(String cpfinvalido: cpfInvalidos){
            if (parteNumerica.equals(cpfinvalido)) return false;
        }

        for (int contador = 1; contador < parteNumerica.length() - 1; contador++){
            digitoCPF = Integer.valueOf(parteNumerica.substring(contador - 1, contador)).intValue();
            d1 = d1 + (11 - contador) * digitoCPF;
            d2 = d2 + (12 - contador) * digitoCPF;
        }

        resto = (d1 % 11);
        if(resto < 2){
            digito1 = 0;
        } else{
            digito1 = 11 - resto;
        }

        d2 += 2 * digito1;
        resto = (d2 % 11);
        if(resto < 2){
            digito2 = 0;
        } else{
            digito2 = 11 - resto;
        }

        digVerific = parteNumerica.substring(parteNumerica.length() - 2, parteNumerica.length());
        digResult = String.valueOf(digito1) + String.valueOf(digito2);


        return (regex.matcher(cpf).matches() && (digVerific == digResult));
    }

}
