package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.InputCreateCursoDTO;
import com.example.demo.domain.dto.OutputGetAllCursoDTO;
import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.interfaces.ICursoService;
import com.example.demo.infra.repositories.ICursoRepository;

@Service
public class CursoService implements ICursoService{
    @Autowired
    private ICursoRepository cursoRep;

    @Override
    public Curso createCurso(InputCreateCursoDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.nome);
        curso.setDuracao(dto.duracao);
        curso.setValor(dto.valor);

        cursoRep.save(curso);

        return curso;
    }

    @Override
    public List<OutputGetAllCursoDTO> getAllCurso() {
        List<Curso> cursos = cursoRep.findAll();
        
        return cursos.stream()
              .map(curso -> new OutputGetAllCursoDTO(
                curso.getId(),
                curso.getNome(),
                curso.getDuracao(),
                curso.getValor()
                ))
              .collect(Collectors.toList());
    }
    
}
