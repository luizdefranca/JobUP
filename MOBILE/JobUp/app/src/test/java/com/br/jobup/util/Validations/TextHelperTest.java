package com.br.jobup.util.Validations;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by luizramos on 08/04/17.
 */
public class TextHelperTest {
    @Test
    public void removerAcentos() throws Exception {
        String expected = "ameoikenfg";
        String actual = TextHelper.RemoverAcentos("ámêôíkénfg");
        assertEquals("Remove Acentos",expected,actual);
    }

    @Test
    public void formatarTextoParaUrl() throws Exception {
        String expected = "http://www.globo.com";
        String actual = TextHelper.FormatarTextoParaUrl("h t t p: //www..Glôbô.c o m");
        assertEquals("Formata texto para Url", expected, actual);
    }

    @Test
    public void getNumeros() throws Exception {
        String expected = "12342";
        String actual = TextHelper.GetNumeros("ghj1gd2342");
    }

    @Test
    public void ajustarTexto() throws Exception {

    }

    @Test
    public void toTitleCase() throws Exception {

    }

}