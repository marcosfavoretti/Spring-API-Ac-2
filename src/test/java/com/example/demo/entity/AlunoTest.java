package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.valueObjects.Email;
import com.example.demo.domain.valueObjects.Ra;

public class AlunoTest {
    @Test
    void testCreateValidAluno(){
        Long id = 1L;
        Email email = new Email("aluno@gmail.com");
        String nome = "João";
        int idade = 18;
        Date data = new Date();
        Ra ra = new Ra("200197");
        Set<AlunoCurso> alunoCurso = new HashSet<AlunoCurso>();

        alunoCurso.add(new AlunoCurso()); 
        alunoCurso.add(new AlunoCurso()); 

        Aluno aluno = new Aluno();

        aluno.setId(id);
        aluno.setEmail(email);
        aluno.setNome(nome);
        aluno.setIdade(idade);
        aluno.setRa(ra);
        aluno.setDataInicio(data);
        aluno.setAlunoCursos(alunoCurso);

        assertEquals(id, aluno.getId());
        assertEquals(email, aluno.getEmail());
        assertEquals(nome, aluno.getNome());
        assertEquals(idade, aluno.getIdade());
        assertEquals(ra, aluno.getRa());
        assertEquals(data, aluno.getDataInicio());
        assertEquals(alunoCurso, aluno.getAlunoCursos());
    }

    @Test
    void testInvalidAlunoEmail(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Email("invalidEmail");
        });
    }

    @Test
    void testInvalidAlunoRa(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Ra("12345");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Ra("1234567");
        });
    }
}
