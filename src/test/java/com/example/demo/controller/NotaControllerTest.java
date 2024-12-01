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
import com.example.demo.domain.interfaces.ICursoNotaService;
import com.example.demo.domain.interfaces.ICursoService;
import com.example.demo.domain.valueObjects.Email;
import com.example.demo.domain.valueObjects.NotaValue;
import com.example.demo.domain.valueObjects.Ra;
import com.example.demo.infra.controller.NotaController;
import com.example.demo.infra.controller.dto.InputCreateAlunoDTO;
import com.example.demo.infra.controller.dto.InputNotaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@WebMvcTest(NotaController.class)
public class NotaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ICursoNotaService cursoNotaService;

    @Test
    public void testAddNota() throws Exception{
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

        Nota notaMock = new Nota();
        notaMock.setId(1L);
        NotaValue notaValue = new NotaValue(5);
        notaMock.setValor(notaValue);
        notaMock.setAlunoCurso(alunoCursoMock);

        InputNotaDTO dto = new InputNotaDTO(5,alunoCursoMock.getId());

        Mockito.when(cursoNotaService.addNota(Mockito.any(InputNotaDTO.class))).thenReturn(notaMock);

        String body = objectMapper.writeValueAsString(dto); 

        mockMvc.perform(MockMvcRequestBuilders.post("/nota")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
