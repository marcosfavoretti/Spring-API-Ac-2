package com.example.demo.infra.controller.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.valueObjects.Email;
import com.example.demo.domain.valueObjects.Ra;

public class InputCreateAlunoDTO {
    public String nome;
    public String email;
    public String ra;
    public int idade;
    public Set<AlunoCurso> alunoCursos;

    public InputCreateAlunoDTO(
            String nome,
            String email,
            String ra,
            int idade) {
        this.nome = nome;
        this.email = email;
        this.ra = ra;
        this.idade = idade;
        this.alunoCursos = new HashSet<>();
    }
}
