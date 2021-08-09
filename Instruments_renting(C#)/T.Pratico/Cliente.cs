using System;
using System.Collections.Generic;

namespace T.Pratico
{
    [Serializable]
    public class Cliente
    {
        // Nome do cliente, este tem de ser unico
        private string nome_cliente;

        // Numero do cliente, verificar se tem 9 digitos
        private string num_Telefone;

        // Lista que guarda os alugueres do cliente ao longo do tempo
        private List<aluguer> hist_alugueres = new List<aluguer>();

        // Lista que guarda os alugueres do cliente ao longo do tempo (max 5 - variavel global )
        private List<aluguer> alugueres_actuais = new List<aluguer>();


        // --------- Funcoes --------- //

        // Funcao para criar um cliente
        public Cliente(string n,string t)
        {
            nome_cliente = n;
            num_Telefone = t;
        }

        // funcao get e set do nome do cliente
        public string nome
        {
            get {return nome_cliente; }
            set { nome_cliente = value; }
        }

        // funcao get e set do telefone do cliente
        public string telefone
        {
            get { return num_Telefone; }
            set { num_Telefone = value; }
        }

        // Retorna o numero de alugueres no momento do cliente
        public int num_alugueres_actuais_abertos()
        {
            return alugueres_actuais.Count;
        }

        // funcao retorna o numero de alugueres no historico do cliente
        public int numalugueres_historico()
        {
            return hist_alugueres.Count;
        }

        // Funcao retorna o numero de alugueres no historico que o cliente entregou com atraso
        public int getatrasos()
        {
            int atrasos=0;

            foreach (var aluguer in hist_alugueres)
            {
                if (aluguer.atraso_aluguer() == true)
                    atrasos += 1;
            }

            return atrasos;
        }

        //Funcao retorna o numero de alugueres no historico que o cliente entregou danificadas
        public int getdanificadas()
        {
            int danificadas = 0;

            foreach (var aluguer in hist_alugueres)
            {
                if (aluguer.getdanificado() == true)
                    danificadas += 1;
            }

            return danificadas;
        }

        // Funcao que recebe um aluguer e mete na lista dos alugueres em andamento do cliente
        public bool guarda_aluguer_aberto(aluguer novo_aberto)
        {
            alugueres_actuais.Add(novo_aberto);
            return true;
        }

        // Funcao retorna dados de um aluguer do historico do cliente pedido
        public string dados_aluguer_historico()
        {
            foreach (var aluguer in hist_alugueres)
            {
                string dados = aluguer.dados_aluguer();
                return dados;
            }
            return null;
        }

        // Apaga o aluguer que estamos a finalizar, da lista dos alugueres abertos do cliente
        public void apaga_aluguer_aberto(aluguer temp)
        {
            alugueres_actuais.Remove(temp);
        }

        // Apaga o aluguer que estamos a finalizar, da lista dos alugueres abertos do cliente
        public void guarda_aluguer_historico(aluguer temp)
        {
            hist_alugueres.Add(temp);
        }

    }
}