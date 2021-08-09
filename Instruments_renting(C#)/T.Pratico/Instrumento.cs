using System;
using System.Collections.Generic;

namespace T.Pratico
{
    [Serializable]
    // Class abstrata para prevenir a criacao de objectos do tipo instrumento, pois um instrumentos tem de ser de algum tipo
    public abstract class Instrumento
    {
        // Situacao do instrumento para saber se esta disponivel ou nao e porque
        private enum Situacao
        {
            disponivel,
            alugado,
            reparacao,
            danificado
        }

        // Valor para o ID, para que cada guitarra tenha um valor unico
        private static int id = 1;

        // id da guitarra
        private int inst_id;

        // Caracteristicas do objecto
        // Descricao da instrumento
        private string descricao;

        // Preço a pagar por cada dia de aluguer da guitarra
        private decimal preco_aluguerDia;

        // Preço da guitarra na compra
        private decimal Valorinstrumento;

        // Variavel de situacao do instrumento
        private Situacao situacao;

        // Lista de alugueres do instrumentos
        List<aluguer> hist_aluguers = new List<aluguer>();

        // Funcao para criar um instrumento
        public Instrumento(string desc, decimal preco_dia, decimal valorinst)
        {
            inst_id = id;

            id++;

            descricao = desc;

            preco_aluguerDia = preco_dia;

            Valorinstrumento = valorinst;

            situacao = Situacao.disponivel;

        }

        // Get e Set para o valor diario do instrumento
        public decimal valor_diario
        {
            get { return preco_aluguerDia; }
            set { preco_aluguerDia = value; }
        }

        //Get do Id do instrumento
        public int getid
        {
            get { return inst_id; }
        }

        // Get e Set para o valor do instrumento
        public decimal valor_instrumento
        {
            get { return Valorinstrumento; }
            set { Valorinstrumento = value; }
        }

        //Funcao para o alterar o estado do instrumento
        public bool mudar_estado(int estado)
        {
            switch (estado)
            {
                case 1:
                    situacao = Situacao.disponivel;
                    return true;

                case 2:
                    situacao = Situacao.alugado;
                    return true;

                case 3:
                    situacao = Situacao.reparacao;
                    return true;

                case 4:
                    situacao = Situacao.danificado;
                    return true;

                default:
                    return false;

            }
        }

        //Get do estado do instrumento
        public string get_estado()
        {
            string estado = situacao.ToString();
            return estado;
        }

        // Funcao para imprimir a informacao do instrumento
        public override string ToString()
        {
            string resumo = "Id do Instrumento: "+ inst_id + "\nDescrição: " + descricao + "\nValor diario de aluguer: " + preco_aluguerDia +
                            "Euros\nValor do instrumento: " + Valorinstrumento + "Euros\nEstado: " + situacao;
            return resumo;
        }

    }

}