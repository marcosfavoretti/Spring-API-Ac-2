package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.entity.Nota;
import com.example.demo.domain.interfaces.ICursoNotaService;
import com.example.demo.domain.valueObjects.NotaValue;
import com.example.demo.infra.controller.dto.InputNotaDTO;
import com.example.demo.infra.repositories.IAlunoCursoRepository;
import com.example.demo.infra.repositories.ICursoNotaRepository;

@Service
public class CursoNotaService implements ICursoNotaService {
    @Autowired
    ICursoNotaRepository cursoNotaRepo;

    @Autowired
    IAlunoCursoRepository alunoCursoRepo;

    @Override
    public Nota addNota(InputNotaDTO dto) {
        Optional<AlunoCurso> alunoCursoOp = this.alunoCursoRepo.findById(dto.idAlunoCurso);
        if (!alunoCursoOp.isPresent()) {
            throw new IllegalStateException("AlunoCurso não encontrado para o ID: " + dto.idAlunoCurso);
        }
        if (!this.notasPendentes(alunoCursoOp.get())) {
            throw new IllegalStateException("O aluno ja foi avaliado 3 vezes (valor maximo).");
        }
        AlunoCurso alunoCurso = alunoCursoOp.get();
        Nota nota = new Nota();
        nota.setAlunoCurso(alunoCurso);
        nota.setValor(new NotaValue(dto.valor));
        this.cursoNotaRepo.save(nota);
        return nota;
    }

    @Override
    public List<Nota> listNotasDoAluno(AlunoCurso alunoCurso) {
        Optional<List<Nota>> notas = this.alunoCursoRepo.findNotasByAlunoCurso(alunoCurso);
        System.out.println(notas.get());
        return notas.isPresent() ? notas.get() : new ArrayList<>();
    }

    @Override
    public boolean notasPendentes(AlunoCurso alunoCurso) {
        System.out.println( "\n\n"+this.listNotasDoAluno(alunoCurso).size());
        return this.listNotasDoAluno(alunoCurso).size() < 3;
    }

}
