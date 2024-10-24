package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.service.Autenticao;


@SpringBootTest
class AutenticacaoTests {
    @Autowired
    private Autenticao autenticacao;

    @Test
    public void ValidaQueEstaLogado(){
        Aluno aluno = new Aluno("marcos", "marcos.favo@gmail.com", 21, new Date());
        boolean response = this.autenticacao.valida(aluno);
        assertEquals(true, response);
    }
}
