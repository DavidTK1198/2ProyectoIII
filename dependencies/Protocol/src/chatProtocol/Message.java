/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatProtocol;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DavidTK1198
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Message implements Serializable {
    @XmlID
    String fecha;
    String destinatario;
    String Remitente;
    String mensaje;
    String hora;
     @XmlTransient
    Date date;

    public String getFecha() {
        return fecha;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getRemitente() {
        return Remitente;
    }

    
    
    public Message(String destinatario, String Remitente, String mensaje) {
        date = new Date();
        fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
        this.destinatario = destinatario;
        this.Remitente = Remitente;
        this.mensaje = mensaje;
        hora = new SimpleDateFormat("HH:mm").format(date);
    }

    public Message() {
        this.fecha = "";
        this.destinatario = "";
        this.Remitente = "";
        this.mensaje = "";
        this.hora="";
    }

    @Override
    public String toString() {
        return mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setRemitente(String Remitente) {
        this.Remitente = Remitente;
    }
    public String getHora(){
         return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    

}


