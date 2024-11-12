package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.entity.Curso;

public class CursoTest {
    @Test
    void testCreateValidCurso(){
        Long id = 1L;
        int duracao = 50;
        String nome = "Curso de Java";
        double valor = 50.99;
        Set<AlunoCurso> alunoCurso = new HashSet<AlunoCurso>();

        alunoCurso.add(new AlunoCurso());
        alunoCurso.add(new AlunoCurso());

        Curso curso = new Curso();
        curso.setId(id);
        curso.setDuracao(duracao);
        curso.setNome(nome);
        curso.setValor(valor);
        curso.setAlunoCursos(alunoCurso);

        assertEquals(id, curso.getId());
        assertEquals(duracao, curso.getDuracao());
        assertEquals(nome, curso.getNome());
        assertEquals(valor, curso.getValor());
        assertEquals(alunoCurso, curso.getAlunoCursos());
    }
}
