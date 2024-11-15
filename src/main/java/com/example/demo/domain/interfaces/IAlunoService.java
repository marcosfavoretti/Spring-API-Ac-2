package com.example.demo.domain.interfaces;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.infra.controller.dto.InputCreateAlunoDTO;

public interface IAlunoService {
   Aluno criarAluno(InputCreateAlunoDTO dto);
}
