/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client.Presentation.chat;

import Client.Logic.Chat;
import chatProtocol.Message;
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
    @Override
      public void addObserver(Observer a) {
       
        super.addObserver(a);
        refresh();
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
        this.setChanged();
        this.notifyObservers();
    }
    

}
