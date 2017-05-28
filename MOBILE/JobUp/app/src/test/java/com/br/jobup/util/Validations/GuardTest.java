package com.br.jobup.util.Validations;

import org.junit.Test;

/**
 * Created by luizramos on 08/04/17.
 */
public class GuardTest {


    @Test(expected = Exception.class)
    public void forNegative_Se_O_Numero_for_negativo() throws Exception {
        Guard.forNegative(-3, "number");
    }

    @Test(expected = Exception.class)
    public void forNullOrEmptyDefaultMessage_Com_String_Em_branco() throws Exception {
        Guard.forNullOrEmptyDefaultMessage("", "empty");
    }

    @Test(expected = Exception.class)
    public void forNullOrEmptyDefaultMessage_Com_String_Nula() throws Exception {
        Guard.forNullOrEmptyDefaultMessage(null, "null");
    }

    @Test(expected = Exception.class)
    public void forNullOrEmpty_With_Empty_String() throws Exception {
        Guard.forNullOrEmpty("", "empty");
    }
    @Test(expected = Exception.class)
    public void forNullOrEmpty_With_Null_String() throws Exception {
        Guard.forNullOrEmpty(null, "empty");
    }

    @Test(expected = Exception.class)
    public void stringLength_With_Min_And_Max_Values_Menor_Que_Minimo() throws Exception {
        Guard.stringLength("casa", 6, 9, "valor pequeno");
    }

    @Test(expected = Exception.class)
    public void stringLength_With_Min_And_Max_Values_Maior_Que_Maximo() throws Exception {
        Guard.stringLength("hjkhkhdkdkdk", 6, 9, "valor pequeno");
    }

    @Test(expected = Exception.class)
    public void stringLength_With_Max_Values_Maior_Que_Maximo() throws Exception {
        Guard.stringLength("hjkhkhdkdkdk", 9, "valor pequeno");
    }


    @Test(expected = Exception.class)
    public void areEqual() throws Exception {

        Guard.areEqual("casa", "vila");
    }


}