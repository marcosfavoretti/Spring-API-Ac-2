package com.example.demo.domain.dto;

public class InputCreateCursoDTO {
    public String nome;
    public int duracao;
    public double valor;

    public InputCreateCursoDTO(
        String nome,
        int duracao,
        double valor
    ) {
        this.nome = nome;
        this.duracao = duracao;
        this.valor = valor;       
    }
}
