package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.entity.Nota;
import com.example.demo.domain.valueObjects.Email;
import com.example.demo.domain.valueObjects.Ra;
import com.example.demo.infra.controller.dto.InputNotaDTO;
import com.example.demo.infra.repositories.IAlunoCursoRepository;
import com.example.demo.infra.repositories.IAlunosRepository;
import com.example.demo.infra.repositories.ICursoNotaRepository;
import com.example.demo.infra.repositories.ICursoRepository;
import com.example.demo.service.CursoNotaService;

@SpringBootTest
public class CursoNotaServiceTest {
    
    @Autowired
    CursoNotaService cursoNotaService;
    @Autowired
    private IAlunoCursoRepository alunoCursoRep;
    @Autowired
    private ICursoRepository cursoRepository;
    @Autowired
    private IAlunosRepository alunoRepo;
    
    public AlunoCurso new_alunoCurso;
    
    @BeforeEach
    void setup(){
        Aluno aluno = new Aluno();
        aluno.setAlunoCursos(new HashSet<>());
        aluno.setDataInicio((new Date()));
        aluno.setEmail(new Email("marcos@gmail.com"));
        aluno.setIdade(18);
        aluno.setNome("Marcos");
        aluno.setRa(new Ra("210482"));
        Aluno new_Aluno = this.alunoRepo.save(aluno);

        //
        Curso curso = new Curso();
        curso.setDuracao(2);
        curso.setAlunoCursos(new HashSet<>());
        curso.setValor(1000);
        curso.setNome("Node básico");
        Curso curso_salvo = this.cursoRepository.save(curso);

        //
        AlunoCurso alunocurso = new AlunoCurso();
        alunocurso.setAluno(new_Aluno);
        alunocurso.setNotas(new ArrayList<>());
        alunocurso.setCurso(curso_salvo);
        new_alunoCurso = this.alunoCursoRep.save(alunocurso);
    }

    @Test
    void testAddInvalidNota(){
        assertThrows(IllegalStateException.class,() -> cursoNotaService.addNota(new InputNotaDTO(5, 9999)));

        cursoNotaService.addNota(new InputNotaDTO(5, new_alunoCurso.getId()));
        cursoNotaService.addNota(new InputNotaDTO(6, new_alunoCurso.getId()));
        cursoNotaService.addNota(new InputNotaDTO(7, new_alunoCurso.getId()));

        assertThrows(IllegalStateException.class, () -> cursoNotaService.addNota(new InputNotaDTO(7, new_alunoCurso.getId())));
    }

    @Test 
    void testAddNota(){
        List<Nota> notas = cursoNotaService.listNotasDoAluno(new_alunoCurso);

        assertEquals(notas.size(), 0);

        cursoNotaService.addNota(new InputNotaDTO(5, new_alunoCurso.getId()));
        cursoNotaService.addNota(new InputNotaDTO(6, new_alunoCurso.getId()));
        cursoNotaService.addNota(new InputNotaDTO(7, new_alunoCurso.getId()));
        
        notas = cursoNotaService.listNotasDoAluno(new_alunoCurso);

        assertEquals(notas.size(), 3);
        assertEquals(notas.get(0).getValor(), 5);
        assertEquals(notas.get(1).getValor(), 6);
        assertEquals(notas.get(2).getValor(), 7);
    }

    @Test
    void testNotasPendentes(){
        cursoNotaService.addNota(new InputNotaDTO(5, new_alunoCurso.getId()));
        cursoNotaService.addNota(new InputNotaDTO(6, new_alunoCurso.getId()));
        
        boolean hasNotasPendentes = cursoNotaService.notasPendentes(new_alunoCurso);

        assertTrue(hasNotasPendentes);
        
        cursoNotaService.addNota(new InputNotaDTO(6, new_alunoCurso.getId()));
        hasNotasPendentes = cursoNotaService.notasPendentes(new_alunoCurso);

        assertFalse(hasNotasPendentes);
    }
}
