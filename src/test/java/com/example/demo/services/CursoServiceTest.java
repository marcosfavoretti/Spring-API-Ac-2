package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.interfaces.ICursoService;
import com.example.demo.infra.controller.dto.InputCreateCursoDTO;
import com.example.demo.service.CursoService;

@SpringBootTest
public class CursoServiceTest {
    @Autowired  
    CursoService cursoService;
    @Test
    void createCurso(){
        InputCreateCursoDTO dto = new InputCreateCursoDTO("dev", 10, 10.00);
        Curso curso = this.cursoService.createCurso(dto);
        assertNotNull(curso.getId());
        assertEquals(dto.nome, curso.getNome());
        assertEquals(dto.valor, curso.getValor());
        assertEquals(dto.duracao, curso.getDuracao());
    }

}
