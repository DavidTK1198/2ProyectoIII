/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author DavidTK1198
 */
public class XmlPersister {

    private String path;
    private static XmlPersister my_instance = null; //Singleton

    public XmlPersister() {
    }

    public void setPath(String p) {
        this.path = p;
    }

    public static XmlPersister getInstance() {
        if (my_instance == null) {
            my_instance = new XmlPersister();
        }
        return my_instance;
    }

    public void store(LocalData my_data) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(LocalData.class);
        FileOutputStream os = new FileOutputStream(path);
        Marshaller nuevo = jaxbContext.createMarshaller();
        nuevo.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        nuevo.marshal(my_data, os);
        os.flush();
        os.close();

    }

    public LocalData restore() throws Exception {
        
        JAXBContext jaxbContext = JAXBContext.newInstance(LocalData.class);
        FileInputStream is = new FileInputStream(path);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        LocalData result = (LocalData) unmarshaller.unmarshal(is);
        is.close();
        return result;
    }

    
}
