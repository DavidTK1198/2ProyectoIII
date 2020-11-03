package Server;

import Server.data.UserDao;
import chatProtocol.User;
import chatProtocol.IService;
import chatProtocol.Message;
import java.util.List;

public class Service implements IService{
    private static IService theInstance;
    public static IService instance(){
        if (theInstance==null){ 
            theInstance=new Service();
        }
        return theInstance;
    }
    
    Server srv;
    UserDao users;

    public Service() {        
        users =  new UserDao();
                       
    }
    
    public void setSever(Server srv){
        this.srv=srv;
    }
    
    public void post(Message m){
        srv.deliver(m);
        // TODO if the receiver is not active, store it temporarily
    }
    
    public User login(User u) throws Exception{
        User result=users.read(u.getId());
        if(result==null)  throw new Exception("User does not exist");
        if(!result.getClave().equals(u.getClave()))throw new Exception("User does not exist");
        return result;
    } 

    public void logout(User p) throws Exception{
        srv.remove(p);
    }

  
    @Override
    public User Registro(User usuario) throws Exception {
         
        try {
         users.create(usuario);
       
        User result=users.read(usuario.getId());
        if(result==null)  throw new Exception("User does not exist");
        if(!result.getClave().equals(usuario.getClave()))throw new Exception("User does not exist");
        return result;
             
        }catch(Exception e){
        throw new Exception("Usuario ya existe");
        }                  
    }  

    @Override
    public void nuevoContactoAnadido(User us) {
        this.srv.notify(us);
    }
    
}






