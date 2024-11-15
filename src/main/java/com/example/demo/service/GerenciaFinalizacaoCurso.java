package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.entity.Nota;
import com.example.demo.domain.enumerations.AlunoCursoStatus;
import com.example.demo.domain.interfaces.IGerenciaFinalizacaoStarategy;
import com.example.demo.infra.repositories.ICursoRepository;

@Service
public class GerenciaFinalizacaoCurso implements IGerenciaFinalizacaoStarategy {
    @Autowired
    ICursoRepository cursoRepo;

    public void finaliza(AlunoCurso alunoCurso) {
        List<Nota> notas = alunoCurso.getNotas();
        double media = this.mediaDaNota(notas);
        if (media >= 7) {
            List<Curso> cursos = this.cursoRepo.findAll();
            List<Curso> cursosNaoAssociados = cursos.stream()
                    .filter(curso -> !curso.getAlunoCursos().contains(alunoCurso))
                    .limit(3)
                    .toList();
            int maxCursos = Math.min(cursosNaoAssociados.size(), 3);
            List<Curso> cursosNovos = cursosNaoAssociados.subList(0, maxCursos);
            cursosNovos.stream().forEach(curso -> {
                System.out.println("ganhou o curso"+ curso.getId() + curso.getNome());
                curso.addAlunoCurso(alunoCurso);
                this.cursoRepo.save(curso);
            });
        }
        alunoCurso.setStatus(AlunoCursoStatus.CONCLUIDO);
    }

    private double mediaDaNota(List<Nota> notas) {
        double sum = notas.stream()
                .map(Nota::getValor)
                .reduce(0.0, Double::sum);
        return sum / notas.size();
    }
}
