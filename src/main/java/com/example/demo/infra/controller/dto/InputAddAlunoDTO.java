package com.example.demo.infra.controller.dto;

public class InputAddAlunoDTO {
    public long idAluno;
    public long idCurso;

    public InputAddAlunoDTO(
            long idAluno,
            long idCurso) {
        this.idAluno = idAluno;
        this.idCurso = idCurso;
    }
}
