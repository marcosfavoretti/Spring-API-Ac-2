package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.enumerations.CursoStt;


@Service
public class GerenciaCurso {

    

    public void finalizaCurso(Aluno aluno, Curso curso) {
        // if(!aluno.hasCurso(curso) ) throw new IllegalStateException("o aluno nao tem o curso");
        
        // if( !curso.podeFechar()) throw new IllegalStateException("o aluno nao pode fechar o curso informado");
        // if(curso.getMedia() > 7.00 ) this.darMais3Cursos(aluno);
        // curso.setStt(CursoStt.FECHADO);
        
    }

    private void darMais3Cursos(Aluno aluno){
        // System.out.println("parabens vc ganou 3 cursos");
        // aluno
        //     .addCurso(new Curso("C#", 80, 10))
        //     .addCurso(new Curso("Python", 9.99, 10))
        //     .addCurso(new Curso("JS", 90, 10));
    }
        // public void finalizaCurso(Aluno aluno, Curso curso) {
        //     throw new UnsupportedOperationException();
        //     // return;
        // }
}
