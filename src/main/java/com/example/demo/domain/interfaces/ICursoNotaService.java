package com.example.demo.domain.interfaces;

import java.util.List;

import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.entity.Nota;
import com.example.demo.infra.controller.dto.InputNotaDTO;

public interface ICursoNotaService {
    Nota addNota(InputNotaDTO dto);
    boolean notasPendentes(AlunoCurso alunoCurso);
    List<Nota> listNotasDoAluno(AlunoCurso alunoCurso);
}
