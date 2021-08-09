package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Equipa {

    Scanner input = new Scanner(System.in);

    ArrayList<Atleta> Equipa1;


    //Construtor base
    public Equipa() {
        Equipa1 = new ArrayList<>();
    }

    //add um atleta
    public void addAtleta(Atleta f){

        Equipa1.add(f);

    }

    // apagar um atleta
    public void delAtleta(int n){

        Equipa1.remove(n-1);

    }

    //listar
    public void listar_nomes(){
        int n = 0;
        for (Atleta atlt: Equipa1
                )
            System.out.println(++n + " " + atlt.getNome());
    }

    //obter
    public String obter(int n){

        return Equipa1.get(n).toString();

    }

    public float salario_equipa_anual(){

        int sal_Equipa_anual = 0;

        for (Atleta atlt: Equipa1
             ) {

            sal_Equipa_anual += atlt.getSalario_anual();

        }

        return sal_Equipa_anual;

    }

    public void jogadores_campo(){

        for (Atleta atlt:Equipa1
             ) {
            if (atlt.getClass().equals(Avancado.class) || atlt.getClass().equals(Medio.class) || atlt.getClass().equals(Defesa.class))
                ((Atleta)atlt).toString();
        }

    }

    public void melhor_jogadores_campo(){

        Atleta melhor = Equipa1.get(0);
        int best_qual = 0;
        int qual = 0;

        for (Atleta atlt:Equipa1
                ) {
            if (qual == 0)
                melhor = atlt;
            else{
                if (atlt.getClass().equals(Jogadore_campo.class)){

                    if(atlt.getClass().equals(Avancado.class)){
                        qual = ((Avancado) atlt).Qualidade_jogador(3,2,1);
                    }
                    else
                        if(atlt.getClass().equals(Medio.class)){
                            qual = ((Medio) atlt).Qualidade_jogador(2,3,1);
                        }

                        else
                            if(atlt.getClass().equals(Defesa.class)){
                            qual = ((Defesa) atlt).Qualidade_jogador(1,2,3);
                        }
                }

                    if (best_qual < qual){
                        best_qual = qual;
                        melhor = atlt;
                    }
                }

        }

        System.out.println("Pontuacao: " + best_qual);
        System.out.println(melhor.toString());

    }

    //save binario
    public void esc_equipa_bin(String ficheiro) throws IOException {

        ObjectOutputStream Writebin = new ObjectOutputStream(new FileOutputStream(ficheiro));

        for (Atleta atlt: Equipa1
             ) {

            Writebin.writeObject(atlt);

        }

        Writebin.close();
        Equipa1.clear();

    }

    //leitura binario
    public void ler_equipa_bin(String ficheiro) throws IOException, ClassNotFoundException {

        ObjectInputStream Writebin = new ObjectInputStream(new FileInputStream(ficheiro));

        while (true) {
            Equipa1.add((Atleta) Writebin.readObject());

            Writebin.close();
        }

    }



    //save text
    public void equipa_text(String ficheiro) throws IOException {

        PrintWriter Write = new PrintWriter(new FileWriter(ficheiro));

        for (Atleta atlt: Equipa1
             ) {

            Write.println(atlt.getNome());
            Write.println(atlt.getIdade());
            Write.println(atlt.getAltura());
            Write.println(atlt.getSalario_mensal_bruto());
            Write.println(atlt.getTempo_jogo());

            if (atlt.getClass().equals(Avancado.class) || atlt.getClass().equals(Medio.class) || atlt.getClass().equals(Defesa.class)){
                Write.println(((Jogadore_campo)atlt).getBolas_cortadas());
                Write.println(((Jogadore_campo)atlt).getGolos_marcados());
                Write.println(((Jogadore_campo)atlt).getNum_assistencias());
            }
            else
            {
                Write.println(((Guarda_redes)atlt).getRemates_defendidos());
            }


            Write.println("\n");

        }

        Write.flush();
        Write.close();

    }

}
