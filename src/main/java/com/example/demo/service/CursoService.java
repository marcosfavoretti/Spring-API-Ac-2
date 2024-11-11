package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.interfaces.ICursoService;
import com.example.demo.infra.repositories.ICursoRepository;

public class CursoService implements ICursoService{
    @Autowired
    private ICursoRepository cursoRep;

    @Override
    public Curso createCurso(Curso curso) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void finalizaCurso() {
        // TODO Auto-generated method stub
        
    }
    public ICursoRepository getCursoRep() {
        return cursoRep;
    }
    public void setCursoRep(ICursoRepository cursoRep) {
        this.cursoRep = cursoRep;
    }
    
}
