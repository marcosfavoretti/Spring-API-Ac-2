package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.interfaces.IAlunoService;
import com.example.demo.domain.valueObjects.Email;
import com.example.demo.domain.valueObjects.Ra;
import com.example.demo.infra.controller.dto.InputCreateAlunoDTO;
import com.example.demo.infra.repositories.IAlunosRepository;


@Service
public class AlunoService implements IAlunoService{
    @Autowired
    IAlunosRepository alunoRepo;

    public Aluno criarAluno(InputCreateAlunoDTO dto){
        Aluno aluno = new Aluno();
        aluno.setDataInicio(new Date());
        aluno.setEmail(new Email(dto.email));
        aluno.setIdade(dto.idade);
        aluno.setNome(dto.nome);
        aluno.setRa(new Ra(dto.ra));
        this.alunoRepo.save(aluno);
        return aluno;
    }

}
