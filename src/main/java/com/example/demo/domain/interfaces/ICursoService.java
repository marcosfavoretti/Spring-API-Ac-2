package com.example.demo.domain.interfaces;

import com.example.demo.domain.entity.Curso;
import com.example.demo.infra.controller.dto.InputCreateCursoDTO;

public interface ICursoService {
    // public List<OutputGetAllCursoDTO> getAllCurso();
    public Curso createCurso(InputCreateCursoDTO curso);
}
