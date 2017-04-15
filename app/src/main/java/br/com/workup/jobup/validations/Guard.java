package br.com.workup.jobup.validations;

/**
 * Created by luizramos on 08/04/17.
 */

public class Guard {

    public static void forNegative(int number, String propName) throws Exception {
        if (number < 0)
            throw new Exception(propName + " não pode ser negativo!");
    }

    public static void forNullOrEmptyDefaultMessage(String value, String propName) throws Exception {
        if ("" == value || null == value)
            throw new Exception(propName + " é obrigatório!");
    }

    public static void forNullOrEmpty(String value, String errorMessage) throws Exception {
        if ("" == value || null == value)
            throw new Exception(errorMessage);
    }

    public static void stringLength(String propName, String stringValue, int maximum) throws Exception {
        stringLength(stringValue, maximum, propName + " não pode ter mais que " + maximum + " caracteres");
    }

    public static void stringLength(String stringValue, int maximum, String message) throws Exception {
        int length = stringValue.length();
        if (length > maximum)
        {
            throw new Exception(message);
        }
    }
    public static void stringLength(String propName, String stringValue, int minimum, int maximum) throws Exception {
        stringLength(stringValue, minimum, maximum, propName + " deve ter de " + minimum + " à " + maximum + " caracteres!");
    }

    public static void stringLength(String stringValue, int minimum, int maximum, String message) throws Exception {
        if ("" == stringValue || null == stringValue)
            stringValue = "";

        int length = stringValue.length();
        if (length < minimum || length > maximum)
        {
            throw new Exception(message);
        }
    }

    public static void areEqual(String a, String b, String errorMessage) throws Exception {
        if (a.equals(b))
            throw new Exception(errorMessage);
    }

    public static void areEqual(String a, String b) throws Exception {
        if (a.equals(b))
            throw new Exception("The Strings don\'t equal");
    }
}
