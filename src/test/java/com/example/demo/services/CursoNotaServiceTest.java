package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

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
    private ICursoNotaRepository notarepo;
    @Autowired
    private IAlunoCursoRepository alunoCursoRep;
    @Autowired
    private ICursoRepository cursoRepository;
    @Autowired
    private IAlunosRepository alunoRepo;
    

    @Test 
    void testAddNota(){
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
        AlunoCurso new_alunoCurso = this.alunoCursoRep.save(alunocurso);

        InputNotaDTO dto = new InputNotaDTO(5, new_alunoCurso.getId());
        Nota nota = cursoNotaService.addNota(dto);

        assertEquals(nota.getValor(), dto.valor);
    }

}
