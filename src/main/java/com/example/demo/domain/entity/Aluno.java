package com.example.demo.domain.entity;

import java.util.Date;
import java.util.HashSet;

import com.example.demo.domain.valueObjects.Email;
import com.example.demo.domain.valueObjects.Ra;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "tb_aluno")
public class Aluno {

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

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AlunoCurso> alunoCursos = new HashSet<>();

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

    public String getRa() {
        return ra.getRa();
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

    public void addAlunoCurso(AlunoCurso alunoCurso){
        this.alunoCursos.add(alunoCurso);
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Set<AlunoCurso> getAlunoCursos() {
        return alunoCursos;
    }

    public void setAlunoCursos(Set<AlunoCurso> alunoCursos) {
        this.alunoCursos = alunoCursos;
    }

}
