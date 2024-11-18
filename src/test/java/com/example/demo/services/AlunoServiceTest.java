package com.example.demo.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.entity.Aluno;
import com.example.demo.domain.valueObjects.Email;
import com.example.demo.domain.valueObjects.Ra;
import com.example.demo.infra.controller.dto.InputCreateAlunoDTO;
import com.example.demo.service.AlunoService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AlunoServiceTest {

    @Autowired
    AlunoService alunoService;

    @Test
    void criarAluno_deveRetornarObjetoCriado() {

        InputCreateAlunoDTO dto = new InputCreateAlunoDTO(
                "Marcos",
                "marcosfavoretti@gmail.com",
                "210482",
                21);

        Aluno alunoMock = new Aluno();
        alunoMock.setId(1L);
        alunoMock.setNome(dto.nome);
        alunoMock.setEmail(new Email(dto.email));
        alunoMock.setIdade(dto.idade);
        alunoMock.setRa(new Ra(dto.ra));

        Aluno alunoCriado = alunoService.criarAluno(dto);

        assertNotNull(alunoCriado.getId(), "O id retornado não deve ser nulo");
        assertEquals(dto.nome, alunoCriado.getNome(), "O nome deve ser igual ao fornecido no DTO");
        assertEquals(dto.email, alunoCriado.getEmail().getEmail(), "O email deve ser igual ao fornecido no DTO");
        assertEquals(dto.idade, alunoCriado.getIdade(), "A idade deve ser igual à fornecida no DTO");
        assertEquals(dto.ra, alunoCriado.getRa(), "O ra deve ser igual à fornecida no DTO");
    }
}
