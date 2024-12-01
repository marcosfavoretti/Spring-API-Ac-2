package com.example.demo.controller;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.entity.AlunoCurso;
import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.entity.Nota;
import com.example.demo.domain.enumerations.AlunoCursoStatus;
import com.example.demo.domain.interfaces.IAlunoCursoService;
import com.example.demo.domain.valueObjects.Email;
import com.example.demo.domain.valueObjects.Ra;
import com.example.demo.infra.controller.AlunoCursoController;
import com.example.demo.infra.controller.dto.InputAddAlunoDTO;
import com.example.demo.infra.controller.dto.InputTerminarCursoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@WebMvcTest(AlunoCursoController.class)
public class AlunoCursoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IAlunoCursoService alunoCursoService;

    @Test
    public void testAddAlunoEmCurso() throws Exception{
        Aluno alunoMock = new Aluno();
        alunoMock.setId(1L);
        Email email = new Email("teste@gmail.com");
        alunoMock.setEmail(email);
        alunoMock.setIdade(19);
        alunoMock.setNome("Fulano");
        Ra ra = new Ra("123123");
        alunoMock.setRa(ra);

        Curso cursoMock = new Curso();
        cursoMock.setId(1L);
        cursoMock.setDuracao(40);
        cursoMock.setNome("CursoTeste");
        cursoMock.setValor(25.50);

        AlunoCurso alunoCursoMock = new AlunoCurso();
        alunoCursoMock.setId(1L);
        alunoCursoMock.setAluno(alunoMock);
        alunoCursoMock.setCurso(cursoMock);
        alunoCursoMock.setStatus(AlunoCursoStatus.ANDAMENTO); 
        alunoCursoMock.setNotas(new ArrayList<Nota>());

        InputAddAlunoDTO dto = new InputAddAlunoDTO(
            alunoMock.getId(),
            cursoMock.getId());

        Mockito.when(alunoCursoService.addAlunoEmCurso(Mockito.any(InputAddAlunoDTO.class))).thenReturn(alunoCursoMock);
        
        String body = objectMapper.writeValueAsString(dto); 

        mockMvc.perform(MockMvcRequestBuilders.post("/alunoCurso")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFinalizaCurso() throws Exception{
        Aluno alunoMock = new Aluno();
        alunoMock.setId(1L);
        Email email = new Email("teste@gmail.com");
        alunoMock.setEmail(email);
        alunoMock.setIdade(19);
        alunoMock.setNome("Fulano");
        Ra ra = new Ra("123123");
        alunoMock.setRa(ra);

        Curso cursoMock = new Curso();
        cursoMock.setId(1L);
        cursoMock.setDuracao(40);
        cursoMock.setNome("CursoTeste");
        cursoMock.setValor(25.50);

        AlunoCurso alunoCursoMock = new AlunoCurso();
        alunoCursoMock.setId(1L);
        alunoCursoMock.setAluno(alunoMock);
        alunoCursoMock.setCurso(cursoMock);
        alunoCursoMock.setStatus(AlunoCursoStatus.ANDAMENTO); 
        alunoCursoMock.setNotas(new ArrayList<Nota>());

        InputTerminarCursoDTO dto = new InputTerminarCursoDTO(alunoCursoMock.getId());
        
        String body = objectMapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/alunoCurso")
        .contentType(MediaType.APPLICATION_JSON)
        .content(body)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
