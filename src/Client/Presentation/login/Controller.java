/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Presentation.login;

import Client.Application.Session;
import Client.Data.XmlPersister;
import Client.Logic.Chat;
import chatProtocol.Message;
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

    Client.Presentation.mainWindow.Model main_model;
    Client.Presentation.mainWindow.View main_Vista;
    Client.Presentation.mainWindow.Controller main_control;
    Client.Presentation.chat.Controller control_chat;
    Client.Presentation.chat.Model control_Model;
    Client.Presentation.chat.View control_View;
    ServiceProxy localService;

    public Controller(View vista, Model model) {
        this.vista = vista;
        this.model = model;
        vista.setControl(this);
        vista.setModel(model);

        initOptions();

    }

    public void initOptions() {
        this.main_model = new Client.Presentation.mainWindow.Model();
        this.control_Model = new Client.Presentation.chat.Model();
        this.control_View = new Client.Presentation.chat.View(vista, true);
        this.main_Vista = new Client.Presentation.mainWindow.View(this.vista, true);
        this.main_control = new Client.Presentation.mainWindow.Controller(this.main_Vista, this.main_model);
        this.control_chat = new Client.Presentation.chat.Controller(control_View, control_Model);
        this.main_control.setParent(this);
        this.control_chat.setParent(this);
        localService = (ServiceProxy) ServiceProxy.instance();
        localService.setController(this);

    }

    public void MainShow() {

        this.main_control.show();

    }

    public void Chat_Close() {
        this.control_View.hide();
    }

    public void Main_Close() {
        this.main_control.hide();
    }

    public void show() {
        this.vista.setVisible(true);
    }

    public void showChat() {
        this.control_View.show();
    }

    public void Hide() {
        this.vista.setVisible(false);
    }

    public void login(User profi) throws Exception {

        User s = ServiceProxy.instance().login(profi);

        model.setCurrentUser(s);
        try {
            XmlPersister.getInstance().setPath(model.getCurrentUser().getId() + ".xml");
            Session.instance().setSession(XmlPersister.getInstance().restore());

            Session.instance().setAttibute(Session.USER_ATTRIBUTE, s);
            List<Chat> lc = Session.instance().getLC();

            Chat nuevoCh = null;
            for (int i = 0; i < lc.size(); i++) {
                nuevoCh = lc.get(i);
                User nuevoUs = new User();

                nuevoUs.setId(nuevoCh.getIdContacto());
                s.addUser(nuevoUs);
                this.nuevoUsuarioAnadido(nuevoUs);
            }

        } catch (Exception e) {
            Session.instance().setAttibute(Session.USER_ATTRIBUTE, s);
        }
        this.Hide();
        this.MainShow();
        model.commit();
    }

    public void registrarse(User p) throws Exception {
        User registered = ServiceProxy.instance().Registro(p);
        Session.instance().setAttibute(Session.USER_ATTRIBUTE, registered);
        model.setCurrentUser(registered);
        this.Hide();
        this.MainShow();
        model.commit();

    }

    public void nuevoUsuarioAnadido(User P) {
        ServiceProxy.instance().nuevoContactoAnadido(P);

    }

    public void post(Message nuevo) {

        ServiceProxy.instance().post(nuevo);
        model.commit();

    }

    public void logout() {
        try {
            try {

                ServiceProxy.instance().logout(model.getCurrentUser());
            } catch (Exception ex) {
            }

            XmlPersister.getInstance().store(Session.instance());
            model.setCurrentUser(null);

            model.commit();

            Session.instance().removeAttribute(Session.USER_ATTRIBUTE);
            this.Main_Close();
            this.show();
        } catch (Exception ex) {
            System.out.println(ex.getCause());
        }
    }

    public void exit() {
        System.exit(0);
    }

    public User getLoggedUser() {
        return model.getCurrentUser();
    }

    public void deliver(Message message) {

        this.control_chat.deliver(message);
        if (!this.control_View.isShowing()) {
            JOptionPane.showMessageDialog(null, "Mensaje recibido de: " + message.getRemitente());
        }
    }

    public void Update(User u1) {
        User usurio = model.getCurrentUser();
        try {
            User usuria = usurio.getUser(u1.getId());
            usuria.setEstado(true);
            this.main_model.setLista(usurio.getUser());
            this.model.setContactUser(new User());

        } catch (Exception e) {
        }
    }

    public void IsOff(User u1) {
        User usurio = model.getCurrentUser();
        try {
            User usuria = usurio.getUser(u1.getId());
            usuria.setEstado(false);
            this.main_model.setLista(usurio.getUser());
            this.model.setContactUser(new User());

        } catch (Exception e) {
        }
    }

    public void WhoChat(User us) {
        this.control_chat.setCurrentContact(us);
    }

}
