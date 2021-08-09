package com.company;

import java.time.LocalDate;

public abstract class Jogadore_campo extends Atleta {

    int golos_marcados;
    int num_assistencias;
    int bolas_cortadas;

    public int getGolos_marcados() {
        return golos_marcados;
    }

    public int getNum_assistencias() {
        return num_assistencias;
    }

    public int getBolas_cortadas() {
        return bolas_cortadas;
    }

    public Jogadore_campo(String nome, LocalDate data_nascimento, double altura, int salario_mensal_bruto, int tempo_jogo, int golos_marcados, int num_assistencias, int bolas_cortadas) {
        super(nome, data_nascimento, altura, salario_mensal_bruto, tempo_jogo);
        this.golos_marcados = golos_marcados;
        this.num_assistencias = num_assistencias;
        this.bolas_cortadas = bolas_cortadas;
    }

    @Override
    public String toString() {
        super.toString();
        System.out.println("Golos marcados: " + golos_marcados);
        System.out.println("Numero de assitencias: " + num_assistencias);
        System.out.println("Bolas cortadas: " + bolas_cortadas);
        return super.toString();
    }


    public int Qualidade_jogador(int a, int b, int c){
        int qual = a*golos_marcados+b*num_assistencias+ c*bolas_cortadas;
        return qual;

    }

}
