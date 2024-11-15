package com.example.demo.domain.interfaces;

import java.util.List;

import com.example.demo.domain.dto.InputCreateCursoDTO;
import com.example.demo.domain.dto.OutputGetAllCursoDTO;
import com.example.demo.domain.entity.Curso;

public interface ICursoService {
    public List<OutputGetAllCursoDTO> getAllCurso();
    public Curso createCurso(InputCreateCursoDTO curso);
}
