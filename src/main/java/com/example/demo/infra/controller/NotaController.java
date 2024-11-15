package com.example.demo.infra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.interfaces.ICursoNotaService;
import com.example.demo.infra.controller.dto.InputNotaDTO;

@RestController
@RequestMapping("/nota")
public class NotaController {
    @Autowired
    ICursoNotaService cursoNota;

    @PostMapping
    void addNota(@RequestBody InputNotaDTO dto){
        this.cursoNota.addNota(dto);
    }
}
