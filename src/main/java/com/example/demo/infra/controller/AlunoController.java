package com.example.demo.infra.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.interfaces.IAlunoService;
import com.example.demo.infra.controller.dto.InputCreateAlunoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    IAlunoService alunoSerivce;

    @PostMapping
    public Aluno criarAluno(@RequestBody InputCreateAlunoDTO dto) {
        Aluno aluno = this.alunoSerivce.criarAluno(dto);
        return aluno;
    }

}
