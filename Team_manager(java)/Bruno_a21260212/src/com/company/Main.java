package com.company;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
	// write your code here

        Scanner input = new Scanner(System.in);
        int opcao = 1;
        int n;

        Equipa TPSI = new Equipa();

        TPSI.addAtleta(new Avancado("Joao", LocalDate.of(1990, Month.OCTOBER, 10),1.2,200,68,0,0,0));

        TPSI.addAtleta(new Medio("ana", LocalDate.of(1992, Month.MARCH, 4),1,200,30,6,0,0));

        TPSI.addAtleta(new Guarda_redes("joana", LocalDate.of(1989, Month.FEBRUARY, 6),1,200,90,3));


        while (opcao != 0) {


            System.out.println( "1 - eliminar atleta");
            System.out.println( "2 - obter atleta");
            System.out.println( "3 - salario equipa anual");
            System.out.println( "4 - lista dos jogadores");
            System.out.println( "5 - melhor jogador de campo");
            System.out.println( "6 - gravar binario");
            System.out.println( "7 - ler binario");
            System.out.println( "8 - gravar em texto");

            opcao = input.nextInt();

            switch (opcao) {

                case 1:
                    TPSI.listar_nomes();

                    System.out.print("Jogador que quer apagar? ");

                    n=input.nextInt();

                    TPSI.delAtleta(n);

                    break;

                case 2:

                    TPSI.listar_nomes();

                    System.out.print("Jogador que quer obter? ");

                    n=input.nextInt();

                    TPSI.obter(n);

                    break;

                case 3:

                    System.out.print("Jogador que quer obter? ");

                    break;

                case 4:

                    System.out.println("Jogador de campo: ");
                    TPSI.jogadores_campo();

                    break;

                case 5:

                    System.out.println("Melhor jogador de campo: ");

                    TPSI.melhor_jogadores_campo();

                    break;
                case 6:

                    TPSI.esc_equipa_bin("Equipa");

                    break;
                case 7:

                    TPSI.ler_equipa_bin("Equipa");

                    break;
                case 8:


                    TPSI.equipa_text("Equipa_text");

                    break;
            }
        }

    }
}
