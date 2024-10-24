package com.example.demo;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.service.Autenticao;
import com.example.demo.domain.service.GerenciaCurso;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GerenciaCursoTest {

        @Autowired
        private Autenticao autenticacao;
        @Autowired
        private GerenciaCurso gerenciadorCurso;

        private Aluno alunoMock;
        private Curso cursoMock;

        @BeforeEach
        public void criaAluno() {
                this.alunoMock = new Aluno("marcos", "marcos@gmail.com", 21, new Date());
                this.cursoMock = new Curso("JAVA", 135.20, 15);
        }

        @Test
        public void terminouCursoAcimaMedia() {
                this.cursoMock
                                .submitNota(8)
                                .submitNota(10)
                                .submitNota(8);
                this.alunoMock.addCurso(this.cursoMock);
                int qtdCursoInicial = this.alunoMock.getCursos().size();
                boolean isAutenticado = this.autenticacao.valida(this.alunoMock);
                assertEquals(isAutenticado, true);
                this.gerenciadorCurso.finalizaCurso(this.alunoMock, this.cursoMock);
                assertEquals(
                                qtdCursoInicial + 3,
                                this.alunoMock.getCursos().size(),
                                "Ao terminar com media maior que s7, o aluno nao deve ser recompensado com 3 cursos");
        }

        @Test
        public void terminouCursoAbaixoMedia() {
                this.cursoMock
                                .submitNota(7)
                                .submitNota(7)
                                .submitNota(7);
                this.alunoMock.addCurso(this.cursoMock);
                int qtdCursoInicial = this.alunoMock.getCursos().size();
                boolean isAutenticado = this.autenticacao.valida(this.alunoMock);
                assertEquals(isAutenticado, true);
                this.gerenciadorCurso.finalizaCurso(this.alunoMock, this.cursoMock);
                assertEquals(
                                qtdCursoInicial,
                                this.alunoMock.getCursos().size(),
                                "Ao terminar com media menor ou igual que 7, o aluno nao deve ser recompensado com 3 cursos");
        }

        @Test
        public void naoTerminouCurso() {
                int qtdCursoInicial;
                this.alunoMock.addCurso(this.cursoMock);
                qtdCursoInicial = this.alunoMock.getCursos().size();
                try {
                        this.cursoMock
                                        .submitNota(8);
                        // so realizou uma prova logo nao terminou todo o curso;
                        boolean isAutenticado = this.autenticacao.valida(this.alunoMock);
                        assertEquals(isAutenticado, true);
                        this.gerenciadorCurso.finalizaCurso(this.alunoMock, this.cursoMock);
                        // assertThrows(
                        //                 IllegalStateException.class,
                        //                 () -> this.gerenciadorCurso.finalizaCurso(this.alunoMock, this.cursoMock),
                        //                 "ao finalizar um curso que nao foi acabado o metodo deve retornar um exception");
                } catch (Exception e) {
                        assertEquals(
                                qtdCursoInicial,
                                this.alunoMock.getCursos().size(),
                                "e nao deve continuar com a msm quantidade de cursos");
                }
             
        }
}
