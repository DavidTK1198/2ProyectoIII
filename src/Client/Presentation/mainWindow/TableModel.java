/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Presentation.mainWindow;


import chatProtocol.User;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Daniel Madrigal
 */
public class TableModel extends AbstractTableModel {

    private List<User> filas;
    private int[] columnas;
    private String[] nombCol = {"UserName","Estado"};

    public TableModel(List<User> filas, int[] columnas) {
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
        User prod = filas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return prod.getId();
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

    public User getRowAt(int n) {
        return filas.get(n);
    }

}






