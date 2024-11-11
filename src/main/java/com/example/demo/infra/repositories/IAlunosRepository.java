package com.example.demo.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Aluno;


@Repository
public interface IAlunosRepository extends JpaRepository<Aluno, Long>{}