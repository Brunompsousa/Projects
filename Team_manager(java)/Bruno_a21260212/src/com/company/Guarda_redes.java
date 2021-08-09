package com.company;

import java.time.LocalDate;

public class Guarda_redes extends Atleta{

    int remates_defendidos;

    public int getRemates_defendidos() {
        return remates_defendidos;
    }

    public Guarda_redes(String nome, LocalDate data_nascimento, double altura, int salario_mensal_bruto, int tempo_jogo, int remates_defendidos) {
        super(nome, data_nascimento, altura, salario_mensal_bruto, tempo_jogo);
        this.remates_defendidos = remates_defendidos;
    }

    @Override
    public String toString() {
        System.out.println("Remates defendidos: " + remates_defendidos);
        return super.toString();
    }
}
