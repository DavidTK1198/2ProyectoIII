/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Presentation.mainWindow;

import Client.Logic.Contact;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Daniel Madrigal
 */
public class TableModel extends AbstractTableModel {

    private List<Contact> filas;
    private int[] columnas;
    private String[] nombCol = {"UserName","Estado"};

    public TableModel(List<Contact> filas, int[] columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }

    @Override
    public int getRowCount() {
        return filas.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contact prod = filas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return prod.getIdC();
            case 1: 
                if(prod.isEstado() == false){
                    return "offline";
                }else{
                    return "online";
                }
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return nombCol[column];
    }

    public Contact getRowAt(int n) {
        return filas.get(n);
    }

}



