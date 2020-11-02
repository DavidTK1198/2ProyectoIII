/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Presentation.mainWindow;

import Client.Application.Session;
import Client.Logic.Contact;
import Client.Logic.Profile;
import chatProtocol.User;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author DavidTK1198
 */
public class Controller {

    private View vista;
    private Model model;
    private Client.Presentation.login.Controller Parent;
   
    private Refresher refresh;

    public Controller(View vista, Model model) {
        this.vista = vista;
        this.model = model;
        vista.setControl(this);
        vista.setModel(model);
        refresh = new Refresher(this);

    }

    public Client.Presentation.login.Controller getParent() {
        return Parent;
    }

    public void setParent(Client.Presentation.login.Controller Parent) {
        this.Parent = Parent;
    }
    

    public void show() {
        this.preSet();
        vista.setVisible(true);

    }

    public void hide() {
        vista.setVisible(false);
    }

    public void buscarPorID(String ced) {

    }

    String getUser() {

        return model.getProfile().getId();
    }

    String getEstado() {
        return "Online";
    }

    public void preSet() {
        Profile perfil = (Profile) Session.instance().getAttribute(Session.USER_ATTRIBUTE);
        model.setCurrent(perfil);
        List<Contact> list = perfil.getContact();
        model.setContact(new Contact());
        model.setLista(list);
        model.commit();
        refresh.start();
    }

    public void agregarContacto(Contact c) throws Exception {
        Profile perfilito = (Profile) Session.instance().getAttribute(Session.USER_ATTRIBUTE);
        perfilito.addContact(c);
        model.setLista(model.getProfile().getContact());
    }
    
    public void logout(){
        this.Parent.logout();
    }

    public void Update() {

        Profile perfil = (Profile) Session.instance().getAttribute(Session.USER_ATTRIBUTE);
        if (perfil != null) {
            List<Contact> list = perfil.getContact();
            model.setContact(new Contact());
            try {
                List<User> nuva=model.fillUser();
                if(nuva!=null){
                this.Parent.update(model.fillUser());
                this.model.compare(Parent.getUsers());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error con el registro de contactos");
            }
            model.setLista(list);

            model.commit();
        }

    }

    public void whatever(Contact contacto) {
        this.Parent.setCurrentContact(contacto);
        this.Parent.Chat_show();
    }

}




