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
 * @author DavidTK1198
 */
public class Model extends Observable {

    private TableModel table;
    private User contact;
    private int[] col = {0, 1};
    private List<User> lista;
    private boolean editable;

    public void addObserver(Observer a) {

        super.addObserver(a);
        refresh();
    }

    public Model() {

        lista = new ArrayList<>();
        table = new TableModel(lista, col);
        editable = false;
        this.contact = new User();
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    private void refresh() {
        this.contact = new User();
        this.setChanged();
        this.notifyObservers();

    }

    public TableModel getTable() {
        return table;
    }

    public void setTable(List<User> tablee) {
        table = new TableModel(tablee, col);
    }

    public User getContact() {
        return this.contact;
    }

    public void setContact(User use) {
        this.contact = use;
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
        return table.getRowAt(n);
    }

    void commit() {
        this.contact = new User();
        this.setChanged();
        this.notifyObservers();
    }

}

