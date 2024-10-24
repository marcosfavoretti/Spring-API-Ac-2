package com.example.demo.domain.interfaces;

import com.example.demo.domain.entity.Aluno;

public interface IAutenticacao {
    public boolean valida(Aluno aluno);
}
