package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;

import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.entity.Nota;
import com.example.demo.domain.valueObjects.NotaValue;

public class CursoNotaTest {
    @Test
    void testCreateValidCursoNota(){
        Long id = 1L;
        NotaValue nota = new NotaValue(7);
        AlunoCurso alunoCurso = new AlunoCurso();

        Nota cursoNota = new Nota();
        cursoNota.setId(id);
        cursoNota.setValor(nota);
        cursoNota.setAlunoCurso(alunoCurso);

        assertEquals(id, cursoNota.getId());
        assertEquals(nota.getValor(), cursoNota.getValor());
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
