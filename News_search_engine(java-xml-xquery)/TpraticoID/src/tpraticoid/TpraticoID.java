package tpraticoid;

import java.io.IOException;
import java.util.ArrayList;
import net.sf.saxon.trans.XPathException;
import static tpraticoid.XMLmaker.xmlMunicipios;
import static tpraticoid.XMLmaker.xmlnoticias;
import static tpraticoid.XMLtoArrays.*;

/**
 *
 * @author Brums
 * 
 * Ficheiro main realiza as operacoes todas sem parte grafica
 * 
 */
public class TpraticoID {

        public static int dia = 15;
        public static int mes = 11;
        public static int ano = 2017;
        
    public static void main(String[] args) throws IOException, XPathException {
        
       
        //ArrayList para as noticias / primeiro site
        ArrayList<noticia> grupoNoticias = new ArrayList<>();
        //ArrayList para os municipios / segundo site
        ArrayList<municipio> grupoMunicipios = new ArrayList<>();
        
        
        String data  = ano + "-" + mes + "-" + dia;
        
        //Vai buscar o ficheiro da pagina com a data dada
        Webpage1acesso.procurarNoticias(data);
        
        
        ArrayList<String> tipoNoticia = Webpage1acesso.procTipoPublic();
        //System.out.println(tipoNoticia.size());
        ArrayList<String> numNoticia = Webpage1acesso.procNumPublic();
        //System.out.println(numNoticia.size());
        ArrayList<String> dataNoticia = Webpage1acesso.procDataPublic();
        //System.out.println(dataNoticia.size());
        ArrayList<String> AutorNoticia = Webpage1acesso.procAutor();
        //System.out.println(AutorNoticia.size());
        ArrayList<String> DescricaoNoticia = Webpage1acesso.procDescricao();
        //System.out.println(DescricaoNoticia.size());
        ArrayList<String> LinkNoticia = Webpage1acesso.procLink();
        //System.out.println(LinkNoticia.size());
        
        //Criacao das noticias no arraylist com as informacoes
        for(int i=0;i<tipoNoticia.size();i++){ 
            noticia n1 = new noticia(tipoNoticia.get(i),numNoticia.get(i),dataNoticia.get(i),AutorNoticia.get(i),DescricaoNoticia.get(i),LinkNoticia.get(i));         
            grupoNoticias.add(n1);
        }     

        //Vai buscar a pagina que contem os codigos para consulta
        Webpage2acessos.ProcuraSite2();
        
        //Array com os nomes dos municipios ja arranjados
        ArrayList<String> nomesMunic = Webpage2acessos.retiraDuplicados(grupoNoticias);
        
        
        //for para criar os municipios
        for (int i = 0; i < nomesMunic.size(); i++) {
            
            //Retira o texto Municipio de da string e vai buscar o codigo do municipio
            Webpage2acessos.procPageMunicip(nomesMunic.get(i));
            //dados do municipio
            String Presidente = Webpage2acessos.procPresidenteCamara();          
            String mail = Webpage2acessos.procMail();
            String siteInst = Webpage2acessos.procSiteInst();
            String telefone = Webpage2acessos.procTelefone();
            String freg = Webpage2acessos.procFreg();
            String km = Webpage2acessos.prockm2();
            String habitantes = Webpage2acessos.procHabitante();
            String imagem = Webpage2acessos.procimagem();
            
            municipio municip= new municipio(nomesMunic.get(i), Presidente, mail, siteInst, telefone, freg, km, habitantes, imagem);
            
            grupoMunicipios.add(municip);
                    
        } 
        
        //cria o xml das noticias
        xmlnoticias(grupoNoticias);
        xmlMunicipios(grupoMunicipios);
       
        
        //Procura as noticias por autor, dado pelo utilizador
        String pesq = "Lisboa";
        XMLmaker.xmlPesquisa(pesq);
        XqueryFunctions.xQueryToXml("pesqAutor.xml", "XQAutor.xql");
        
        ArrayList<noticia> NoticiasPesqAutor = XmlPesqNoticiasAutorToArray();
        System.out.println(NoticiasPesqAutor);
        
        
        //Procura as noticias por o tipo dado pelo utilizador
        pesq = "Aviso";
        XMLmaker.xmlPesquisa(pesq);
        XqueryFunctions.xQueryToXml("pesqTipo.xml", "XQtipo.xql");
        
        //Procura o municipio por o nome do municipio dado pelo utilizador
        pesq = "Lisboa";
        XMLmaker.xmlPesquisa(pesq);
        XqueryFunctions.xQueryToXml("pesqcamara.xml", "XQCamara.xql");
        
        //Procura os municipios por o nome do presidente dado pelo utilizador
        pesq = "Fernando";
        XMLmaker.xmlPesquisa(pesq);
        XqueryFunctions.xQueryToXml("pesqpresidente.xml", "XQPresidente.xql");
        
        //Cria uma lista ordenada dos municipios das noticias
        XqueryFunctions.xQueryToXml("pesqListaMunic.xml", "XQListaMunic.xql");
        
        //Conta e regista quantas noticias cada municipio tem
        XqueryFunctions.xQueryToXml("pesqListaMunic.xml", "XQListaMunicCount.xql");
        
        //Ordena os dados por ondem decrescente
        XqueryFunctions.xQueryToXml("pesqListaMunic.xml", "XQListaMunicCountDesc.xql");
        
        //Retira o top 5 dos municipios com mais noticias
        XqueryFunctions.xQueryToXml("pesqListaMunic.xml", "XQListaMunicCountDesc5.xql");
        
    }
    
}
