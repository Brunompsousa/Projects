namespace T.Pratico
{
    public class Limitadores
    {
        // --------- Numero maximo de alugueres por cliente --------- //
        public static int Max_alugueres = 5;

        // --------- Numero maximo de dias que o cliente pode alugar um instrumento --------- //
        public static int Max_tempo = 7;

        // --------- Numero de atrasos que o cliente e proibido de alugar mais instrumentos  --------- //
        public static int Max_atrasos = 10;

        // --------- Numero de instrumentos danificados que o cliente é proibido de alugar mais instrumentos  --------- //
        public static int Max_danificadas = 3;

        // --------- Numero de melhores cliente que e dado por a funcao que lista os melhores clientes  --------- //
        public static int Max_melhores_clientes = 3;

        // --------- Preço minimo que requer que o cliente tenho x numero de alugueres realizados  --------- //
        public static int Preco_requeralugueres = 200;

        // --------- x alugueres para que o cliente possa alugar instrumentos com valor superior a Y  --------- //
        public static int Alugueres_requeridos = 3;

    }


}