package com.example.demo.domain.entity;

import com.example.demo.domain.valueObjects.NotaValue;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_nota")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private NotaValue valor;
    @ManyToOne
    @JoinColumn(name = "aluno_curso_id", nullable = false)
    private AlunoCurso alunoCurso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotaValue getValor() {
        return valor;
    }

    public void setValor(NotaValue valor) {
        this.valor = valor;
    }

    public AlunoCurso getAlunoCurso() {
        return alunoCurso;
    }

    public void setAlunoCurso(AlunoCurso alunoCurso) {
        this.alunoCurso = alunoCurso;
    }

}