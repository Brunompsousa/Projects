/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpraticoid;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Brums
 */
public class XMLtoArrays {
    
    //Funcao que passa a informacao do xml, da pesquisa por autor, para um array 
    public static ArrayList<noticia> XmlPesqNoticiasAutorToArray() throws FileNotFoundException{
        
        //Array que irá ter os dados do ficheiro do resultado da pesquisa
        ArrayList<noticia> Noticias = new ArrayList<>();
        
        //Scanner para lermos o ficheiro do resultado da pesquisa
        Scanner bR = new Scanner(new FileReader("pesqAutor.xml"));
        
        //Enquando o ficheiro tiver linhas para ler
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            //Se a linha tiver a String vamos ler a proxima linha
            if(line.contains("<Noticia>")){
                line = bR.nextLine();
                
                //Criamos um array de strings no qual vamos guardar os dados da noticia
                String[] dados = new String[8];
                
                //For para guardar todos os dados no array
                for(int i = 0; i < 7 ; i++){                    
                    dados[i] = line;
                    line = bR.nextLine();   
                }
                
                //Strings para guardar os dados depois de retirarmos o texto que nao queremos
                String a, b, c, d, e, f;
            
            //Guardar os dados com os textos que nao queremos, ficando assim com a informacao que queremos para mostar na interface
            a = dados[1].replace("<Tipo>", "").replace("</Tipo>", "");
            b = dados[2].replace("<NúmeroPub>", "").replace("</NúmeroPub>", "");
            c = dados[3].replace("<Data>", "").replace("</Data>", "");
            d = dados[4].replace("<Autor>", "").replace("</Autor>", "");
            e = dados[5].replace("<Descrição>", "").replace("</Descrição>", "");
            f = dados[6].replace("<Link>", "").replace("</Link>", "");
            
            //Criacao do objeto noticia com os dados arranjados e adicionamos a mesma a um array 
            Noticias.add(new noticia(a.trim(), b.trim(), c.trim(), d.trim(), e.trim(), f.trim()));
            
            }          
        }      
       //Return do array para que este seja usado para meter os dados nos elementos da interface
       return Noticias; 
    }
 
    //Funcao que passa a informacao do xml, da pesquisa por Tipo de noticia, para um array 
    public static ArrayList<noticia> XmlPesqNoticiasTipoToArray() throws FileNotFoundException{
        
        //Array que irá ter os dados do ficheiro do resultado da pesquisa
        ArrayList<noticia> Noticias = new ArrayList<>();
        
        //Scanner para lermos o ficheiro do resultado da pesquisa
        Scanner bR = new Scanner(new FileReader("pesqTipo.xml"));
        
        //Enquando o ficheiro tiver linhas para ler
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            //Se a linha tiver a String vamos ler a proxima linha
            if(line.contains("<Noticia>")){
                line = bR.nextLine();
                
                //Criamos um array de strings no qual vamos guardar os dados da noticia
                String[] dados = new String[8];
                //For para guardar todos os dados no array
                for(int i = 0; i < 7 ; i++){  
                    dados[i] = line;
                    line = bR.nextLine();                    
                }
                
                //Strings para guardar os dados depois de retirarmos o texto que nao queremos
                String a, b, c, d, e, f;
            
            //Guardar os dados com os textos que nao queremos, ficando assim com a informacao que queremos para mostar na interface
            a = dados[1].replace("<Tipo>", "").replace("</Tipo>", "");
            b = dados[2].replace("<NúmeroPub>", "").replace("</NúmeroPub>", "");
            c = dados[3].replace("<Data>", "").replace("</Data>", "");
            d = dados[4].replace("<Autor>", "").replace("</Autor>", "");
            e = dados[5].replace("<Descrição>", "").replace("</Descrição>", "");
            f = dados[6].replace("<Link>", "").replace("</Link>", "");
            
            //Criacao do objeto noticia com os dados arranjados e adicionamos a mesma a um array  
            Noticias.add(new noticia(a.trim(), b.trim(), c.trim(), d.trim(), e.trim(), f.trim()));
            
            }          
        }
       //Return do array para que este seja usado para meter os dados nos elementos da interface
       return Noticias; 
    }

    //Funcao que passa a informacao do xml, da pesquisa por nome do municipio, para um array 
    public static ArrayList<municipio> XmlPesqMunicipioToArray() throws FileNotFoundException{
        
        //Array que irá ter os dados do ficheiro do resultado da pesquisa
        ArrayList<municipio> Municipios = new ArrayList<>();
        
        //Scanner para lermos o ficheiro do resultado da pesquisa
        Scanner bR = new Scanner(new FileReader("pesqcamara.xml"));
        
        //Enquando o ficheiro tiver linhas para ler
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            //Se a linha tiver a String vamos ler a proxima linha
            if(line.contains("<Municipio>")){
                line = bR.nextLine();
                
                //Se a linha tiver a String vamos ler a proxima linha, pois o numero neste caso nao nos é util
                if(line.contains("<Número>"))
                    line = bR.nextLine();
                    
                //Criamos um array de strings no qual vamos guardar os dados do municipio
                String[] dados = new String[9];
                
                //For para guardar todos os dados no array
                for(int i = 0; i < 9 ; i++){
                    dados[i] = line;
                    line = bR.nextLine();   
                }
                
                //Strings para guardar os dados depois de retirarmos o texto que nao queremos
                String a, b, c, d, e, f, g, h, i;
            
            //Guardar os dados com os textos que nao queremos, ficando assim com a informacao que queremos para mostar na interface
            a = dados[0].replace("<Nome>", "").replace("</Nome>", "");
            b = dados[1].replace("<Presidente>", "").replace("</Presidente>", "");
            c = dados[2].replace("<Mail>", "").replace("</Mail>", "");
            d = dados[3].replace("<site>", "").replace("</site>", "");
            e = dados[4].replace("<Telefone>", "").replace("</Telefone>", "");
            f = dados[5].replace("<Freguesias>", "").replace("freguesias</Freguesias>", "");
            g = dados[6].replace("<Area>", "").replace("</Area>", "");
            h = dados[7].replace("<Habitantes>", "").replace("habitantes</Habitantes>", "");
            i = dados[8].replace("<imagem>", "").replace("</imagem>", "");
            
            //Criacao do objeto municipio com os dados arranjados e adicionamos a mesma a um array 
            Municipios.add(new municipio(a.trim(), b.trim(), c.trim(), d.trim(), e.trim(), f.trim(), g.trim(), h.trim(), i.trim()));
            }          
        }
        //Return do array para que este seja usado para meter os dados nos elementos da interface
        return Municipios; 
    }
    
    //Funcao que passa a informacao xml para um array 
    public static ArrayList<municipio> XmlPesqPresidenteMunic_ToArray() throws FileNotFoundException{
        
        //Array que irá ter os dados do ficheiro do resultado da pesquisa
        ArrayList<municipio> Municipios = new ArrayList<>();
        
        //Scanner para lermos o ficheiro do resultado da pesquisa
        Scanner bR = new Scanner(new FileReader("pesqpresidente.xml"));
        
        //Enquando o ficheiro tiver linhas para ler
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            //Se a linha tiver a String vamos ler a proxima linha
            if(line.contains("<Municipio>")){
                line = bR.nextLine();
                
                //Se a linha tiver a String vamos ler a proxima linha, pois o numero neste caso nao nos é util
                if(line.contains("<Número>"))
                    line = bR.nextLine();
                    
                //Criamos um array de strings no qual vamos guardar os dados do municipio
                String[] dados = new String[9];
                
                //For para guardar todos os dados no array
                for(int i = 0; i < 9 ; i++){                    
                    dados[i] = line;
                    line = bR.nextLine();                   
                }
               
            //Strings para guardar os dados depois de retirarmos o texto que nao queremos   
            String a, b, c, d, e, f, g, h, i;
            
            //Guardar os dados com os textos que nao queremos, ficando assim com a informacao que queremos para mostar na interface
            a = dados[0].replace("<Nome>", "").replace("</Nome>", "");
            b = dados[1].replace("<Presidente>", "").replace("</Presidente>", "");
            c = dados[2].replace("<Mail>", "").replace("</Mail>", "");
            d = dados[3].replace("<site>", "").replace("</site>", "");
            e = dados[4].replace("<Telefone>", "").replace("</Telefone>", "");
            f = dados[5].replace("<Freguesias>", "").replace("freguesias</Freguesias>", "");
            g = dados[6].replace("<Area>", "").replace("</Area>", "");
            h = dados[7].replace("<Habitantes>", "").replace("habitantes</Habitantes>", "");
            i = dados[8].replace("<imagem>", "").replace("</imagem>", "");
            
            //Criacao do objeto municipio com os dados arranjados e adicionamos a mesma a um array 
            Municipios.add(new municipio(a.trim(), b.trim(), c.trim(), d.trim(), e.trim(), f.trim(), g.trim(), h.trim(), i.trim()));
            
            }          
        }   
        //Return do array para que este seja usado para meter os dados nos elementos da interface
        return Municipios; 
    }
    
    //Funcao que passa a informacao xml para um array 
    public static String[] XmlPesqtop5() throws FileNotFoundException{
        
        //Array que irá ter os dados do ficheiro do resultado da pesquisa
        String[] top5 = new String[10];
        
        //Int para a contagem das linhas que vamos ler de cada vez
        int stop = 2;
        
        //Int para sabermos em que elemento do array estamos a escrever
        int copy = 0;
        
        //Scanner para lermos o ficheiro do resultado da pesquisa
        Scanner bR = new Scanner(new FileReader("pesqListaMunic.xml"));
        
        //Enquando o ficheiro tiver linhas para ler
        while (bR.hasNextLine()) {                      
            
        //Le a linha do ficheiro para a string line
        String line = bR.nextLine();
            
        //Se a linha tiver a String vamos ler a proxima linha
        if(line.contains("<Munic>")){
            
            //Guardamos a proxima linha
            line = bR.nextLine();
                
                //For para guardamos os dados no array
                for(int i=0;copy < stop; copy++){
                    
                    //Se a linha tiver a String vamos tirar o texto desnecessario e guardar os dados no array
                    if(line.contains("nome"))
                        top5[copy] = line.replace("<nome>", "").replace("</nome>", "");
                    
                    //Se a linha tiver a String vamos tirar o texto desnecessario e guardar os dados no array
                    if(line.contains("Num"))
                        top5[copy] = line.replace("<Num>", "").replace("</Num>", "");
                    
                    line = bR.nextLine();
                }
                //Aumentamos o stop em 2 para que assim so vamos ver 2 linhas a cada interacao do for, que sao as linhas de dados que queremos
                stop +=2;
            }          
        }
        //Return do array para que este seja usado para meter os dados nos elementos da interface
        return top5; 
    }
}
