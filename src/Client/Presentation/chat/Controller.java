/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Presentation.chat;

import Client.Application.Session;
import Client.Logic.Chat;
import chatProtocol.Message;
import chatProtocol.User;
import java.util.List;

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
        model.getNuevoChat().addMsj(message);
        model.commit();
    }
    public void setCurrentContact(User c){
        User profi = (User) Session.instance().getAttribute(Session.USER_ATTRIBUTE);
        model.setRemitente(profi.getId());
        List<User> lc = profi.getUser();
        for(int i=0; i<lc.size(); i++){
            if(lc.get(i).getId().equals(c.getId())){
                Chat cc = Session.instance().buscarChat(lc.get(i).getId());
                if(cc == null){
                    cc = new Chat(c.getId());
                    Session.instance().addChats(cc);
                }
                model.setMessages(cc.getMensajes());//falta mensajes
                model.setContacto(c.getId());
                break;
            }
        }
        
        model.commit();
        this.show();
        
    }
}
