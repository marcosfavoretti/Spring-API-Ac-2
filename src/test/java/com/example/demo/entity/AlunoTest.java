package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.valueObjects.Email;

public class AlunoTest {
    @Test
    void testCreateValidAluno(){
        Long id = 1L;
        Email email = new Email("aluno@gmail.com");

        Aluno aluno = new Aluno();

        aluno.setId(id);
        aluno.setEmail(email);
        aluno.setNome(null);
        aluno.setIdade(null);
        aluno.setRa(null);
        aluno.setDataInicio(null);
        aluno.setAlunoCursos(null);
    }
}
