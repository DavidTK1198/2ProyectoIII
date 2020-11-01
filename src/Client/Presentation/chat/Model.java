/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client.Presentation.chat;

import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @author DavidTK1198
 */
public class Model extends Observable {
      public void addObserver(Observer a) {
       
        super.addObserver(a);
        refresh();
    }
        private void refresh(){
        this.setChanged();
        this.notifyObservers();
        
    }
    
      
    void commit() {
        this.setChanged();
        this.notifyObservers();
    }
    

}
