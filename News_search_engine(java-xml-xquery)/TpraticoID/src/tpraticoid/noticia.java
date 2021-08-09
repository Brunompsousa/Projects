/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpraticoid;


/**
 *
 * @author Brums
 */
public class noticia{
    
    private static int numeracao = 1;
    
    int num;
    String tipo;
    String data;
    String autor;
    String descricao;
    String link;
    String numPub;

    //Construtor
    public noticia(String tipo, String numPub, String data, String autor, String descrisao, String link) {
        
        this.num = numeracao;
        this.tipo = tipo;
        this.data = data;
        this.autor = autor;
        this.descricao = descrisao;
        this.link = link;
        this.numPub = numPub;
        
        numeracao++;
    }


    
    //GET's e SET's
    public int getNum() {
        return num;
    }

    public String getTipo() {
        return tipo;
    }

    public String getData() {
        return data;
    }

    public String getAutor() {
        return autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLink() {
        return link;
    }

    public String getNumPub() {
        return numPub;
    }

    //Override do tostring usado no programa quando nao usamos a interface para verificar os dados do objecto
    @Override
    public String toString() {
        
        String Noticia = "\nNumero: " + num + "\nTipo: " + tipo + "\nNumero de Publicacao: " + numPub + "\nData: " + data + "\nAutor: " + autor + "\nDescricao: " + descricao + "\nLink: " + link;
        
        return Noticia; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
