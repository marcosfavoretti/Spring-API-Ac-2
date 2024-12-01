package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.entity.Nota;
import com.example.demo.domain.enumerations.AlunoCursoStatus;
import com.example.demo.domain.valueObjects.Email;
import com.example.demo.domain.valueObjects.NotaValue;
import com.example.demo.domain.valueObjects.Ra;
import com.example.demo.infra.controller.dto.InputAddAlunoDTO;
import com.example.demo.infra.controller.dto.InputTerminarCursoDTO;
import com.example.demo.infra.repositories.IAlunosRepository;
import com.example.demo.infra.repositories.ICursoNotaRepository;
import com.example.demo.infra.repositories.ICursoRepository;
import com.example.demo.service.AlunoCursoService;

@SpringBootTest
public class AlunoCursoServiceTest {

    @Autowired
    ICursoRepository cursoRepo;

    @Autowired
    IAlunosRepository alunoRepo;

    @Autowired
    AlunoCursoService alunoCursoService;

    @Autowired
    ICursoNotaRepository notarepo;

    Curso curso;

    Aluno aluno;

    @BeforeEach
    public void setUp() {
        this.curso = new Curso();
        curso.setAlunoCursos(new HashSet<>());
        curso.setDuracao(1);
        curso.setId(1L);
        curso.setNome("DEVOPS");
        curso.setValor(0.99);
        this.cursoRepo.save(curso);

        this.aluno = new Aluno();
        aluno.setDataInicio(new Date());
        aluno.setEmail(new Email("marcos@facens.br"));
        aluno.setId(1L);
        aluno.setIdade(19);
        aluno.setNome("Marcos Favoretti Jr");
        aluno.setRa(new Ra("210482"));
        this.alunoRepo.save(aluno);
    }

    @Test
    public void adicionaAlunoEmCurso() {

        InputAddAlunoDTO inputalunodto = new InputAddAlunoDTO(1L, 1L);
        AlunoCurso alunoCursos = this.alunoCursoService.addAlunoEmCurso(inputalunodto);

        assertEquals(alunoCursos.getCurso().getId(), this.curso.getId());
        assertEquals(alunoCursos.getAluno().getId(), this.aluno.getId());

    }

    @Test
    public void adicionaAlunoEmCursoMasNaoTemOAluno() {
        InputAddAlunoDTO inputalunodto = new InputAddAlunoDTO(100L, 1L);
        assertThrows(IllegalStateException.class, () -> this.alunoCursoService.addAlunoEmCurso(inputalunodto));
    }

    @Test
    public void getAlunoCursosAlunoNaoEncontrado() {
        assertThrows(IllegalStateException.class, () -> this.alunoCursoService.getAlunoCurso(999L));
    }

    

    @Test
    public void adicionaAlunoEmCursoMasNaoTemOCurso() {

        InputAddAlunoDTO inputalunodto = new InputAddAlunoDTO(1L, 100L);
        assertThrows(IllegalStateException.class, () -> this.alunoCursoService.addAlunoEmCurso(inputalunodto));
    }

    @Test
    public void getAlunoCursos() {
        InputAddAlunoDTO inputalunodto = new InputAddAlunoDTO(1L, 1L);
        AlunoCurso al = this.alunoCursoService.addAlunoEmCurso(inputalunodto);
        AlunoCurso alunocurso = this.alunoCursoService.getAlunoCurso(al.getId());
        assertEquals(alunocurso.getId(), al.getId());
    }

    @Test
    @Transactional
    public void terminaCurso() {
        InputAddAlunoDTO inputalunodto = new InputAddAlunoDTO(1L, 1L);
        AlunoCurso al = this.alunoCursoService.addAlunoEmCurso(inputalunodto);
        AlunoCurso alunocurso = this.alunoCursoService.getAlunoCurso(al.getId());
        Nota nota1 = new Nota();
        nota1.setAlunoCurso(alunocurso);
        nota1.setValor(new NotaValue(10));
        Nota nota2 = new Nota();
        nota2.setAlunoCurso(alunocurso);
        nota2.setValor(new NotaValue(10));
        Nota nota3 = new Nota();
        nota3.setAlunoCurso(alunocurso);
        nota3.setValor(new NotaValue(10));
        this.notarepo.save(nota1);
        this.notarepo.save(nota2);
        this.notarepo.save(nota3);
        InputTerminarCursoDTO input = new InputTerminarCursoDTO(alunocurso.getId());
        this.alunoCursoService.terminaCurso(input);
        AlunoCurso aluno = this.alunoCursoService.getAlunoCurso(alunocurso.getId());
        assertEquals(aluno.getStatus(), AlunoCursoStatus.CONCLUIDO);
    }
}
