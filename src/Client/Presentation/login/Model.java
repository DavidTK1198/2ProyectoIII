/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client.Presentation.login;

import Client.Logic.Profile;
import chatProtocol.User;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *@author DavidTK1198
 */


public class Model extends java.util.Observable {
    User currentUser;
    List<String> messages;
    private Profile perfil;

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
    

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
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
