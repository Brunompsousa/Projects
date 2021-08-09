/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpraticoid;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Brums
 */
public class Webpage2acessos {
    
    // Vai buscar o source da pagina (segundo site / municipios) e guarda, este pode ser qualquer municipio
    // pois apenas servirá para tirarmos os codigos dos municipios
    public static void ProcuraSite2(){
        
        String link = "http://www.anmp.pt/anmp/pro/mun1/mun101w3.php?cod=M2200";
        HttpRequestFunctions.httpRequestISO(link, "", "Sitecod.html");

    }
    
    //Recebe o nome do municipio e guarda o site do mesmo
    public static void procPageMunicip(String Municip) throws IOException
    {
        //String para metermos o codigo que vamos usar para ir buscar a pagina do municipio
        String codMunicipio = "";
        
        //String ER para a procura do codigo do municipio
        String erMunicipio = "<OPTION value\\=\"mun101w3\\.php\\?cod\\=(M[0-9]{4})\">" + Municip + "\\b"; 

        //Criacao do pattern
        Pattern p = Pattern.compile(erMunicipio); 
        
        //Scanner para ler o ficheiro do site que se foi buscar
        Scanner bR = new Scanner(new FileReader("Sitecod.html"));
        
        //Enquanto houver proxima linha no ficheiro
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            Matcher m = p.matcher(line);
        
            //Procura se ha alguma expressao que coincida com a ER
            while(m.find()){
                //Quando encontra, guarda os dados na string
                codMunicipio = m.group(1);
            }
        }
        
        //Fecha o scanner de leitura do ficheiro
        bR.close();

        // Vai buscar o source da pagina (segundo site / municipios) e guarda, este ja com o codigo
        // de um municipio do qual guardamos nas noticias
        String link = "http://www.anmp.pt/anmp/pro/mun1/mun101w3.php?cod=" + codMunicipio;
        HttpRequestFunctions.httpRequestISO(link, "", "SiteMunicipio.html");
    }
    
    //Funcao para ir buscar o nome do presidente do municipio
    public static String procPresidenteCamara() throws IOException
    {
        //Er feitas para procurar o presidente da camara do municipio
        String ernome="([a-z A-Z À-ú \\s]+), Presidente da Câmara";
        
        //Criacao do pattern
        Pattern p = Pattern.compile(ernome); 
        
        //String para o nome do presidente da camara
        String nomePresidente = "";
        
        //Scanner para ler o ficheiro do site que se foi buscar
        Scanner bR = new Scanner(new FileReader("SiteMunicipio.html"));
        
        //Enquanto houver proxima linha no ficheiro
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();

            Matcher m = p.matcher(line);
        
            //Procura se ha alguma expressao que coincida com a ER
            while(m.find()){
                
                //Quando encontra, guarda os dados na string
                nomePresidente = m.group(1);
            }
        }
        
        //Fecha o scanner de leitura do ficheiro
        bR.close();
        
        //Funcao que vai transformar o nome do presidente, que vem todo em maiusculas, para meter apenas a primeira letra de cada nome maiuscula
        nomePresidente = upperCaseStrings(nomePresidente);
        
        //Retorna uma string com os dados encontrados
        return nomePresidente;
    }
    
    //Funcao para ir buscar o mail do municipio
    public static String procMail() throws IOException
    {
        //Er feitas para procurar o mail do municipio
        String ernome="mailto\\:([a-z A-Z Á-ú]+@[a-z A-Z Á-ú\\-]+\\.[a-z A-Z]+)";
        
        //Criacao do pattern
        Pattern p = Pattern.compile(ernome); 
        
        //String para o mail do municipio
        String mail = "";
        
        //Scanner para ler o ficheiro do site que se foi buscar
        Scanner bR = new Scanner(new FileReader("SiteMunicipio.html"));
        
        //Enquanto houver proxima linha no ficheiro
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            Matcher m = p.matcher(line);
        
            //Procura se ha alguma expressao que coincida com a ER
            while(m.find()){
                
                //Quando encontra, guarda os dados na string
                mail = m.group(1);
            }
        }
        
        //Fecha o scanner de leitura do ficheiro
        bR.close();
        
        //Retorna uma string com os dados encontrados
        return mail;
        
    }
    
    //Funcao para ir buscar o Site do municipio
    public static String procSiteInst() throws IOException
    {
        //Er feitas para procurar o site do municipio
        String ernome="(http://www.[a-z A-Z\\-]+\\.[a-z A-Z]+)";
        
        //Criacao do pattern
        Pattern p = Pattern.compile(ernome); 
        
        //String para o site do municipio
        String siteInst = "";
        
        //Scanner para ler o ficheiro do site que se foi buscar
        Scanner bR = new Scanner(new FileReader("SiteMunicipio.html"));
        
        //Enquanto houver proxima linha no ficheiro
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            Matcher m = p.matcher(line);
        
            //Procura se ha alguma expressao que coincida com a ER
            while(m.find()){
                
                //Quando encontra, guarda os dados na string
                siteInst = m.group(1);
            }
        }
        
        //Fecha o scanner de leitura do ficheiro
        bR.close();
        
        //Retorna uma string com os dados encontrados
        return siteInst;
    }
   
    //Funcao para ir buscar o nome do presidente
    public static String procTelefone() throws IOException
    {
        //Er feitas para procurar presidente do municipio
        String ernome="Telefone\\: ([0-9\\s]{11})";
        
        //Criacao do pattern
        Pattern p = Pattern.compile(ernome); 

        //String para o telefone do municipio
        String procTele = "";
        
        //Scanner para ler o ficheiro do site que se foi buscar
        Scanner bR = new Scanner(new FileReader("SiteMunicipio.html"));
        
        //Enquanto houver proxima linha no ficheiro
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            Matcher m = p.matcher(line);
        
            //Procura se ha alguma expressao que coincida com a ER
            while(m.find()){
                
                //Quando encontra, guarda os dados na string
                procTele = m.group(1);
            }
        }
        
        //Fecha o scanner de leitura do ficheiro
        bR.close();
        
        //Retorna uma string com os dados encontrados
        return procTele;
    }
    
    //Funcao para ir buscar o numero de freguesias do municipio
    public static String procFreg() throws IOException
    {
        //Er feitas para procurar o numero de freguesias do municipio
        String ernome="([0-9]+) freguesia[s]?";
        
        //Criacao do pattern
        Pattern p = Pattern.compile(ernome); 
        
        //String para o numero de freguesias do municipio
        String freg = "";
        
        //Scanner para ler o ficheiro do site que se foi buscar
        Scanner bR = new Scanner(new FileReader("SiteMunicipio.html"));
        
        //Enquanto houver proxima linha no ficheiro
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            Matcher m = p.matcher(line);
        
            //Procura se ha alguma expressao que coincida com a ER
            while(m.find()){
                
                //Quando encontra, guarda os dados na string
                freg = m.group();
            }
        }
        
        //Fecha o scanner de leitura do ficheiro
        bR.close();
        
        //Retorna uma string com os dados encontrados
        return freg;
    }
   
    //Funcao para ir buscar a area em km2 do municipio
    public static String prockm2() throws IOException
    {
        //Er feitas para procurar a area em km2 do municipio
        String ernome="[0-9\\,]+ km2";
        
        //Criacao do pattern
        Pattern p = Pattern.compile(ernome); 
           
        //String para a area em km2 do municipio
        String km = "";
        
        //Scanner para ler o ficheiro do site que se foi buscar
        Scanner bR = new Scanner(new FileReader("SiteMunicipio.html"));
        
        //Enquanto houver proxima linha no ficheiro
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            Matcher m = p.matcher(line);
        
            //Procura se ha alguma expressao que coincida com a ER
            while(m.find()){
                
                //Quando encontra, guarda os dados na string
                km = m.group();
            }
        }
        
        //Fecha o scanner de leitura do ficheiro
        bR.close();
        
        //Retorna uma string com os dados encontrados
        return km;
    }
    
    //Funcao para ir buscar o numero de habitantes do municipio
    public static String procHabitante() throws IOException
    {
        //Er feitas para procurar o numero de habitantes do municipio
        String ernome="[0-9]*\\s[0-9]+ habitante[s]?";
        
        //Criacao do pattern
        Pattern p = Pattern.compile(ernome);  
        
        //String para o numero de habitantes do municipio
        String habitantes = "";
        
        //Scanner para ler o ficheiro do site que se foi buscar
        Scanner bR = new Scanner(new FileReader("SiteMunicipio.html"));
        
        //Enquanto houver proxima linha no ficheiro
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            Matcher m = p.matcher(line);
        
            //Procura se ha alguma expressao que coincida com a ER
            while(m.find()){
                
                //Quando encontra, guarda os dados na string
                habitantes = m.group();
            }
        }
        
        //Fecha o scanner de leitura do ficheiro
        bR.close();
        
        //Retorna uma string com os dados encontrados
        return habitantes;
    }
    
    //Funcao para ir buscar o link da imagem do brasão do municipio
    public static String procimagem() throws IOException
    {
        //Er feitas para procurar o link da imagem do brasão do municipio
        String ernome="(SRC=\"../../../image/munap/([a-z A-Z Á-ú 0-9]+b.jpg))";
        
        //Criacao do pattern
        Pattern p = Pattern.compile(ernome); 
        
        //String para guardar o link da imagem do brasão do municipio
        String imagem = "";
        
        //Scanner para ler o ficheiro do site que se foi buscar
        Scanner bR = new Scanner(new FileReader("SiteMunicipio.html"));
        
        //Enquanto houver proxima linha no ficheiro
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            Matcher m = p.matcher(line);
        
            //Procura se ha alguma expressao que coincida com a ER
            while(m.find()){
                
                //Quando encontra, guarda os dados na string, neste caso, remove a parte do Source e subtitui por o link direto
                // para que assim tenhamos acesso direto a imagem online
                imagem = m.group(1).replace("SRC=\"../../../image/munap/", "http://www.anmp.pt/image/munap/");
            }
        }
        
        //Fecha o scanner de leitura do ficheiro
        bR.close();
        
        //Retorna uma string com os dados encontrados
        return imagem;
    }
    
    //Funcao que tira os municipios repetidos da nossa listagem para que nao haja repeticao de informacao
    //Recebe um array com todos os autores pa pagina das noticias 
    public static ArrayList<String> retiraDuplicados(ArrayList<noticia> grupoNoticias){
        
        //Cria uma array de strings no qual se vai guardar os nomes de todos os municipios encontrados na pagina das noticas sem que se repitam 
        ArrayList<String> nomesMunic = new ArrayList<>();
        
        //For para corrermos todos os elementos do array que recebemos e deixar apenas o nome do municipio
        for(int i = 0 ; i < grupoNoticias.size() ; i++){
        
        //Verifica se é ou nao um municipio, caso este nao seja, nao presisamos de o guardar pois nao existe pagina do mesmo para ir buscar
        if(grupoNoticias.get(i).getAutor().contains("Município") == true)
            {
                //Copia o elemento em que estamos do array para uma String e retira o texto
                String nomeMunicipio = grupoNoticias.get(i).getAutor();
                nomeMunicipio = nomeMunicipio.replace("Município de" , "").trim();
                
                //Verificamos se o nome ainda tem a palavra 'Município', no caso de ter, vamos tentar retirar de outras maneiras
                //pois existem 2 variantes que presisamos de ver, caso a primeira falhe
                if(nomeMunicipio.contains("Município") == true)
                    nomeMunicipio = nomeMunicipio.replace("Município do" , "").trim();
                
                if(nomeMunicipio.contains("Município") == true)
                    nomeMunicipio = nomeMunicipio.replace("Município da" , "").trim();
                
                //No caso especial de Ourém, a mesmo tinha outro nome, o que presisamos de criar um caso especial para a mesma
                if(nomeMunicipio.contains("Ourém") == true)
                    nomeMunicipio = nomeMunicipio.replace("e Associação Desportiva" , "").trim();
                
                //Depois de termos o nome apenas do municipio, adicionamos o mesmo ao array ao qual vamos virificar se existem repeticoes ou nao dos nomes
                nomesMunic.add(nomeMunicipio);
            }
        }
        
        //For para corrermos os elementos do array comecando no primeiro elemento
        for (int i = 0 ; i < nomesMunic.size() ; i++) {
            
            //For para corrermos os elementos do array comecando no elemnto seguinte ao que temos no primeiro for
            for (int j = i+1; j < nomesMunic.size(); j++) {
                
                //caso o elemento do segundo for seja igual ao do primeiro, removemos o elemento em questao
                //Ao remover o elemento, todos os elementos que estavam após esse andaram uma cada para tras no array
                //o que nos obriga a nao andar uma casa para a frente ou saltariamos elementos do array
                if(nomesMunic.get(j).contains(nomesMunic.get(i))){
                    nomesMunic.remove(j);
                    j--;
                }
            }   
        }

        //Retorna um array com o nome dos municipios sem que haja duplicados
        return nomesMunic;
    } 
    
    // Funcao para meter os nomes apenas com a primeira letra de cada nome/palavra maiuscula
    private static String upperCaseStrings(String value) {

        //Mete a string toda como minusculas
        value = value.toLowerCase();
        
        //cria um array de char's e converte a string para la
        char[] array = value.toCharArray();
        
        // Mete a primeira letra maiuscula
        array[0] = Character.toUpperCase(array[0]);

        // Mete maiuscula a letra que venha depois de um espaço
        for (int i = 1; i < array.length; i++) {
            if (Character.isWhitespace(array[i - 1])) {
                array[i] = Character.toUpperCase(array[i]);
            }
        }

        // Retorna uma string com o array de char's dentro
        return new String(array);
    }
}
