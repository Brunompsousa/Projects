using System;
using System.Collections.Generic;
using System.Globalization;

namespace T.Pratico
{

    public class Loja
    {
        // lista de clientes
        private List<Cliente> clientes = new List<Cliente>();

        // lista de instrumentos
        private List<Instrumento> instrumentos = new List<Instrumento>();

        // lista de alugueres
        // A lista de alugueres guarda todos os alugueres que existem no momento
        private List<aluguer> alugueres = new List<aluguer>();

        // A lista de alugueres guarda todos os alugueres que ja foram feitos e fechados
        private List<aluguer> hist_alugueresloja = new List<aluguer>();


        // --------- Funcoes --------- //
        // --------- Clientes --------- //

        //criar cliente
        public bool Criar_cliente(string n, string t)
        {
            Cliente novo = new Cliente(n, t);
            clientes.Add(novo);

            if (clientes.Contains(novo))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Verifica se o nome do cliente ja existe
        public bool Verifica_existe_nome(string nome)
        {
            if (clientes.Count != 0)
            {
                foreach (var c in clientes)
                {
                    if (c.nome == nome)
                        return true;
                }
                return false;
            }
            return false;
        }

        //Procura cliente com nome recebido
        public bool mudar_nome_cliente(string nome,string novonome)
        {
            foreach (var cliente in clientes)
            {
                if (cliente.nome == nome)
                {
                    cliente.nome = novonome;
                    return true;
                }
            }
            return false;
        }

        //Procurar cliente e muda telefone
        public bool mudar_telefone_cliente(string nome, string novotele)
        {
            foreach (var cliente in clientes)
            {
                if (cliente.nome == nome)
                {
                    cliente.telefone = novotele;
                    return true;
                }
            }
            return false;
        }
        
        // Retorna numa string os dados de um aluguer no historico de um cliente, dado por nome
        public string dados_aluguere_historico_cliente(string nomecliente)
        {
            foreach (var cliente in clientes)
            {
                if (cliente.nome == nomecliente)
                {
                    string dados = cliente.dados_aluguer_historico();
                    return dados;
                }
            }
            return null;
        }

        // Retorna uma string com os dados de um cliente da lista dado por nome
        public string informcacoes_cliente_pedido(int i)
        {

                string dados = "Nome cliente: " + clientes[i].nome + "\nTelefone: " + clientes[i].telefone +
                               "\nGuitarras alugadas de momento: "
                               + clientes[i].num_alugueres_actuais_abertos();
                return dados;
        }

        //Funcao que da os atrasos do cliente nos alugueres que ja entregou
        public int cliente_atrasos(string nomecliente)
        {
            foreach (var cliente in clientes)
            {
                if (cliente.nome == nomecliente)
                {
                    return cliente.getatrasos();
                }
            }
            return 0;
        }

        // Retorna quando alugueres, ja finalizados, fez o cliente 
        public int num_alugueres_concluidos_cliente(string nomecliente)
        {
            foreach (var cliente in clientes)
            {
                if (cliente.nome == nomecliente)
                {
                    return cliente.numalugueres_historico();
                }
            }
            return 0;

        }

        // Retorna quando alugueres, em aberto, tem o cliente 
        public int num_alugueres_abertos_cliente(string nomecliente)
        {
            foreach (var cliente in clientes)
            {
                if (cliente.nome == nomecliente)
                {
                    return cliente.num_alugueres_actuais_abertos();
                }
            }
            return 0;

        }

        // Quantas vezes os instrumentos vieram danificados, de alugueres que ja finalizaram de um dado cliente
        public int cliente_danificadas(string nomecliente)
        {
            foreach (var cliente in clientes)
            {
                if (cliente.nome == nomecliente)
                {
                    return cliente.getdanificadas();
                }
            }
            return 0;

        }

        // --------- Alugueres --------- //

        // Guardar o aluguer na lista de alugueres da loja e enviar ao cliente para este guardar na sua lista de alugueres em aberto
        public bool guardar_aluguer(string nome, int id, int diasaluguer)
        {
            aluguer novo = new aluguer(nome, id, diasaluguer);
            alugueres.Add(novo);
            foreach (var cliente in clientes)
            {
                if (cliente.nome == nome)
                {
                    cliente.guarda_aluguer_aberto(novo);
                    foreach (var instrumento in instrumentos)
                    {
                        if(instrumento.getid == id)
                        instrumento.mudar_estado(2);
                    }
                }
            }
            return true;
        }

        // Funcao retorna numero de alugueres em aberto da loja
        public int num_alugueres_abertosloja()
        {
            return alugueres.Count;
        }

        // Funcao retorna numero de alugueres concluidos da loja
        public int num_alugueres_historicoloja()
        {
            return hist_alugueresloja.Count;
        }

        // Retorna dados do aluguer se o ID do instrumento for o pedido
        public string dados_historicoaluguer_Id(int id)
        {
            foreach (var aluguer in hist_alugueresloja)
            {
                if (aluguer.getIDaluguer() == id)
                {
                    return aluguer.dados_aluguer();
                }
            }
            return null;
        }

        //Retorna dados dos alugueres todos em historico
        public string dados_aluguer_aberto()
        {
            foreach (var aluguer in alugueres)
            {
                return aluguer.dados_aluguer();

            }
            return null;
        }

        //Retorna dados dos alugueres todos em historico
        public string dados_historicoaluguer_todos()
        {
            string dados;

            foreach (var aluguer in hist_alugueresloja)
            {
                dados = aluguer.dados_aluguer_hist();

                if (aluguer.atraso_aluguer() == true)
                {
                    dados += "\nAtraso na entrega: Sim";
                    if (aluguer.getdanificado() == true)
                    {
                        dados += "\nDanificado na entrega: Sim";
                        return dados;
                    }
                    else
                    {
                        dados += "\nDanificado na entrega: Não";
                        return dados;
                    }
                }
                else
                {
                    dados += "\nAtraso na entrega: Não";
                    if (aluguer.getdanificado() == true)
                    {
                        dados += "\nDanificado na entrega: Sim";
                        return dados;
                    }
                    else
                    {
                        dados += "\nDanificado na entrega: Não";
                        return dados;
                    }
                }
            }
            return null;
        }

        // lista de melhores clientes
        public string melhores_clientes()
        {
            List<Cliente> melhorestemp = new List<Cliente>();
            int cont = 0;

            for (int i = 0; i < clientes.Count-1; i++)
            {
                for (int j = 1; j < clientes.Count; j++)
                {
                    if (clientes[i].numalugueres_historico() < clientes[j].numalugueres_historico())
                    {
                        cont++;
                    }
                }
                if (melhorestemp.Count < Limitadores.Max_melhores_clientes)
                {
                    if (cont < Limitadores.Max_melhores_clientes)
                    {
                        melhorestemp.Add(clientes[i]);
                    }               
                }
                else
                {
                    goto enviardados;
                }
            }
            enviardados:
            foreach (var melhore in melhorestemp)
            {
                string dados = "\nNome: " + melhore.nome + "\nTelefone: " + melhore.telefone;
                return dados;
            }
            return "";
        }

        // Encerrar aluguer
        public bool encerra_aluguer(string n, int id, string d)
        {
            List<aluguer> temp = new List<aluguer>();

            for (int i = 0; i < alugueres.Count; i++)
            {
                if (alugueres[i].getnomealuguer() == n && alugueres[i].getIDaluguer() == id)
                {
                    temp.Add(alugueres[i]);
                    aluguer temporario = alugueres[i];

                    foreach (var cliente in clientes)
                    {
                        if (cliente.nome == n)
                        {
                            cliente.apaga_aluguer_aberto(temporario);
                        }
                    }

                    temporario.dataentrega_final();
                    temporario.atraso_aluguer();

                    if (temporario.danificado_aluguer(d) == true)
                    {
                        foreach (var instrumento in instrumentos)
                        {
                            if (instrumento.getid == id)
                            {
                                instrumento.mudar_estado(4); //mudar para danificada
                                temporario.setdanificado();
                            }
                        }

                    }
                    else
                    {
                        foreach (var instrumento in instrumentos)
                        {
                            if (instrumento.getid == id)
                            {
                                instrumento.mudar_estado(1); //mudar para disponivel
                            }
                        }

                    }

                    hist_alugueresloja.Add(temporario);

                    foreach (var cliente in clientes)
                    {
                        if (cliente.nome == n)
                        {
                            cliente.guarda_aluguer_historico(temporario);
                        }
                    }
                    goto remover;
                }
            }
            remover:
            foreach (var aluguer in temp)
            {
                alugueres.Remove(aluguer);
            }

            return true;
        }

        // -------------------------------- //
        // --------- Instrumentos --------- //

        // Numero de clientes que existem
        public int numclientes()
        {
            return clientes.Count;
        }

        // Criar instrumento guitarra
        public bool Cria_guitarra(string descrisao, decimal preco_dia, decimal valor)
        {
            Instrumento novo = new Guitarra(descrisao,preco_dia,valor);
            instrumentos.Add(novo);

            if (instrumentos.Contains(novo))
                return true;
            else
                return false;
        }

        // Criar instrumento acordeao
        public bool Cria_acordeao(string descrisao, decimal preco_dia, decimal valor)
        {
            Instrumento novo = new Acordeão(descrisao,preco_dia,valor);
            return true;
        }

        //Estado de um instrumento por ID
        public string estadoinstrumento(int id)
        {
            foreach (var inst in instrumentos)
            {

                if (inst.getid == id)
                {
                    return inst.get_estado();
                }

            }
            return null;
        }

        //Preço diario de um instrumento por ID
        public bool mudavaloraluguerdiario(int id,decimal valor)
        {
            foreach (var inst in instrumentos)
            {

                if (inst.getid == id)
                {
                    inst.valor_diario = valor;
                    return true;
                }

            }
            return false;
        }

        //Valor de um instrumento por ID
        public bool mudavalor_instrumento(int id, decimal valor)
        {
            foreach (var inst in instrumentos)
            {

                if (inst.getid == id)
                {
                    inst.valor_instrumento = valor;
                    return true;
                }

            }
            return false;
        }

        //Ver se um instrumento existe por ID
        public bool existeid(int id)
        {
            foreach (var inst in instrumentos)
            {
                if (inst.getid == id)
                    return true;
            }
            return false;
        }

        // Numero de instrumento que existem na lista
        public int numinstrumentos()
        {
            return instrumentos.Count;
        }

        // Informacoes de um instrumento por ID
        public bool enviar_instrumento_reparacao(int id)
        {
            foreach (var inst in instrumentos)
            {
                if (inst.getid == id)
                {
                    if (inst.get_estado() == "danificado")
                    {
                        inst.mudar_estado(3);
                        return true;
                    }
                }
                return false;

            }
            return false;

        }

        // Informacoes de um instrumento por ID
        public bool Voltar_instrumento_reparacao(int id)
        {
            foreach (var inst in instrumentos)
            {
                if (inst.getid == id)
                {
                    if (inst.get_estado() == "reparacao")
                    {
                        inst.mudar_estado(1);
                        return true;
                    }
                }
                return false;

            }
            return false;

        }

        // Informacoes de um instrumento por ID
        public string dadosinstrumentos(int id)
        {
            foreach (var inst in instrumentos)
            {
                if (inst.getid == id)
                {
                    return inst.ToString();
                }

            }

            return null;
        }

        // Funcao que retorna o valor de um instrumento
        public decimal valor_do_insturmento(int id)
        {
            foreach (var inst in instrumentos)
            {

                if (inst.getid == id)
                {
                   return inst.valor_instrumento;
                    
                }

            }
            return 0;
        }

        // Retorna id do instrumento no aluguer de uma dado utilizador
        public bool id_aluguer_utilizador_dado(string nomecliente, int id)
        {

            foreach (var aluguer in alugueres)
            {
                if (aluguer.getnomealuguer() == nomecliente)
                {
                    if (aluguer.getIDaluguer() == id)
                    {
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

    }
}