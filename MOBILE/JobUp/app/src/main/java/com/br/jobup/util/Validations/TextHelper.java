package com.br.jobup.util.Validations;

import android.view.textservice.TextInfo;

import java.util.regex.Pattern;

import static android.R.attr.text;
import static android.R.attr.value;
import static android.R.id.input;

/**
 * Created by luizramos on 08/04/17.
 */

public class TextHelper
{
    public static String RemoverAcentos(String texto)
    {
        if (texto == null) return "";

            final String comAcentos = "ÄÅÁÂÀÃäáâàãÉÊËÈéêëèÍÎÏÌíîïìÖÓÔÒÕöóôòõÜÚÛüúûùÇç";
            final String semAcentos = "AAAAAAaaaaaEEEEeeeeIIIIiiiiOOOOOoooooUUUuuuuCc";

        for (int i = 0; i < comAcentos.length(); i++)
            texto = texto.replace(String.valueOf(comAcentos.charAt(i)), String.valueOf(semAcentos.charAt(i)));

        return texto;
    }

    public static String FormatarTextoParaUrl(String texto)
    {
        texto = RemoverAcentos(texto);

        String textoretorno = texto.replace(" ", "");
        texto = textoretorno;

            final String permitidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmonopqrstuvwxyz0123456789-_";

        for (int i = 0; i < texto.length(); i++)
            if (!permitidos.contains(texto.substring(i, 1))) {
                textoretorno = textoretorno.replace(texto.substring(i, 1), "");
            }

        return textoretorno;
    }

    public static String GetNumeros(String texto)
    {
        return Pattern.compile("[0-9]").matcher(texto).replaceAll("");
    }



    public static String AjustarTexto(String valor, int tamanho)
    {
        if (valor.length() > tamanho)
        {
            valor = valor.substring(1, tamanho);
        }
        return valor;
    }

    /// <summary>
    /// deixa as primeiras letras maiusculas
    /// </summary>
    /// <param name="texto"></param>
    /// <returns></returns>
    public static String ToTitleCase(String texto) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : texto.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }

}
