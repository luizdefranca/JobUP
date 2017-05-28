package com.br.jobup.util;

import java.util.Arrays;
import java.util.List;

/*
 * Created by Luiz Carlos Ramos on 26/05/17 05:03
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 16/05/17 03:49
 */

/**
 * Created by luizramos on 09/04/17.
 */

public class Uf {

    public static final String AC = "Acre";
    public static final String Al = "Alagoas";
    public static final String AP = "Amapá";
    public static final String AM = "Amazonas";
    public static final String BA = "Bahia";
    public static final String CE = "Ceará";
    public static final String DF = "Distrito Federal";
    public static final String ES = "Espírito Santos";
    public static final String GO = "Goiás";
    public static final String MA = "Maranhão";
    public static final String MT = "Mato Grosso";
    public static final String MS = "Mato Grosso do Sul";
    public static final String MG = "Minas Gerais";
    public static final String PR = "Paraná";
    public static final String PB = "Paraíba";
    public static final String PA = "Pará";
    public static final String PE = "Pernambuco";
    public static final String PI = "Piauí";
    public static final String RJ = "Rio de Janeiro";
    public static final String RN = "Rio Grande do Norte";
    public static final String RS = "Rio Grande do Sul";
    public static final String RO = "Rondônia";
    public static final String RR = "Roraima";
    public static final String SC = "Santa Catarina";
    public static final String SE = "Sergipe";
    public static final String SP = "São Paulo";
    public static final String TO = "Tocantins";
    public static final String EX = "Países Estrangeiros";

    int uf;

    public List<String> UF = Arrays.asList(
                Uf.AC,
                Uf.Al,
                Uf.AP,
                Uf.AM,
                Uf.BA,
                Uf.CE,
                Uf.DF,
                Uf.ES,
                Uf.GO,
                Uf.MA,
                Uf.MT,
                Uf.MS,
                Uf.MG,
                Uf.PR,
                Uf.PB,
                Uf.PA,
                Uf.PE,
                Uf.PI,
                Uf.RJ,
                Uf.RN,
                Uf.RS,
                Uf.RO,
                Uf.RR,
                Uf.SC,
                Uf.SE,
                Uf.SP,
                Uf.TO,
                Uf.EX
    );

    public Uf(){

    }

    public List<String> getUF(){
        return this.UF;
    }

    public void setUf(String uf){
        this.uf = UF.indexOf(uf);

    }

    public void setUF(List<String> UF){
        this.UF = UF;
    }


}
