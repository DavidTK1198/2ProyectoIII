package Server;

import chatProtocol.Message;
import chatProtocol.Protocol;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import chatProtocol.User;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Server {

    ServerSocket srv;
    List<Worker> workers;
    List<User> conectados;

    public Server() {
        try {
            srv = new ServerSocket(Protocol.PORT);
            workers = Collections.synchronizedList(new ArrayList<Worker>());
            conectados = new ArrayList<>();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getCause());
        }
    }

    public void run() {
        Service localService = (Service) (Service.instance());
        localService.setSever(this);
        boolean continuar = true;
        while (continuar) {
            try {
                Socket skt = srv.accept();
                ObjectInputStream in = new ObjectInputStream(skt.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(skt.getOutputStream());
                try {
                    int method = in.readInt(); // should be Protocol.LOGIN
                    switch (method) {
                        case 1: {
                            User user = (User) in.readObject();
                            try {
                                user = Service.instance().login(user);
                                out.writeInt(Protocol.ERROR_NO_ERROR);
                                out.writeObject(user);
                                out.flush();
                                Worker worker = new Worker(skt, in, out, user);
                                workers.add(worker);
                                worker.start();
                            } catch (Exception ex) {
                                out.writeInt(Protocol.ERROR_LOGIN);
                                out.flush();
                            }
                        }
                        break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            List<User> lu = (List<User>) in.readObject();
                            try{
                                for(int i=0; i<lu.size();i++){
                                    User conec = lu.get(i);
                                    this.usuariosConectados(conec);
                                }
                                out.writeInt(Protocol.ERROR_NO_ERROR);
                                out.writeObject(conectados);
                                out.flush();
                                conectados = new ArrayList<>();
                                
                                
                            }catch(Exception ex){
                                out.writeInt(Protocol.ERROR_UPDATE);
                                out.flush();
                            }
                        
                    }
                } catch (ClassNotFoundException ex) {
                    System.out.println("hoola");
                }

            } catch (IOException ex) {
                System.out.println("mundo");
            }
        }
    }

    public void deliver(Message message) {
        for (Worker wk : workers) {
            User n = wk.getUser();
            if (n.getId().equals(message.getDestinatario())) {
                wk.deliver(message);
            }
        }
    }

    public void remove(User u) {
        for (Worker wk : workers) {
            if (wk.user.equals(u)) {
                workers.remove(wk);
                try {
                    wk.skt.close();
                } catch (IOException ex) {
                }
                break;
            }
        }
    }
      public void usuariosConectados(User usuario)throws Exception{
          if(workers.size() == 1){
              return;
          }
          for(int i=0; i<this.workers.size(); i++){
              if(usuario.getId().equals(workers.get(i).getUser().getId())){
                  conectados.add(usuario);
              }
          }
         
    }

}
