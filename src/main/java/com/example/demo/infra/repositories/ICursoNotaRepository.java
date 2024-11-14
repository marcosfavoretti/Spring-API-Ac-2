package com.example.demo.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.entity.Nota;

public interface ICursoNotaRepository extends JpaRepository<Nota, Long> {}
