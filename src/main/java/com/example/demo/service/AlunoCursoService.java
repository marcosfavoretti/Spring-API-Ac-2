package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.enumerations.AlunoCursoStatus;
import com.example.demo.domain.interfaces.IAlunoCursoService;
import com.example.demo.domain.interfaces.ICursoNotaService;
import com.example.demo.domain.interfaces.IGerenciaFinalizacaoStarategy;
import com.example.demo.infra.controller.dto.InputAddAlunoDTO;
import com.example.demo.infra.controller.dto.InputTerminarCursoDTO;
import com.example.demo.infra.repositories.IAlunoCursoRepository;
import com.example.demo.infra.repositories.IAlunosRepository;
import com.example.demo.infra.repositories.ICursoRepository;

@Service
public class AlunoCursoService implements IAlunoCursoService {
    @Autowired
    IAlunoCursoRepository alunoCursoRepo;

    @Autowired
    ICursoRepository cursoRepo;

    @Autowired
    IAlunosRepository alunoRepo;

    @Autowired
    ICursoNotaService cursoNotaService;

    @Autowired
    IGerenciaFinalizacaoStarategy gerennciaFinalizacao;

    @Override
    public AlunoCurso addAlunoEmCurso(InputAddAlunoDTO dto) {
        Optional<Aluno> aluno = this.alunoRepo.findById(dto.idAluno);
        Optional<Curso> curso = this.cursoRepo.findById(dto.idCurso);
        if (!aluno.isPresent() || !curso.isPresent())
            throw new IllegalStateException();
        // se der tempo colocar um exception personalizada
        AlunoCurso alunocurso = new AlunoCurso();
        alunocurso.setAluno(aluno.get());
        alunocurso.setStatus(AlunoCursoStatus.ANDAMENTO);
        alunocurso.setCurso(curso.get());
        this.alunoCursoRepo.save(alunocurso);
        return alunocurso;
    }

    @Override
    public AlunoCurso getAlunoCurso(long idAlunoCurso) {
        Optional<AlunoCurso> alunocurso = this.alunoCursoRepo.findById(idAlunoCurso);
        if(!alunocurso.isPresent()) throw new IllegalStateException("O aluno nao foi achado");
        return alunocurso.get();
    }

    @Override
    public void terminaCurso(InputTerminarCursoDTO dto) {
        Optional<AlunoCurso> alunoCursoOP = this.alunoCursoRepo.findById(dto.idAlunoCurso);
        if(!alunoCursoOP.isPresent()) throw new IllegalStateException("nao foi achado no banco o aluno nesse curso");
        if(alunoCursoOP.get().getStatus().equals(AlunoCursoStatus.CONCLUIDO))  throw new IllegalStateException("O usuario ja terminou esse curso");
        AlunoCurso alunoCurso = alunoCursoOP.get();
        boolean temNotasPendentes = this.cursoNotaService.notasPendentes(alunoCurso);
        if(temNotasPendentes) throw new IllegalStateException("o aluno nao terminou o ciclo de avaliacao");//com notas pendentes gera exception
        this.gerennciaFinalizacao.finaliza(alunoCurso);
        this.alunoCursoRepo.save(alunoCurso);
    }
}
