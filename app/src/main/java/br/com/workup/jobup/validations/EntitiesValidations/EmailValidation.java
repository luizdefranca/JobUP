package br.com.workup.jobup.validations.EntitiesValidations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luizramos on 09/04/17.
 */

public class EmailValidation {

    public static boolean isEmail(String value) {
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(value);

        if (m.find()){
            return true;
        } else {
            return false;
        }
    }
}
