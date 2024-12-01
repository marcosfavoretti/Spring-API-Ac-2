package com.example.demo.infra.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.interfaces.IAlunoCursoService;
import com.example.demo.infra.controller.dto.InputAddAlunoDTO;
import com.example.demo.infra.controller.dto.InputTerminarCursoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("alunoCurso")
public class AlunoCursoController {
    @Autowired
    IAlunoCursoService alunoCursoService;

    @PostMapping
    public AlunoCurso addAlunoEmCurso(@RequestBody InputAddAlunoDTO dto) {
        return this.alunoCursoService.addAlunoEmCurso(dto);
    }

    @PostMapping("/finaliza")
    public void finalizaCuroDoAluno(@RequestBody InputTerminarCursoDTO dto) {
        this.alunoCursoService.terminaCurso(dto);
    }
}
