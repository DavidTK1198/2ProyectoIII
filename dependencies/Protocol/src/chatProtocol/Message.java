/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatProtocol;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DavidTK1198
 */
public class Message implements Serializable {

    String fecha;
    String destinatario;
    String Remitente;

    public String getFecha() {
        return fecha;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getRemitente() {
        return Remitente;
    }

    public Message(String destinatario, String Remitente) {
         Date date = new Date();
        fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
        this.destinatario = destinatario;
        this.Remitente = Remitente;
    }
    public Message() {
        this.fecha = "";
        this.destinatario ="";
        this.Remitente = "";
    }
    
    

}
