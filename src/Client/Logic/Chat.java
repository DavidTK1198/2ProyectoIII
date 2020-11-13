/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Logic;

import chatProtocol.Message;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

/**
 *
 * @author DavidTK119
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Chat {

    @XmlID
    private String idContacto;
    private List<Message> mensajes;

    public String getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(String idContacto) {
        this.idContacto = idContacto;
    }

    public List<Message> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Message> mensajes) {
        this.mensajes = mensajes;
    }

    public Chat(String idContacto) {
        this.idContacto = idContacto;
        this.mensajes = new ArrayList<>();
    }

    public Chat() {
        this.idContacto = "";
        this.mensajes = new ArrayList<>();
    }

    public void addMsj(Message men) {
        mensajes.add(men);
    }
    public String mostrarConversacion(){
        String conversacion = "";
        for(Message ms: mensajes){
            conversacion+= ms.toString2();
        }
        return conversacion;
    }

}

