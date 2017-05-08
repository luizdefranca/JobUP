package com.br.jobup.models;

/*
 * Created by Luiz Carlos Ramos on 08/05/17 20:04
 *
 * Copyright (c) 2017. All rights reserved.
 * Last modified 08/05/17 20:04
 */

import java.io.Serializable;

/**
 * Created by luizramos on 08/05/17.
 */

public enum TempServico implements Serializable{

    PRAZO01("O quanto Antes Possível", 0),
    PRAZO02("Nos próximos 30 dias", 1),
    PRAZO03("Nos próximos 3 meses", 2),
    PRAZO04("Não tenho certeza", 3);

    private String stringValue;
    private int intValue;
    private TempServico(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }


}
