package com.example.demo.infra.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.interfaces.IAlunoCursoService;
import com.example.demo.infra.controller.dto.InputAddAlunoDTO;
import com.example.demo.infra.controller.dto.InputTerminarCursoDTO;
import com.example.demo.infra.controller.dto.OutputAlunoCursoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // @GetMapping("/{idAlunoCurso}")
    // public OutputAlunoCursoDTO getAlunoCurso(@PathVariable long idAlunoCurso) {
    //     AlunoCurso alunocurso =  this.alunoCursoService.getAlunoCurso(idAlunoCurso);
    //     return new OutputAlunoCursoDTO(alunocurso.getAluno(), alunocurso.getNotas(), alunocurso.getCurso(), alunocurso.getStatus());
    // }
}
