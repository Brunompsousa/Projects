/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpraticoid;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.jdom2.Element;
import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Brums
 */
public class XMLmaker {
    
    //Funcao que recebe um array de noticias com as noticias todas e cria um xml com os dados do array
    public static void xmlnoticias(ArrayList<noticia> gruponoticias) throws IOException{
        
        //Elemento base para o ficheiro XML
        Element Noticias = new Element("Noticias");
        
        //Documento e damos o elemento base para o mesmo
        Document myDocument = new Document(Noticias);
        
        //For para correr todas as noticias do array e para cada uma vai criar os elements e copiar os dados para os mesmos
        for (noticia noticia : gruponoticias) {
        
            //Cria o elemento de cada noticia, pois este ira ter os dados da mesma
            Element Noticia = new Element("Noticia");

                //atributos da noticia
                Element Numero = new Element("Número");
                Numero.setText(noticia.getNum()+"");
                
                Element Tipo = new Element("Tipo");
                Tipo.setText(noticia.getTipo());
                
                Element numPub = new Element("NúmeroPub");
                numPub.setText(noticia.getNumPub());

                Element dataPub = new Element("Data");
                dataPub.setText(noticia.getData());
                
                Element Autor = new Element("Autor");
                Autor.setText(noticia.getAutor());
                
                Element descPub = new Element("Descrição");
                descPub.setText(noticia.getDescricao());
                
                Element linkPub = new Element("Link");
                linkPub.setText(noticia.getLink());
                
                //Adicionar os elementos ao elemento noticia
                Noticia.addContent(Numero);
                Noticia.addContent(Tipo);
                Noticia.addContent(numPub);
                Noticia.addContent(dataPub);
                Noticia.addContent(Autor);
                Noticia.addContent(descPub);
                Noticia.addContent(linkPub);
                
                //Adiciona o element noticia, com os dados, ao elemento base do documento (Noticias)
                Noticias.addContent(Noticia);
        }

        
        XMLOutputter xout = new XMLOutputter();
        
        BufferedWriter bd = new BufferedWriter(new FileWriter("grupoNoticias.xml"));

        xout.output(Noticias,bd);


    }
    
    //Funcao que recebe um array de municipios e cria um xml com os dados do array   
    public static void xmlMunicipios(ArrayList<municipio> grupomunicipios) throws IOException{
        
        //Elemento base para o ficheiro XML
        Element Municipios = new Element("Municipios");
        
        //Documento e damos o elemento base para o mesmo
        Document myDocument = new Document(Municipios);
        
        //For para correr todas os municipios do array e para cada uma vai criar os elements e copiar os dados para os mesmos
        for (municipio municip : grupomunicipios) {
        
            //Cria o elemento de cada municipio, pois este ira ter os dados
            Element Municipio = new Element("Municipio");

                //atributos da municipio
                Element Numero = new Element("Número");
                Numero.setText(municip.getNum()+"");
                
                Element Nome = new Element("Nome");
                Nome.setText(municip.getNomeMunicipio());
                
                Element Presidente = new Element("Presidente");
                Presidente.setText(municip.getPresidenteCamara());

                Element Mail = new Element("Mail");
                Mail.setText(municip.getMail());
                
                Element site = new Element("site");
                site.setText(municip.getWebSite());
                
                Element Telefone = new Element("Telefone");
                Telefone.setText(municip.getTelefone());
                
                Element nFreguesias = new Element("Freguesias");
                nFreguesias.setText(municip.getNumFreguesias());
                
                Element area = new Element("Area");
                area.setText(municip.getKm2());
                
                Element nHabitantes = new Element("Habitantes");
                nHabitantes.setText(municip.getHabitantes());
                
                Element imagem = new Element("imagem");
                imagem.setText(municip.getImagem());
                
                //Adicionar os elementos ao elemento municipio
                Municipio.addContent(Numero);
                Municipio.addContent(Nome);
                Municipio.addContent(Presidente);
                Municipio.addContent(Mail);
                Municipio.addContent(site);
                Municipio.addContent(Telefone);
                Municipio.addContent(nFreguesias);
                Municipio.addContent(area);
                Municipio.addContent(nHabitantes);
                Municipio.addContent(imagem);
                
                //Adiciona o element municipio, com os dados, ao elemento base do documento (Municipios)
                Municipios.addContent(Municipio);
        }

        XMLOutputter xout = new XMLOutputter();
        
        BufferedWriter bd = new BufferedWriter(new FileWriter("grupoMunicipios.xml"));

        xout.output(Municipios,bd);
    }
    
    //Funcao que recebe uma string com a palavra para pesquisar e cria um xml
    public static void xmlPesquisa(String pesq) throws IOException{
        
        //Elemento para o ficheiro XML
        Element Pesquisa = new Element("Pesquisa");
        Pesquisa.setText(pesq);
        
        //Documento e damos o elemento base para o mesmo, que neste caso e o unico que temos
        Document myDocument = new Document(Pesquisa);

        XMLOutputter xout = new XMLOutputter();
        
        BufferedWriter bd = new BufferedWriter(new FileWriter("pesquisa.xml"));

        xout.output(Pesquisa,bd);
    }

         
}
