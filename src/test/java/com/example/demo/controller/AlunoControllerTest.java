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

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.interfaces.IAlunoService;
import com.example.demo.domain.valueObjects.Email;
import com.example.demo.domain.valueObjects.Ra;
import com.example.demo.infra.controller.AlunoController;
import com.example.demo.infra.controller.dto.InputCreateAlunoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IAlunoService alunoService;

    @Test
    public void testCreateAluno() throws Exception{
        Aluno alunoMock = new Aluno();
        alunoMock.setId(1L);
        Email email = new Email("teste@gmail.com");
        alunoMock.setEmail(email);
        alunoMock.setIdade(19);
        alunoMock.setNome("Fulano");
        Ra ra = new Ra("123123");
        alunoMock.setRa(ra);
        
        InputCreateAlunoDTO dto = new InputCreateAlunoDTO(
            alunoMock.getNome(),
            alunoMock.getEmail().getEmail(),
            alunoMock.getRa(),
            alunoMock.getIdade());

        Mockito.when(alunoService.criarAluno(Mockito.any(InputCreateAlunoDTO.class))).thenReturn(alunoMock);

        String body = objectMapper.writeValueAsString(dto); 

        mockMvc.perform(MockMvcRequestBuilders.post("/aluno")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Fulano"));
    }
}
