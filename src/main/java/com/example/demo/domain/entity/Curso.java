package com.example.demo.domain.entity;

import java.util.ArrayList;

import com.example.demo.domain.enumerations.CursoStt;

public class Curso {
    private CursoStt stt;
    private String nome;
    private double valor;
    private int duracao;
    private ArrayList<Integer> notas = new ArrayList<>();

    public Curso(String nome, double valor, int duracao) {
        this.nome = nome;
        this.valor = valor;
        this.duracao = duracao;
        this.stt = CursoStt.FAZENDO;
    }

    public void setStt(CursoStt stt){
        this.stt = stt;
    }
    public CursoStt getStt(){
        return this.stt;
    }
    public String getNome() {
        return nome;
    }
    public double getValor() {
        return valor;
    }
    public int getDuracao() {
        return duracao;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    
    public Curso submitNota(int nota){
        if(this.notas.size() == 3) throw new IllegalStateException("Erro ao adicionar mais uma nota");
        this.notas.add(nota);
        return this;
    }

    public boolean podeFechar(){
        return this.notas.size() == 3 ? true : false;
    }

    public double getMedia(){
        if(!this.podeFechar()) throw new IllegalStateException("nao foi feita todas as provas ainda");
        int finalSum = 0;
        for(int nota : this.notas){
            finalSum += nota;
        }
        return finalSum/this.notas.size();
    }
}
