/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Logic;

import chatProtocol.Message;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DavidTK119
 */
public class Chat {

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
    public void addMsj(Message men){
        mensajes.add(men);
    }

}
