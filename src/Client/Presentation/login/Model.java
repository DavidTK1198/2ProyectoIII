/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client.Presentation.login;

import Client.Logic.Chat;
import Client.Logic.Profile;
import chatProtocol.Message;
import chatProtocol.User;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *@author DavidTK1198
 */


public class Model extends java.util.Observable {
    User currentUser;
    List<Message> messages;
    private Profile perfil;
    Chat nuevoChat;
    Message mensaje;

    public Model() {
       currentUser = null;
       messages=new ArrayList<>();
       perfil = new Profile();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Profile getPerfil() {
        return perfil;
    }

    public void setPerfil(Profile perfil) {
        this.perfil = perfil;
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
    

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        this.commit();
    }
    
    public void commit(){
        this.setChanged();
        this.notifyObservers();        
    } 



}


