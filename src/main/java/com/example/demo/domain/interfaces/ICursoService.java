package com.example.demo.domain.interfaces;

import com.example.demo.domain.entity.Curso;

public interface ICursoService {
    public Curso createCurso(Curso curso);
    public void finalizaCurso();
}
