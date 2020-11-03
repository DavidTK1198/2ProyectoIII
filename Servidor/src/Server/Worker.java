package Server;

import chatProtocol.User;
import chatProtocol.Protocol;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import chatProtocol.Message;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker {

    Socket skt;
    ObjectInputStream in;
    ObjectOutputStream out;
    User user;
    Server serv;

    public Worker(Socket skt, ObjectInputStream in, ObjectOutputStream out, User user) {
        this.skt = skt;
        this.in = in;
        this.out = out;
        this.user = user;
    }

    public void setServ(Server serv) {
        this.serv = serv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    boolean continuar;

    public void start() {
        try {
            System.out.println("Worker atendiendo peticiones...");
            Thread t = new Thread(new Runnable() {
                public void run() {
                    listen();
                }
            });
            continuar = true;
            t.start();
        } catch (Exception ex) {
        }
    }

    public void stop() {
        continuar = false;
    }

    public void listen() {
        int method;
        while (continuar) {
            try {
                method = in.readInt();
                switch (method) {
                    //case Protocol.LOGIN: done on accept
                    case Protocol.LOGOUT:
                    try {
                        Service.instance().logout(user);
                    } catch (Exception ex) {
                    }
                    stop();
                    break;
                    case Protocol.POST:
                        Message message = null;
                        try {
                            message = (Message) in.readObject();
                            Service.instance().post(message);
                        } catch (ClassNotFoundException ex) {
                        }
                        break;
                    case Protocol.CONECTADO:
                        User nuevo = (User)in.readObject();
                        Service.instance().nuevoContactoAnadido(nuevo);
                        
                }
                out.flush();
            } catch (IOException ex) {
                continuar = false;
            } catch (ClassNotFoundException ex) {
               
            }
        }
    }

    public void deliver(Message message) {
        try {
            out.writeInt(Protocol.DELIVER);
            out.writeObject(message);
            out.flush();
        } catch (IOException ex) {
        }
    }
    public void notifiqueON(User us){
          try {
            
            out.writeInt(Protocol.CONECTADO);
            out.writeObject(us);
            out.flush();
            
        } catch (IOException ex) {
        }
    }
    public void notifiqueOFF(User us){
            try {
            
            out.writeInt(Protocol.DESCONECTADO);
            out.writeObject(us);
            out.flush();
            
        } catch (IOException ex) {
        }
    }

}

