package com.company;

import java.time.LocalDate;
import java.time.Period;
import java.io.*;

public abstract class Atleta implements Serializable {

    //variaveis
    String nome;
    LocalDate data_nascimento;
    double altura;
    int salario_mensal_bruto;
    int tempo_jogo;

    public Atleta(String nome, LocalDate data_nascimento, double altura, int salario_mensal_bruto, int tempo_jogo) {
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.altura = altura;
        this.salario_mensal_bruto = salario_mensal_bruto;
        this.tempo_jogo = tempo_jogo;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public double getAltura() {
        return altura;
    }

    public int getSalario_mensal_bruto() {
        return salario_mensal_bruto;
    }

    public int getTempo_jogo() {
        return tempo_jogo;
    }

    public int getSalario_anual(){

        return salario_mensal_bruto * 14;

    }

    public String getNome() {
        return nome;
    }

    public int getIdade()
    {
        LocalDate today = LocalDate.now();
        Period p = Period.between(data_nascimento, today);
        return p.getYears();
    }

    @Override
    public String toString() {

        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + getIdade());
        System.out.println("Altura: " + altura + "m");
        System.out.println("Salario mensal bruto: " + salario_mensal_bruto + "€");
        System.out.println("Tempo de jogo: " + tempo_jogo + "horas");

        return super.toString();
    }
}
