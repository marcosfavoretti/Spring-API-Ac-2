package com.example.demo.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.entity.Curso_Nota;

public interface ICurso_NotaRepository extends JpaRepository<Curso_Nota, Long> {}
