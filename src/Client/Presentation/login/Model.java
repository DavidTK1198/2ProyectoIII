/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client.Presentation.login;

import chatProtocol.User;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *@author DavidTK1198
 */


public class Model extends java.util.Observable {
    User currentUser;
    List<User> users;

    public Model() {
       currentUser = new User();
       users=new ArrayList<>();
     
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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





