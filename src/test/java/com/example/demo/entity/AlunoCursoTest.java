package com.example.demo.entity;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.entity.Nota;
import com.example.demo.domain.enumerations.AlunoCursoStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlunoCursoTest {

    private AlunoCurso alunoCurso;
    private Aluno alunoMock;
    private Curso cursoMock;
    private Nota notaMock;

    @BeforeEach
    void setUp() {
        alunoCurso = new AlunoCurso();
        alunoMock = mock(Aluno.class);
        cursoMock = mock(Curso.class);
        notaMock = mock(Nota.class);
    }

    @Test
    void shouldSetAndGetAluno() {
        alunoCurso.setAluno(alunoMock);
        assertEquals(alunoMock, alunoCurso.getAluno());
    }

    @Test
    void shouldSetAndGetCurso() {
        alunoCurso.setCurso(cursoMock);
        assertEquals(cursoMock, alunoCurso.getCurso());
    }

    @Test
    void shouldAddNotaToNotasList() {
        alunoCurso.setNotas(Arrays.asList(notaMock));
        assertEquals(1, alunoCurso.getNotas().size());
        assertTrue(alunoCurso.getNotas().contains(notaMock));
    }


    @Test
    void shouldSetAndGetStatus() {
        alunoCurso.setStatus(AlunoCursoStatus.ANDAMENTO);
        assertEquals(AlunoCursoStatus.ANDAMENTO, alunoCurso.getStatus());
    }

    @Test
    void shouldSetAndGetId() {
        alunoCurso.setId(1L);
        assertEquals(1L, alunoCurso.getId());
    }

    @Test
    void shouldHandleNullValuesInSetters() {
        alunoCurso.setAluno(null);
        alunoCurso.setCurso(null);
        alunoCurso.setNotas(null);

        assertNull(alunoCurso.getAluno());
        assertNull(alunoCurso.getCurso());
        assertNull(alunoCurso.getNotas());
    }
}
