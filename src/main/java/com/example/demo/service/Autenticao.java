package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.interfaces.IAutenticacao;
import com.example.demo.domain.interfaces.IConsultOnlineUsers;

@Service
public class Autenticao implements IAutenticacao {

    public boolean valida(Aluno aluno) {
        return true;
        // return onlineUserService.get(aluno.getId()) != null ? true : false;
    }
}
