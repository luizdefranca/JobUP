package com.br.jobup.models;

/**
 * Created by luizramos on 19/04/17.
 */

public enum Uf {
    AC(0), Al(1), AP(2), AM(3), BA(4), CE(5), DF(6), ES(7), GO(8), MA(9), MT(10), MS(11), MG(12),
    PR(13), PB(14), PA(15), PE(16), PI(17), RJ(18), RN(19), RS(20), RO(21), RR(22), SC(23), SE(24),
    SP(25), TO(26), EX(27);

    private int valor;

    Uf(int valor) {
        this.valor = valor;
    }

    public int getValor(){
        return this.valor;
    }

    public void setValor(int valor){
        this.valor = valor;
    }
}
