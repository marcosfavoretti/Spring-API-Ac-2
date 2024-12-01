package com.example.demo.infra.controller.dto;

import com.example.demo.domain.valueObjects.NotaValue;

public class InputNotaDTO {
    public long idAlunoCurso;
    public double valor;

    public InputNotaDTO(
            double valor,
            long idAlunoCurso) {
        this.idAlunoCurso = idAlunoCurso;
        this.valor = new NotaValue(valor).getValor();
    }
}
