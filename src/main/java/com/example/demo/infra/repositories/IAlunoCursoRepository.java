package com.example.demo.infra.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.entity.Nota;

@Repository
public interface IAlunoCursoRepository extends JpaRepository<AlunoCurso, Long>{
        @Query("SELECT n FROM Nota n WHERE n.alunoCurso = :alunoCurso")
        Optional<List<Nota>> findNotasByAlunoCurso(@Param("alunoCurso") AlunoCurso alunoCurso);
}
