package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.enumerations.AlunoCursoStatus;
import com.example.demo.domain.valueObjects.Email;
import com.example.demo.domain.valueObjects.Ra;
import com.example.demo.infra.controller.dto.InputNotaDTO;
import com.example.demo.infra.repositories.IAlunoCursoRepository;
import com.example.demo.infra.repositories.IAlunosRepository;
import com.example.demo.infra.repositories.ICursoRepository;
import com.example.demo.service.CursoNotaService;
import com.example.demo.service.GerenciaFinalizacaoCurso;

@SpringBootTest
public class GerenciaFinalizaCursoTest {

    @Autowired
    GerenciaFinalizacaoCurso gerenciaService;

    @Autowired
    CursoNotaService cursoNotaService;
    // Mock do repositório
    @Autowired
    ICursoRepository cursosRepo;
    @Autowired
    private IAlunoCursoRepository alunoCursoRep;
    @Autowired
    private ICursoRepository cursoRepository;
    @Autowired
    private IAlunosRepository alunoRepo;
    
    public AlunoCurso new_alunoCurso;
    public Aluno new_aluno;
    @BeforeEach
    void setup() {
         Aluno aluno = new Aluno();
        aluno.setAlunoCursos(new HashSet<>());
        aluno.setDataInicio((new Date()));
        aluno.setEmail(new Email("marcos@gmail.com"));
        aluno.setIdade(18);
        aluno.setNome("Marcos");
        aluno.setRa(new Ra("210482"));
        new_aluno = this.alunoRepo.save(aluno);

        Curso curso = new Curso();
        curso.setDuracao(2);
        curso.setAlunoCursos(new HashSet<>());
        curso.setValor(1000);
        curso.setNome("Node básico");
        Curso curso_salvo = this.cursoRepository.save(curso);
        Curso curso2 = new Curso();
        curso.setDuracao(2);
        curso.setAlunoCursos(new HashSet<>());
        curso.setValor(1000);
        curso.setNome("Node básico");
        this.cursoRepository.save(curso2);
        Curso curso3 = new Curso();
        curso.setDuracao(2);
        curso.setAlunoCursos(new HashSet<>());
        curso.setValor(1000);
        curso.setNome("Node básico");
        this.cursoRepository.save(curso3);
        Curso curso4 = new Curso();
        curso.setDuracao(2);
        curso.setAlunoCursos(new HashSet<>());
        curso.setValor(1000);
        curso.setNome("Node básico");
        this.cursoRepository.save(curso4);

        AlunoCurso alunocurso = new AlunoCurso();
        alunocurso.setAluno(new_aluno);
        alunocurso.setNotas(new ArrayList<>());
        alunocurso.setCurso(curso_salvo);
        alunocurso.setStatus(AlunoCursoStatus.ANDAMENTO);
        new_alunoCurso = this.alunoCursoRep.save(alunocurso);
    }

    @Test
    @Transactional
    void testDeveFinalizarCursos() {
        cursoNotaService.addNota(new InputNotaDTO(5, new_alunoCurso.getId()));
        cursoNotaService.addNota(new InputNotaDTO(6, new_alunoCurso.getId()));
        cursoNotaService.addNota(new InputNotaDTO(5, new_alunoCurso.getId()));
        
        gerenciaService.finaliza(new_alunoCurso);

        AlunoCurso updatedAlunoCurso = alunoCursoRep.getReferenceById(new_alunoCurso.getId()); 
        List<AlunoCurso> cursosAluno = alunoCursoRep.findAll();
        List<AlunoCurso> cursosAdquiridos = cursosAluno.stream()
                        .filter(alunoCurso -> alunoCurso.getAluno().getId().equals(new_aluno.getId()))
                        .toList(); 
        assertEquals(updatedAlunoCurso.getStatus(),AlunoCursoStatus.CONCLUIDO);
        assertEquals(cursosAdquiridos.size(), 1);
    }

    @Test
    @Transactional
    void testDeveFinalizarCursosMediaMaior7() {
        cursoNotaService.addNota(new InputNotaDTO(8, new_alunoCurso.getId()));
        cursoNotaService.addNota(new InputNotaDTO(8, new_alunoCurso.getId()));
        cursoNotaService.addNota(new InputNotaDTO(8, new_alunoCurso.getId()));
        
        AlunoCurso updatedAlunoCurso = alunoCursoRep.getReferenceById(new_alunoCurso.getId());
        gerenciaService.finaliza(updatedAlunoCurso);

        //updatedAlunoCurso = alunoCursoRep.getReferenceById(new_alunoCurso.getId()); 
        List<AlunoCurso> cursosAluno = alunoCursoRep.findAll();
        List<AlunoCurso> cursosAdquiridos = cursosAluno.stream()
                        .filter(alunoCurso -> alunoCurso.getAluno().getId().equals(new_aluno.getId()))
                        .toList(); 
        assertEquals(updatedAlunoCurso.getStatus(),AlunoCursoStatus.CONCLUIDO);
        assertEquals(cursosAdquiridos.size(), 4);
    }
}
