/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Logic;

/**
 *
 * @author DavidTK119
 */
public class Contact {

    private String nombre;
    private String IdC;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdC() {
        return IdC;
    }

    public void setIdC(String IdC) {
        this.IdC = IdC;
    }

    public Contact(String nombre, String IdC) {
        this.nombre = nombre;
        this.IdC = IdC;
    }

    public Contact() {
        this.nombre = "";
        this.IdC = "";
    }

}
