package com.example.demo.domain.interfaces;

import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.infra.controller.dto.InputAddAlunoDTO;
import com.example.demo.infra.controller.dto.InputTerminarCursoDTO;

public interface IAlunoCursoService {
    AlunoCurso addAlunoEmCurso(InputAddAlunoDTO dto);
    void terminaCurso(InputTerminarCursoDTO dto);
    AlunoCurso getAlunoCurso(long idAlunoCurso);
}
