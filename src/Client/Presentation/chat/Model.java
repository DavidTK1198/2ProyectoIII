/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client.Presentation.chat;

import Client.Logic.Chat;
import chatProtocol.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @author DavidTK1198
 */
public class Model extends Observable {
    
    List<Message> messages;;
    Chat nuevoChat;
    Message mensaje;
    String nombrePersona;
    String remitente;
    @Override
      public void addObserver(Observer a) {
       
        super.addObserver(a);
        refresh();
    }

    public Model() {
        this.messages = new ArrayList<>();
        this.nuevoChat = new Chat();
        this.mensaje = new Message();
        this.nombrePersona = "";
        this.remitente = "";
        
    }
      
        private void refresh(){
        this.setChanged();
        this.notifyObservers();
        
    }
       public Chat getNuevoChat() {
        return nuevoChat;
    }

    public void setNuevoChat(Chat nuevoChat) {
        this.nuevoChat = nuevoChat;
    }

    public Message getMensaje() {
        return mensaje;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }
    

    public void setMensaje(Message mensaje) {
        this.mensaje = mensaje;
    }
    
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
      
    void commit() {
        this.mensaje = new Message();
        this.setChanged();
        this.notifyObservers();
    }

    void setContacto(String nombre) {
        nombrePersona = nombre;
    }
    

}
