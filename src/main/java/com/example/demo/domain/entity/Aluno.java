package com.example.demo.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.example.demo.domain.valueObjects.Email;
import com.example.demo.domain.valueObjects.Ra;

import jakarta.annotation.Generated;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_aluno")
public class Aluno {
    // private String nome;
    // private String email;
    // private int idade;
    // private Date dataInicio;
    // private ArrayList<Curso> cursos = new ArrayList<>();
    // public Aluno(String nome, String email, int idade, Date dataInicio) {
    // this.nome = nome;
    // this.email = email;
    // this.idade = idade;
    // this.dataInicio = dataInicio;
    // }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Embedded
    private Email email;

    @Embedded
    private Ra ra;

    private Date dataInicio;

    private int idade;

    @ManyToMany
    @JoinTable(name = "tb_aluno_curso", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "aluno_id"), // Chave estrangeira para Aluno
            inverseJoinColumns = @JoinColumn(name = "curso_id") // Chave estrangeira para Curso
    )
    private List<Curso> cursos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Ra getRa() {
        return ra;
    }

    public void setRa(Ra ra) {
        this.ra = ra;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

}
