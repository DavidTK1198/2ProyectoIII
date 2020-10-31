/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client.Presentation.mainWindow;

import Client.Logic.Contact;
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
    private Contact contact;
    private int[] col = {0,1};
    private List<Contact> lista;
    private boolean editable;
    
    public void addObserver(Observer a) {
       
        super.addObserver(a);
        refresh();
    }
    public Model() {
        
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
        this.contact = new Contact();
        this.setChanged();
        this.notifyObservers();
        
    }
    

    public TableModel getTable() {
        return table;
    }

    public void setTable(List<Contact> tablee) {
        table = new TableModel(tablee,col);
    }

    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact use) {
        this.contact = use;
    }

    public List<Contact> getLista() {
        return lista;
    }

    public void setLista(List<Contact> a) {
        this.lista = a;
        setTable(a);
        refresh();
    }

    public Contact getRow(int n) {
       return  table.getRowAt(n);
    }

    void commit() {
        this.contact = new Contact();
        this.setChanged();
        this.notifyObservers();
    }
}
