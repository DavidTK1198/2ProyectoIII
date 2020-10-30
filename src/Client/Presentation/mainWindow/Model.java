/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client.Presentation.mainWindow;

import chatProtocol.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 
 *@author DavidTK1198
 */
public class Model extends Observable{
    
    
    private TableModel table;
    private User us;
    private int[] col = {0,1};
    private List<User> lista;
    private boolean editable;
    
    public void addObserver(Observer a) {
       
        super.addObserver(a);
        refresh();
    }

  

    public Model() {
        us = new User();
        lista = new ArrayList<>();
        table = new TableModel(lista,col);
        editable = false;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    private void refresh(){
         us = new User();
        this.setChanged();
        this.notifyObservers();
        
    }

    public TableModel getTable() {
        return table;
    }

    public void setTable(List<User> tablee) {
        table = new TableModel(tablee,col);
    }

    public User getUser() {
        return us;
    }

    public void setUser(User use) {
        this.us = use;
    }

    public List<User> getLista() {
        return lista;
    }

    public void setLista(List<User> a) {
        this.lista = a;
        setTable(a);
        refresh();
    }

    public User getRow(int n) {
       return  table.getRowAt(n);
    }

    void commit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
