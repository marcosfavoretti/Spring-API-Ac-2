package com.example.demo.infra.controller.dto;

import java.util.List;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.entity.Nota;
import com.example.demo.domain.enumerations.AlunoCursoStatus;
import com.example.demo.domain.valueObjects.Ra;

public class OutputAlunoCursoDTO {
    public Aluno aluno;
    public List<Nota> notas;
    public Curso cursos;
    public AlunoCursoStatus status;

    public OutputAlunoCursoDTO(
            Aluno aluno,
            List<Nota> notas,
            Curso curso,
            AlunoCursoStatus status) {
                this.aluno = new Aluno();
                this.aluno.setDataInicio(aluno.getDataInicio());
                this.aluno.setEmail(aluno.getEmail());
                this.aluno.setNome(aluno.getNome());
                this.aluno.setIdade(aluno.getIdade());
                this.aluno.setRa(new Ra(aluno.getRa()));
                this.aluno.setId(aluno.getId());
                this.notas= notas;
                this.status = status;
                this.cursos = new Curso();
                this.cursos.setDuracao(curso.getDuracao());
                this.cursos.setId(curso.getId());
                this.cursos.setNome(curso.getNome());
                this.cursos.setDuracao(curso.getDuracao());
                this.cursos.setValor(curso.getValor());
    }
}
