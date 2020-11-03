/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client.Presentation.login;

import chatProtocol.User;

/**
 * 
 *@author DavidTK1198
 */


public class Model extends java.util.Observable {
    User currentUser;
    User contactUser;

    public Model() {
       currentUser = new User();
       this.contactUser = new User();
      
     
    }

    public User getContactUser() {
        return contactUser;
    }

    public void setContactUser(User contactUser) {
        this.contactUser = contactUser;
    }

    public User getCurrentUser() {///M
        return currentUser;
    }

   

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
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






