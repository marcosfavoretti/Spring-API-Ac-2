package com.example.demo.domain.interfaces;

import java.util.List;

import com.example.demo.domain.entity.Curso;
import com.example.demo.infra.controller.dto.InputCreateCursoDTO;
import com.example.demo.infra.controller.dto.OutputGetAllCursoDTO;

public interface ICursoService {
    public List<OutputGetAllCursoDTO> getAllCurso();
    public Curso createCurso(InputCreateCursoDTO curso);
}
