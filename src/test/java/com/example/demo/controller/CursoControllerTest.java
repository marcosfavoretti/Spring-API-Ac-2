package com.example.demo.controller;

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

import com.example.demo.domain.entity.Curso;
import com.example.demo.domain.interfaces.ICursoService;
import com.example.demo.infra.controller.CursoController;
import com.example.demo.infra.controller.dto.InputCreateCursoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@WebMvcTest(CursoController.class)
public class CursoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ICursoService cursoService;

    @Test
    public void testCreateCurso() throws Exception{
        Curso cursoMock = new Curso();
        cursoMock.setId(1L);
        cursoMock.setDuracao(40);
        cursoMock.setNome("CursoTeste");
        cursoMock.setValor(25.50);

        InputCreateCursoDTO dto = new InputCreateCursoDTO(
            cursoMock.getNome(),
            cursoMock.getDuracao(),
            cursoMock.getValor()
        );

        Mockito.when(cursoService.createCurso(Mockito.any(InputCreateCursoDTO.class))).thenReturn(cursoMock);

        String body = objectMapper.writeValueAsString(dto);

         mockMvc.perform(MockMvcRequestBuilders.post("/curso")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("CursoTeste"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.duracao").value(40))
            .andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(25.50));
    }

}
