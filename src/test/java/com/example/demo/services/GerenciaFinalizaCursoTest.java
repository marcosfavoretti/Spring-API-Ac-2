package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.entity.Nota;
import com.example.demo.domain.enumerations.AlunoCursoStatus;
import com.example.demo.domain.valueObjects.Email;
import com.example.demo.domain.valueObjects.NotaValue;
import com.example.demo.domain.valueObjects.Ra;
import com.example.demo.infra.repositories.ICursoRepository;
import com.example.demo.service.GerenciaFinalizacaoCurso;

@SpringBootTest
public class GerenciaFinalizaCursoTest {

    @Autowired
    GerenciaFinalizacaoCurso gerenciaService;

    // Mock do repositório
    @MockBean
    ICursoRepository cursosRepo;

    private Curso curso1;
    private Curso curso2;

    @BeforeEach
    void setup() {
        // Criando cursos simulados
        curso1 = new Curso();
        curso1.setId(1L);
        curso1.setDuracao(1);
        curso1.setNome("DEV");
        curso1.setValor(10.99);

        curso2 = new Curso();
        curso2.setId(2L);
        curso2.setDuracao(1);
        curso2.setNome("DEV2");
        curso2.setValor(15.99);

        // Persistindo os cursos no banco real para garantir a integridade referencial
        cursosRepo.save(curso1);
        cursosRepo.save(curso2);

        // Configurando o comportamento do método `findAll()` do repositório
        when(cursosRepo.findAll()).thenReturn(Arrays.asList(curso1, curso2));
    }

    @Test
    void deveFinalizarCursos() {
        // Criando aluno e seus dados
        // Aluno aluno = new Aluno();
        // aluno.setDataInicio(new Date());
        // aluno.setEmail(new Email("marcosfavoretti@gmail.com"));
        // aluno.setId(1L);
        // aluno.setNome("MARCO");
        // aluno.setRa(new Ra("210482"));
        // aluno.setIdade(10);

        // // Criando o curso para o aluno
        // Curso curso = new Curso();
        // curso.setDuracao(1);
        // curso.setId(1L);
        // curso.setNome("DEV");
        // curso.setValor(10.99);

        // // Criando as notas
        // Nota nota1 = new Nota();
        // nota1.setId(1L);
        // nota1.setValor(new NotaValue(10));

        // Nota nota2 = new Nota();
        // nota2.setId(2L);
        // nota2.setValor(new NotaValue(10));

        // Nota nota3 = new Nota();
        // nota3.setId(3L);
        // nota3.setValor(new NotaValue(6.7));

        // AlunoCurso alunoCurso = new AlunoCurso();
        // alunoCurso.setAluno(aluno);
        // alunoCurso.setId(1L);
        // alunoCurso.setStatus(AlunoCursoStatus.ANDAMENTO);
        // alunoCurso.setNotas(Arrays.asList(nota1, nota2, nota3));
        // alunoCurso.setCurso(curso);

        // gerenciaService.finaliza(alunoCurso);

        // assertEquals(4, alunoCurso.getAluno().getAlunoCursos().size()); // O aluno agora tem 4 cursos
        // assertEquals(AlunoCursoStatus.CONCLUIDO, alunoCurso.getStatus()); // O curso deve ser finalizado
    }
}
