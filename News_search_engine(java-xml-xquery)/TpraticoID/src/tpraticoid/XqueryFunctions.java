/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tpraticoid;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;
import net.sf.saxon.Configuration;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;

/**
 *
 * @author abs
 */
public class XqueryFunctions {
    public static void xQueryToText(String outputFile, String queryFile) throws XPathException, IOException{
        Configuration config = new Configuration();
        StaticQueryContext sqc = new StaticQueryContext(config);
        XQueryExpression exp = sqc.compileQuery(new FileReader(queryFile));
        DynamicQueryContext dynamicContext = new DynamicQueryContext(config);
        
        Properties props = new Properties();
        props.setProperty(OutputKeys.METHOD, "text");
        exp.run(dynamicContext, new StreamResult(new File(outputFile)), props);
    }
    
    public static void xQueryToHtml(String outputFile, String queryFile) throws XPathException, IOException{
        Configuration config = new Configuration();
        StaticQueryContext sqc = new StaticQueryContext(config);
        XQueryExpression exp = sqc.compileQuery(new FileReader(queryFile));
        DynamicQueryContext dynamicContext = new DynamicQueryContext(config);
        
        Properties props = new Properties();
        props.setProperty(OutputKeys.METHOD, "html");
        props.setProperty(OutputKeys.DOCTYPE_PUBLIC, "-//W3C//DTD HTML 4.01 Transitional//EN");
        exp.run(dynamicContext, new StreamResult(new File(outputFile)), props);
    }
    
    public static void xQueryToXml(String outputFile, String queryFile) throws XPathException, IOException{
        Configuration config = new Configuration();
        StaticQueryContext sqc = new StaticQueryContext(config);
        XQueryExpression exp = sqc.compileQuery(new FileReader(queryFile));
        DynamicQueryContext dynamicContext = new DynamicQueryContext(config);
       
        Properties props = new Properties();
        props.setProperty(OutputKeys.METHOD, "xml");
        exp.run(dynamicContext, new StreamResult(new File(outputFile)), props);
    }
    
}
