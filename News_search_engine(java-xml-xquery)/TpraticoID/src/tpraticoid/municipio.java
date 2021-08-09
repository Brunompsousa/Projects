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
public class municipio {

    private static int numeracao = 1;
    
    int num;
    String nomeMunicipio;
    String presidenteCamara;
    String mail;
    String webSite;
    String Telefone;
    String numFreguesias;
    String km2;
    String habitantes;
    String Imagem;

    //Construtor
    public municipio(String nomeMunicipio, String presidenteCamara, String mail, String webSite, String Telefone, String numFreguesias, String km2, String habitantes, String Imagem) {
        
        this.num = numeracao;
        this.nomeMunicipio = nomeMunicipio;
        this.presidenteCamara = presidenteCamara;
        this.mail = mail;
        this.webSite = webSite;
        this.Telefone = Telefone;
        this.numFreguesias = numFreguesias;
        this.km2 = km2;
        this.habitantes = habitantes;
        this.Imagem = Imagem;
        
        numeracao++;
    }

    //GET's e SET's
    public int getNum() {
        return num;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public String getPresidenteCamara() {
        return presidenteCamara;
    }

    public String getMail() {
        return mail;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getTelefone() {
        return Telefone;
    }

    public String getNumFreguesias() {
        return numFreguesias;
    }

    public String getKm2() {
        return km2;
    }

    public String getHabitantes() {
        return habitantes;
    }

    public String getImagem() {
        return Imagem;
    }
    
    //Override do tostring usado no programa quando nao usamos a interface para verificar os dados do objecto
    @Override
    public String toString() {
        
        String Noticia = "Numero: " + num + "\nMunicipio : " + nomeMunicipio + "\nPresidente da Camara: " + presidenteCamara + "\nMail: " + mail+ "\nSite: " + webSite + "\nTelefone: " + Telefone + "\n" + numFreguesias + "\nArea:" + km2 + "\n" + habitantes + "\nImagem" + Imagem;
        
        return Noticia; //To change body of generated methods, choose Tools | Templates.
    }
    
}
