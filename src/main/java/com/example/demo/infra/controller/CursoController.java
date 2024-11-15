package com.example.demo.infra.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.InputCreateCursoDTO;
import com.example.demo.domain.dto.OutputGetAllCursoDTO;
import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.interfaces.ICursoService;
import com.example.demo.service.CursoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/Curso")
public class CursoController {
    
    @Autowired
    private final ICursoService cursoService;

    
    public CursoController(ICursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<OutputGetAllCursoDTO> getAllCurso() {
        return this.cursoService.getAllCurso();
    }

    @PostMapping
    public Curso postMethodName(@RequestBody InputCreateCursoDTO dto) {
        Curso cursoCreated = cursoService.createCurso(dto);
        
        return cursoCreated;
    }
    
    
}
