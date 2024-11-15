package com.example.demo.infra.controller.dto;

public class OutputGetAllCursoDTO {
    public Long id;
    public String nome;
    public int duracao;
    public double valor;

    public OutputGetAllCursoDTO(
            Long id,
            String nome,
            int duracao,
            double valor) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.valor = valor;
    }
}
