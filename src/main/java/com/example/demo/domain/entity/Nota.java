package com.example.demo.domain.entity;

import jakarta.persistence.OneToMany;

public class Nota {
    @OneToMany(
        mappedBy = Aluno.class
    )
}
