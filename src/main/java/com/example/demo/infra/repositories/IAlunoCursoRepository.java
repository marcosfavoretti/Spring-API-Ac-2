package com.example.demo.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.AlunoCurso;

@Repository
public interface IAlunoCursoRepository extends JpaRepository<AlunoCurso, Long>{}
