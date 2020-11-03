package Client.Presentation.login;

import Client.Application.Session;
import chatProtocol.User;
import chatProtocol.Protocol;
import chatProtocol.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.SwingUtilities;
import chatProtocol.IService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceProxy implements IService{
    private static IService theInstance;
    public static IService instance(){
        if (theInstance==null){ 
            theInstance=new ServiceProxy();
        }
        return theInstance;
    }
    
    Socket skt;    
    ObjectInputStream in;
    ObjectOutputStream out;
    Controller controller;

    public ServiceProxy() {           
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


    private void connect() throws Exception{
        skt = new Socket(Protocol.SERVER,Protocol.PORT);
        out = new ObjectOutputStream(skt.getOutputStream() );
        in = new ObjectInputStream(skt.getInputStream());    
    }

    private void disconnect() throws Exception{
        skt.shutdownOutput();
        skt.close();
    }
    
    public User login(User u) throws Exception{
        connect();
        try {
            out.writeInt(Protocol.LOGIN);
            out.writeObject(u);
            out.flush();
            int response = in.readInt();
            if (response==Protocol.ERROR_NO_ERROR){
                User u1=(User) in.readObject();
                this.start();
                return u1;
            }
            else {
                disconnect();
                throw new Exception("No remote user");
            }            
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }
    
    public void logout(User u) throws Exception{
        out.writeInt(Protocol.LOGOUT);
        out.writeObject(u);
        out.flush();
        this.stop();
        this.disconnect();
    }
    
    public void post(Message message){
        try {
            out.writeInt(Protocol.POST);
            out.writeObject(message);
            out.flush();            
        } 
        catch (IOException ex) {}   
    }  
    public void nuevoContactoAnadido(User us){
         try {
            out.writeInt(Protocol.CONECTADO);
            out.writeObject(us);
            out.flush();            
        } 
        catch (IOException ex) {}   
    }
    
    // LISTENING FUNCTIONS
   boolean continuar = true;    
   public void start(){
        Thread t = new Thread(new Runnable(){
            public void run(){
                listen();
            }
        });
        continuar = true;
        t.start();
    }
    public void stop(){
        continuar=false;
    }
    
   public void listen(){
        int method;
        while (continuar) {
            try {
                method = in.readInt();
                switch(method){
                case Protocol.DELIVER:
                    try {
                        Message message=(Message)in.readObject();
                        deliver(message);
                    } 
                    catch (ClassNotFoundException ex) {}
                    break;
                case Protocol.CONECTADO:
                    User u1=(User) in.readObject();
                    this.controller.Update(u1);
                    break;
                }
         
                out.flush();
            } catch (IOException ex) {
                continuar = false;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServiceProxy.class.getName()).log(Level.SEVERE, null, ex);
            }                        
        }
    }
    
  
    
   private void deliver( final Message  message ){
      SwingUtilities.invokeLater(new Runnable(){
            public void run(){
               controller.deliver(message);
            }
         }
      );
   }

    @Override
    public User Registro(User usuario) throws Exception {
         connect();
        try {
            out.writeInt(Protocol.REGISTER);
            out.writeObject(usuario);
            out.flush();
            int response = in.readInt();
            if (response==Protocol.ERROR_NO_ERROR){
                User u1=(User) in.readObject();
                this.start();
                return u1;
            }
            else {
                disconnect();
                throw new Exception("Los datos ingresados corresponder a otro usuario del sistema");
            }            
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

}













