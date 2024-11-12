package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.domain.entity.Curso;
import com.example.demo.infra.repositories.ICursoRepository;

@ActiveProfiles("test")
@DataJpaTest
public class CursoRepositoryTest {
    @Autowired
    private ICursoRepository cursoRepository;

    @Test
    void criarCursoNoBanco() {
        Curso curso = new Curso();
        curso.setDuracao(2);
        curso.setAlunoCursos(new HashSet<>());
        // curso.setId(2L);
        curso.setValor(1000);
        curso.setNome("Node básico");
        Curso curso_salvo = this.cursoRepository.save(curso);
        assertNotNull(curso_salvo.getId()); // Verifique se o ID foi gerado
        assertEquals("Node básico", curso_salvo.getNome()); // Verifique o nome do curso
    }


    
}
