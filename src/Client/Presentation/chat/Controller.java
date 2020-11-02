/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Presentation.chat;

import Client.Application.Session;
import Client.Logic.Chat;
import Client.Logic.Contact;
import Client.Logic.Profile;
import chatProtocol.Message;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DavidTK1198
 */
public class Controller {

    private View vista;
    private Model model;
    private Client.Presentation.login.Controller Parent;
   

    public Controller(View vista, Model model) {
        this.vista = vista;
        this.model = model;
        vista.setControl(this);
        vista.setModel(model);
   

    }

    public Client.Presentation.login.Controller getParent() {
        return Parent;
    }

    public void setParent(Client.Presentation.login.Controller Parent) {
        this.Parent = Parent;
    }

    public void show() {
        vista.setVisible(true);

    }

    public void hide() {
        vista.setVisible(false);
    }

    public void buscarPorID(String ced) {
        
    }

    public void deliver(Message message) {
        model.getMessages().add(message);
        model.commit();
    }
    public void setCurrentContact(Contact c){
        Profile profi = (Profile) Session.instance().getAttribute(Session.USER_ATTRIBUTE);
        List<Chat> lc = profi.getChat();
        boolean flag=false;
        for(int i=0; i<lc.size(); i++){
            if(lc.get(i).getIdContacto().equals(c.getIdC())){
                model.setMessages(lc.get(i).getMensajes());
                flag=true;
                
            }
        }
        if(flag==false){
            Chat cc = new Chat(c.getIdC());
            try {
                profi.addChat(cc);
            } catch (Exception ex) {
                
            }
        }
        model.setContacto(c.getNombre());
        
        model.commit();
        
        
    }
}

