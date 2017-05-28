package com.br.jobup.models.servico;

/*
 * Created by Luiz Carlos Ramos on 26/05/17 05:03
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 26/05/17 05:02
 */

/**
 * Created by luizramos on 26/05/17.
 */

public enum DuracaoServico {

    HORA("Hora(s)", 0),
    DIA("Dia(s)", 1),
    SEMANA("Semana(s)", 2),
    MES("Mês(es)", 3);

    private String stringValue;
    private int intValue;
    private DuracaoServico(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
