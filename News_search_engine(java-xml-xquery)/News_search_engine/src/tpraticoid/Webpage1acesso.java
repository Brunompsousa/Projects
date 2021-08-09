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
public class Webpage1acesso {

        
    // Descarrega a pagina (primeiro site / noticias) e guarda-a
    public static void procurarNoticias(String data){
        
        //Link base para a pesquisa onde e introduzida a data ja formatada de modo a que o site esteja correto para o guardarmos
        String link = "https://dre.pt/web/guest/pesquisa-avancada/-/asearch/advanced/maximized?types=SERIEII&dataPublicacaoInicio="+data+"&dataPublicacaoFim="+data+"&parte=H+-+Autarquias+locais_II&sortOrder=ASC&search=Pesquisar&fpb=dHJ1ZQ%3D%3D";
        
        //TESTE DATA DIFERENTE DATAS
        //String link = "https://dre.pt/web/guest/pesquisa-avancada/-/asearch/advanced/maximized?types=SERIEII&dataPublicacaoInicio=2017-09-02&dataPublicacaoFim=2017-01-02&parte=H+-+Autarquias+locais_II&sortOrder=ASC&search=Pesquisar&fpb=dHJ1ZQ%3D%3D";
        //String link = "https://dre.pt/web/guest/pesquisa-avancada/-/asearch/advanced/maximized?types=SERIEII&dataPublicacaoInicio=2017-08-10&dataPublicacaoFim=2017-08-10&parte=H+-+Autarquias+locais_II&sortOrder=ASC&search=Pesquisar&fpb=dHJ1ZQ%3D%3D";

        //função que vai copiar a pagina para o computador
        HttpRequestFunctions.httpRequestUTF(link, data, "Site1.html"); 
                
    }
    
    //Vai buscar o tipo das publicações
    public static ArrayList procTipoPublic() throws IOException
    {
        //Er feitas para procurar o titulo da noticia
        String ernome="title=\"([a-z A-Z 0-9 À-ú\\s\\(\\)]+)n.º";
        
        //Criacao do pattern
        Pattern p = Pattern.compile(ernome);
        
        //Arraylist para guardar o tipo das publicações
        ArrayList<String> tipoPublicacao = new ArrayList<>();
        
        //Scanner para ler o ficheiro do site que se foi buscar
        Scanner bR = new Scanner(new FileReader("Site1.html"));
        
        //Enquanto houver proxima linha no ficheiro
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            Matcher m = p.matcher(line);
        
            //Procura se ha alguma expressao que coincida com a ER
            while(m.find()){
                
                //Quando encontra, adiciona a mesma ao arraylist
                tipoPublicacao.add(m.group(1));
            }
        }
        //Fecha o scanner de leitura do ficheiro
        bR.close();
            
        //Retorna o array com todas as expressoes que coincidiram com a ER
        return tipoPublicacao;
    }
    
    //Vai buscar o numero das publicacoes
    public static ArrayList procNumPublic() throws IOException
    {
        
        //Er feitas para procurar titulo da noticia
        String ernome="title=\"[a-z A-Z À-ú \\s\\(\\)]*\\sn.º\\s(\\d{1,5}(\\-[A-Z])?/\\d{4})";
        
        //Criacao do pattern
        Pattern p = Pattern.compile(ernome); 
        
        //Arraylist para guardar os numeros das publicações
        ArrayList<String> NumPublicacao = new ArrayList<>();
        
        //Scanner para ler o ficheiro do site que se foi buscar
        Scanner bR = new Scanner(new FileReader("Site1.html"));
        
        //Enquanto houver proxima linha no ficheiro
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            Matcher m = p.matcher(line);
        
            //Procura se ha alguma expressao que coincida com a ER
            while(m.find()){
                
                //Quando encontra, adiciona a mesma ao arraylist
                NumPublicacao.add(m.group(1));
            }
        }
        //Fecha o scanner de leitura do ficheiro
        bR.close();
        
        //Retorna o array com todas as expressoes que coincidiram com a ER
        return NumPublicacao;
    }
    
    //Vai buscar a data das publicacoes
    public static ArrayList procDataPublic() throws IOException
    {
        //Er feitas para procurar a data da noticia
        String ernome="((19[0-9]{2}|20[0-9]{2})[\\-|\\/](0[1-9]|1[0-2])[\\-|\\/]([1,2][0-9]|3[0,1]|0[1-9]))</a>";
        
        //Criacao do pattern
        Pattern p = Pattern.compile(ernome); 
        
        //Arraylist para as datas das noticias
        ArrayList<String> dataPublicacao = new ArrayList<>();
        
        //Scanner para ler o ficheiro do site que se foi buscar
        Scanner bR = new Scanner(new FileReader("Site1.html"));
        
        //Enquanto houver proxima linha no ficheiro
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            Matcher m = p.matcher(line);
        
            //Procura se ha alguma expressao que coincida com a ER
            while(m.find()){
                
                //Quando encontra, adiciona a mesma ao arraylist
                dataPublicacao.add(m.group(1));
            }
        } 
        //Fecha o scanner de leitura do ficheiro
        bR.close();
        
        //Retorna o array com todas as expressoes que coincidiram com a ER
        return dataPublicacao;
    }
    
    //Vai buscar o autor das publicacoes
    public static ArrayList procAutor() throws IOException
    {
        //Er feitas para procurar os autores das noticias
        String ernome="class=\"author\">\\s*([A-Z a-z À-ú\\s]+)";
        
        //Criacao do pattern
        Pattern p = Pattern.compile(ernome); 
               
        //Arraylist para guardar os autores das noticias
        ArrayList<String> autorPublicacao = new ArrayList<>();
        
        //Scanner para ler o ficheiro do site que se foi buscar
        Scanner bR = new Scanner(new FileReader("Site1.html"));
        
        //Enquanto houver proxima linha no ficheiro
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            //Vai verificar se a linha que guardamos contem a string e se existe uma linha depois da que guardamos
            if (bR.hasNextLine() && line.contains("class=\"author\">")==true){
                
                //Junta a linha que ja tinhamos guardada com a proxima linha no ficheiro que estamos a ler
                line = line + " " + bR.nextLine();
            }
            
            Matcher m = p.matcher(line);
        
            //Procura se ha alguma expressao que coincida com a ER
            while(m.find()){
                
                //Quando encontra, adiciona a mesma ao arraylist
                autorPublicacao.add(m.group(1).trim());
            }
        }   
        //Fecha o scanner de leitura do ficheiro
        bR.close();
         
        //Retorna o array com todas as expressoes que coincidiram com a ER
        return autorPublicacao;
    }
    
    //Vai buscar as descrições das publicacoes
    public static ArrayList procDescricao() throws IOException
    {
        //Er feitas para procurar descriçao da noticia
        String ernome="<p>([a-z A-Z 0-9 À-ú \\-\\s\\,\\.\\º\\(\\)\\ª\\/\\:]+)</p>";
        
        //Criacao do pattern
        Pattern p = Pattern.compile(ernome); 
        
        
        //Arraylist para os nomes dos produtos
        ArrayList<String> Descricao = new ArrayList<>();
        
        //Scanner para ler o ficheiro que ja existe usado na procura dos nomes
        Scanner bR = new Scanner(new FileReader("Site1.html"));
        
        //Enquanto for possivel ler a proxima linha ele faz o while
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = "";
            
            if(bR.hasNextLine())
            {
                line = bR.nextLine();
            }
            
            if (bR.hasNextLine() && line.contains("class=\"summary\">")==true){
                line = line + " " + bR.nextLine();
            }
            
            Matcher m = p.matcher(line);
        
            //Procura se ha o Er na linha que temos guardada
            while(m.find()){
                //Se encontrar guarda os dados na array list
                Descricao.add(m.group(1));
            }
        }
        
        //Fecha o scanner de leitura do ficheiro
        bR.close();
        
        return Descricao;
    }
    
    //Vai buscar os links das publicacoes
    public static ArrayList procLink() throws IOException
    {
        //Er feitas para procurar link da noticia
        String ernome="<a href=\"(https://dre.pt/web/guest/pesquisa-avancada/-/asearch/1([a-z A-Z 0-9 À-ú \\s \\:\\/\\.\\-\\?\\=\\&\\;\\+\\_]+))\" title";
        
        //Criacao do pattern
        Pattern p = Pattern.compile(ernome); 
        
        //Arraylist para guardar os links das publicações
        ArrayList<String> Link = new ArrayList<>();
        
        //Scanner para ler o ficheiro do site que se foi buscar
        Scanner bR = new Scanner(new FileReader("Site1.html"));
        
        //Enquanto houver proxima linha no ficheiro
        while (bR.hasNextLine()) {          
            
            //Le a linha do ficheiro para a string line
            String line = bR.nextLine();
            
            //Caso o ficheiro nao tenha acabado, juntamos a proxima linha a string que ja tinhamos com a linha anterior
            if(bR.hasNextLine())
            {
                line = line +"\n"+ bR.nextLine();
            }
            
            Matcher m = p.matcher(line);
        
            //Procura se ha alguma expressao que coincida com a ER
            while(m.find()){
                
                //Quando encontra, adiciona a mesma ao arraylist
                Link.add(m.group(1));
            }
        }
        
        //Fecha o scanner de leitura do ficheiro
        bR.close();
        
        //Retorna o array com todas as expressoes que coincidiram com a ER
        return Link;
    }
    
    
}


