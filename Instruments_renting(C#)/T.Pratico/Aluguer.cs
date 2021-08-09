using System;
using System.Collections.Generic;

namespace T.Pratico
{
    [Serializable]
    public class aluguer
    {
        public aluguer(string nome, int id, int dias)
        {
            nCliente = nome;
            IDinstrumento_alugado = id;
            datainicio = DateTime.Now;
            previsao_entrega = datainicio.AddDays(dias);
        }

        // Cliente da loja
        private string nCliente;

        // Guitarra da loja
        private int IDinstrumento_alugado;

        // Data do inicio do aluguer
        private DateTime datainicio;

        // Dias do aluguer
        private DateTime previsao_entrega; 

        // Data da devolucao da guitarra alugada
        private DateTime dataEntrega;

        // Estado do instrumento na estrega (s ou n)
        private string Danificado;


        // --------- Funcoes --------- //


        // Funcao para atribuir a data quando o cliente entrega o instrumento
        public bool dataentrega_final()
        {
            dataEntrega = DateTime.Now;
            return true;
        }

        //Funcao para verificar se há atraso na entrega do instrumento
        public bool atraso_aluguer()
        {
            if(dataEntrega > previsao_entrega)
                return true;
            else
            {
                return false;
            }
        }

        //Funcao para verificar se na entrega do instrumento, este estava danificado
        public bool danificado_aluguer(string estado)
        {
            if (estado == "s")

                return true;
            else
            {
                return false;
            }
        }

        //Funcao para verificar se na entrega do instrumento, este estava danificado
        public bool getdanificado()
        {
            if (Danificado == "s")

                return true;
            else
            {
                return false;
            }
        }

        // Funcao que retorna uma string com os dados do aluguer
        public string dados_aluguer()
        {
            string dados = "Nome do cliente: " + nCliente + "\nInstrumento alugado: " + IDinstrumento_alugado
                + "\nData do aluguer: " + datainicio + "\nData de entrega prevista: " + previsao_entrega
                + "\nData de entrega final: " + dataEntrega;
            return dados;
        }

        // Funcao que retorna uma string com os dados do aluguer
        public string dados_aluguer_hist()
        {
            string dados = "Nome do cliente: " + nCliente + "\nInstrumento alugado: " + IDinstrumento_alugado
                           + "\nData do aluguer: " + datainicio + "\nData fim do aluguer: " + dataEntrega;
            return dados;
        }

        //Funcao get ID no aluguer
        public int getIDaluguer()
        {
            return IDinstrumento_alugado;
        }

        //Funcao get nome no aluguer
        public string getnomealuguer()
        {
            return nCliente;
        }

        //Funcao set danificado
        public bool setdanificado()
        {
            Danificado = "s";
            return true;

        }

    }
}
 