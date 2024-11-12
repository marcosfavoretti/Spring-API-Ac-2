package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.entity.Curso_Nota;
import com.example.demo.domain.valueObjects.NotaValue;

public class CursoNotaTest {
    @Test
    void testCreateValidCursoNota(){
        Long id = 1L;
        NotaValue nota = new NotaValue(7);
        AlunoCurso alunoCurso = new AlunoCurso();

        Curso_Nota cursoNota = new Curso_Nota();
        cursoNota.setId(id);
        cursoNota.setValor(nota);
        cursoNota.setAlunoCurso(alunoCurso);

        assertEquals(id, cursoNota.getId());
        assertEquals(nota, cursoNota.getValor());
        assertEquals(alunoCurso, cursoNota.getAlunoCurso());
    }

    @Test
    void testInvalidNota(){
        assertThrows(IllegalArgumentException.class, () -> {
            new NotaValue(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new NotaValue(11);
        });
    }
}
