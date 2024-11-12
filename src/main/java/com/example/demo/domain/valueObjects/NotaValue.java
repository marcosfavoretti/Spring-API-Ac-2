package com.example.demo.domain.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class NotaValue {
    private double notaValue;


    protected NotaValue(){}

    public NotaValue(double valor) {
        if (valor < 0 || valor > 10) {  // Exemplo de nota de 0 a 10
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10");
        }
        this.notaValue = valor;
    }

    public double getValor() {
        return this.notaValue;
    }

    public void setValor(double valor) {
        this.notaValue = valor;
    }

}
