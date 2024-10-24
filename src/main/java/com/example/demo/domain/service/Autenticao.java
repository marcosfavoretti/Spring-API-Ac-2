package com.example.demo.domain.service;



import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.interfaces.IAutenticacao;



@Service
public class Autenticao implements IAutenticacao{
    public boolean valida(Aluno aluno) {
        return aluno.getNome() == "marcos";
    }
    
}
