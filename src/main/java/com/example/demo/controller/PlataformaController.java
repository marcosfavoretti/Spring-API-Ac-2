package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.service.GerenciaCurso;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PlataformaController {
    private HashMap<Integer,Aluno> alunos = new HashMap<Integer, Aluno>();
    private Curso cursopadrao = new Curso("java", 21231, 21).submitNota(9).submitNota(10).submitNota(10);

    @Autowired
    GerenciaCurso gerenciador;
    PlataformaController(){
        Aluno aluno1 = new Aluno("marcos", "marcos@gmail.com", 21, new Date());
        aluno1.addCurso(
            this.cursopadrao
        );
        Aluno aluno2 = new Aluno("patrick", "patrick@gmail.com", 21, new Date());
        alunos.put(1, aluno1);
        alunos.put(2, aluno2);
    }
    @GetMapping("finaliza")
    public String getMethodName(@RequestParam int id) {
        System.out.println(id);
        Aluno alunotarget = this.alunos.get(id);
        if(alunotarget == null) throw new IllegalStateException();
        this.gerenciador.finalizaCurso(alunotarget, this.cursopadrao);
        return new String("FINALIZADO");
    }
    
}
