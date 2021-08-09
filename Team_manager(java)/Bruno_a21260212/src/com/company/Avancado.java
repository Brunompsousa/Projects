package com.company;

import java.time.LocalDate;

public class Avancado extends Jogadore_campo{
    public Avancado(String nome, LocalDate data_nascimento, double altura, int salario_mensal_bruto, int tempo_jogo, int golos_marcados, int num_assistencias, int bolas_cortadas) {
        super(nome, data_nascimento, altura, salario_mensal_bruto, tempo_jogo, golos_marcados, num_assistencias, bolas_cortadas);
    }

    @Override
    public int Qualidade_jogador(int a, int b, int c) {
        return super.Qualidade_jogador(3, 2, 1);
    }
}
