/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Data;


import Client.Application.Session;
import Client.Logic.Chat;
import chatProtocol.Message;
import chatProtocol.User;
import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
/**
 *
 * @author DavidTK1198
 */
public class XmlPersister {

    private static XmlPersister my_instance = null; //Singleton

    public XmlPersister() {
    }
    public static XmlPersister getInstance() {
        if (my_instance == null) {
            my_instance = new XmlPersister();
        }
        return my_instance;
    }
    public void makeXml(List<Chat> lc) throws Exception {

        User u1 = (User) Session.instance().getAttribute("user");
        // Creo una instancia de DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Creo un documentBuilder
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Creo un DOMImplementation
        DOMImplementation implementation = builder.getDOMImplementation();

        // Creo un documento con un elemento raiz
        Document documento = implementation.createDocument(null, "LocalData", null);
        documento.setXmlVersion("1.0");

        // Creo los elementos
        

        //Nombre Contacto
       

        for (int i = 0; i < lc.size(); i++) {
            Chat c = lc.get(i);
            Element chat = documento.createElement("Chat");
           

            //codigo
            Element contacto = documento.createElement("Contacto");
            Text id = documento.createTextNode(c.getIdContacto());
            contacto.appendChild(id);
            chat.appendChild(contacto);

            //nombre
            List<Message> lm = c.getMensajes();
            for(int j=0; j<lm.size(); j++){
                Message ms = lm.get(i);
                Element conversacion = documento.createElement("Mensajes");
                
                
                Element destinatario = documento.createElement("Destinatario");
                Text contenidoReal = documento.createTextNode(ms.getDestinatario());
                destinatario.appendChild(contenidoReal);
                conversacion.appendChild(destinatario);
                
                Element remitente = documento.createElement("Remitente");
                Text contenidoReal2 = documento.createTextNode(ms.getRemitente());
                remitente.appendChild(contenidoReal2);
                conversacion.appendChild(remitente);
                
                Element mensaje = documento.createElement("Mensaje");
                Text contenidoReal3 = documento.createTextNode(ms.getMensaje());
                mensaje.appendChild(contenidoReal3);
                conversacion.appendChild(mensaje);
                
                Element fecha = documento.createElement("Fecha");
                Text contenidoReal4 = documento.createTextNode(ms.getFecha());
                fecha.appendChild(contenidoReal4);
                conversacion.appendChild(fecha);
                
                Element hora = documento.createElement("Hora");
                Text contenidoReal5 = documento.createTextNode(ms.getHora());
                hora.appendChild(contenidoReal5);
                conversacion.appendChild(hora);
                
                chat.appendChild(conversacion);
            }
             documento.getDocumentElement().appendChild(chat);
        }

       
        // Asocio el source con el Document
        Source source = new DOMSource(documento);
        // Creo el Result, indicado que fichero se va a crear
        
        //Result result = new StreamResult(new File("FacturaElectronica.xml"));
        Result result = new StreamResult(new File(u1.getId()+".xml"));// no se si esto sirve  
        // Creo un transformer, se crea el fichero XML
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);

    }


    
}

