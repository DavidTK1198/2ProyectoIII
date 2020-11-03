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

    public Server() {
        try {
            srv = new ServerSocket(Protocol.PORT);
            workers = Collections.synchronizedList(new ArrayList<Worker>());

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
                                this.notify(user,1);
                                break;
                            } catch (Exception ex) {
                                out.writeInt(Protocol.ERROR_LOGIN);
                                out.flush();
                            }

                        }
                        case Protocol.REGISTER:
                            User nuevoUsuario = (User) in.readObject();
                            try {
                                nuevoUsuario = Service.instance().Registro(nuevoUsuario);
                                out.writeInt(Protocol.ERROR_NO_ERROR);
                                out.writeObject(nuevoUsuario);
                                out.flush();
                                Worker worker = new Worker(skt, in, out, nuevoUsuario);
                                workers.add(worker);
                                worker.start();
                                worker.setServ(this);
                                break;
                            } catch (Exception ex) {
                                out.writeInt(Protocol.ERROR_REGISTER);
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
        this.notify(u, 2);
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

    public void notify(User nuevo,int numerito) {
        boolean flag = true;
        if(numerito == 1){
            flag = false;
        for (Worker wk : workers) {
            if (wk.getUser().getId().equals(nuevo.getId())) {
                flag = true;
                break;
            }
        }
        }

        if (flag) {
            for (Worker wk : workers) {
                if (!wk.getUser().getId().equals(nuevo.getId())) {
                    switch(numerito){
                        case 1:
                            wk.notifiqueON(nuevo);
                            break;
                        case 2:
                            wk.notifiqueOFF(nuevo);
                            break;
                    }
                }
            }
        }
    }
    

}
