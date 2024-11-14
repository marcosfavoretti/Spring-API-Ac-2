package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.valueObjects.Email;
import com.example.demo.domain.valueObjects.Ra;
import com.example.demo.infra.repositories.IAlunosRepository;

@ActiveProfiles("test")
@DataJpaTest
public class AlunoRepositoryTest {
     @Autowired
    private IAlunosRepository alunoRepo;

    @Test
    void testCriarAluno() {
        Aluno aluno = new Aluno();
        aluno.setAlunoCursos(new HashSet<>());
        aluno.setDataInicio((new Date()));
        aluno.setEmail(new Email("marcos@gmail.com"));
        aluno.setIdade(18);
        aluno.setNome("Marcos");
        aluno.setRa(new Ra("210482"));
        Aluno new_Aluno = this.alunoRepo.save(aluno);
        assertNotNull(new_Aluno.getId()); // Verifique se o ID foi gerado
        assertEquals("Marcos", new_Aluno.getNome()); // Verifique o nome do curso
    }


}
