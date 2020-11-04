/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Data;


import Client.Application.Session;
//import Client.Logic.Chat;
//import chatProtocol.Message;
//import chatProtocol.User;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.util.List;
import javafx.scene.chart.PieChart.Data;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Result;
//import javax.xml.transform.Source;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import org.w3c.dom.DOMImplementation;
//import org.w3c.dom.Text;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
/**
 *
 * @author DavidTK1198
 */
public class XmlPersister {

    private static XmlPersister my_instance = null; //Singleton
    private String path;
   

    public XmlPersister() {
          this.path = "";
    }
    public static XmlPersister getInstance() {
        if (my_instance == null) {
            my_instance = new XmlPersister();
        }
        return my_instance;
    }
    
 

    public void setPath(String p) {
        this.path = p;
    }

  

    public void store(Session my_data) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Session.class);
        FileOutputStream os = new FileOutputStream(path);
        Marshaller nuevo = jaxbContext.createMarshaller();
        nuevo.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        nuevo.marshal(my_data, os);
        os.flush();
        os.close();

    }

    public Data restore() throws Exception {
        
        JAXBContext jaxbContext = JAXBContext.newInstance(Session.class);
        FileInputStream is = new FileInputStream(path);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Data result = (Data) unmarshaller.unmarshal(is);
        is.close();
        return result;
    }
    
//    public void makeXml(List<Chat> lc) throws Exception {
//
//        User u1 = (User) Session.instance().getAttribute("user");
//        // Creo una instancia de DocumentBuilderFactory
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        // Creo un documentBuilder
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        // Creo un DOMImplementation
//        DOMImplementation implementation = builder.getDOMImplementation();
//
//        // Creo un documento con un elemento raiz
//        Document documento = implementation.createDocument(null, "LocalData", null);
//        documento.setXmlVersion("1.0");
//
//        // Creo los elementos
//        
//
//        //Nombre Contacto
//       
//
//        for (int i = 0; i < lc.size(); i++) {
//            Chat c = lc.get(i);
//            Element conversacion = documento.createElement("Mensajes");
//            Element chat = documento.createElement("Chat");
//           
//
//            //codigo
//            Element contacto = documento.createElement("Contacto");
//            Text id = documento.createTextNode(c.getIdContacto());
//            contacto.appendChild(id);
//            chat.appendChild(contacto);
//
//            //nombre
//            List<Message> lm = c.getMensajes();
//            for(int j=0; j<lm.size(); j++){
//                Message ms = lm.get(j);
//                
//                
//                
//                Element destinatario = documento.createElement("Destinatario");
//                Text contenidoReal = documento.createTextNode(ms.getDestinatario());
//                destinatario.appendChild(contenidoReal);
//                conversacion.appendChild(destinatario);
//                
//                Element remitente = documento.createElement("Remitente");
//                Text contenidoReal2 = documento.createTextNode(ms.getRemitente());
//                remitente.appendChild(contenidoReal2);
//                conversacion.appendChild(remitente);
//                
//                Element mensaje = documento.createElement("Mensaje");
//                Text contenidoReal3 = documento.createTextNode(ms.getMensaje());
//                mensaje.appendChild(contenidoReal3);
//                conversacion.appendChild(mensaje);
//                
//                Element fecha = documento.createElement("Fecha");
//                Text contenidoReal4 = documento.createTextNode(ms.getFecha());
//                fecha.appendChild(contenidoReal4);
//                conversacion.appendChild(fecha);
//                
//                Element hora = documento.createElement("Hora");
//                Text contenidoReal5 = documento.createTextNode(ms.getHora());
//                hora.appendChild(contenidoReal5);
//                conversacion.appendChild(hora);
//                
//                
//            }
//            chat.appendChild(conversacion);
//             documento.getDocumentElement().appendChild(chat);
//        }
//
//       
//        // Asocio el source con el Document
//        Source source = new DOMSource(documento);
//        // Creo el Result, indicado que fichero se va a crear
//        
//        //Result result = new StreamResult(new File("FacturaElectronica.xml"));
//        Result result = new StreamResult(new File(u1.getId()+".xml"));// no se si esto sirve  
//        // Creo un transformer, se crea el fichero XML
//        Transformer transformer = TransformerFactory.newInstance().newTransformer();
//        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//        transformer.transform(source, result);
//
//    }
//
//
//      public void read(User us) throws Exception {
//
//        String aux = "";
//        // Creo una instancia de DocumentBuilderFactory
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        // Creo un documentBuilder
//        DocumentBuilder builder = factory.newDocumentBuilder();
//
//        // Obtengo el documento, a partir del XML
//        Document documento = builder.parse(new File(us.getId()+".xml"));
//
//        // agarro la etiquete emp del documento
//        NodeList miEmpresa = documento.getElementsByTagName("LocalData");
//
//        // Recorro las etiquetas
//        for (int i = 0; i < miEmpresa.getLength(); i++) {
//            // Cojo el nodo actual
//            Node nodo = miEmpresa.item(i);
//            // Compruebo si el nodo es un elemento
//            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
//                // Lo transformo a Element
//                Element e = (Element) nodo;
//                // Obtengo sus hijos
//                NodeList hijos = e.getChildNodes();
//                // Recorro sus hijos
//                for (int j = 0; j < hijos.getLength(); j++) {
//                    // Obtengo al hijo actual
//                    Node hijo = hijos.item(j);
//                    // Compruebo si es un nodo
//                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
//                        
//                        // evaluo el contenido
//                        aux = hijo.getNodeName();
//                        if ("nombreEm".equals(aux)) {
//                            aux = hijo.getTextContent();
////                            Logic.Empresa.getInstance().setNombre(aux);
//                        }
//
//                        if ("telefono".equals(aux)) {
//                            aux = hijo.getTextContent();
////                            Logic.Empresa.getInstance().setTelefono(aux);
//                        }
//
//                        if ("correo".equals(aux)) {
//                            aux = hijo.getTextContent();
////                            Logic.Empresa.getInstance().setCorreo(aux);
//                        }
//
//                        if ("actividad".equals(aux)) {
//                            aux = hijo.getTextContent();
////                            Logic.Empresa.getInstance().setActividad(aux);
//                        }
//
//                        if ("CedulaJuridica".equals(aux)) {
//                            aux = hijo.getTextContent();
////                            Logic.Empresa.getInstance().setCedulaJuridica(aux);
//                        }
//                        if ("direccion".equals(aux)) {
//                            aux = hijo.getTextContent();
////                            Logic.Empresa.getInstance().setDireccion(aux);
//                        }
//
//                    }
//
//                }
//
//            }
//
//        }
//
//    }


}







