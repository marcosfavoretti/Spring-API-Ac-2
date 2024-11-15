package com.example.demo.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Nota;

@Repository
public interface ICursoNotaRepository extends JpaRepository<Nota, Long> {}
