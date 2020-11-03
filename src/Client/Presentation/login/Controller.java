/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Presentation.login;

import Client.Application.Session;
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

    Client.Presentation.mainWindow.Model main_model;
    Client.Presentation.mainWindow.View main_Vista;
    Client.Presentation.mainWindow.Controller main_control;
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
        this.main_Vista = new Client.Presentation.mainWindow.View(this.vista, true);
        this.main_control = new Client.Presentation.mainWindow.Controller(this.main_Vista, this.main_model);
        this.main_control.setParent(this);
        localService = (ServiceProxy) ServiceProxy.instance();
        localService.setController(this);

    }

    public void MainShow() {

        this.main_control.show();

    }

    public void Chat_Close() {

    }

    public void Main_Close() {
        this.main_control.hide();
    }

    public void show() {
        this.vista.setVisible(true);
    }

    public void Hide() {
        this.vista.setVisible(false);
    }

    public void login(User profi) throws Exception {

        User s=ServiceProxy.instance().login(profi);
        Session.instance().setAttibute(Session.USER_ATTRIBUTE, s);
        model.setCurrentUser(s);
        this.Hide();
        this.MainShow();
        model.commit();
    }
    public void registrarse(User p)throws Exception{
        User registered = ServiceProxy.instance().Registro(p);
        Session.instance().setAttibute(Session.USER_ATTRIBUTE, registered);
        model.setCurrentUser(registered);
        this.Hide();
        this.MainShow();
        model.commit();
        
    }
    public void nuevoUsuarioAnadido(User P){
        ServiceProxy.instance().nuevoContactoAnadido(P);
        
    }

    public void post(Message nuevo) {

        ServiceProxy.instance().post(nuevo);
        model.commit();
        
    }
    
    public void logout() {
        try {
            ServiceProxy.instance().logout(model.getCurrentUser());
        } catch (Exception ex) {
        }
        model.setCurrentUser(null);
        model.commit();
        Session.instance().removeAttribute(Session.USER_ATTRIBUTE);
        this.Main_Close();
        this.show();
    }

    public void exit() {
        System.exit(0);
    }
    
   public User getLoggedUser(){
        return model.getCurrentUser();
    }
    public void deliver(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Update(User u1) {
        User usurio = model.getCurrentUser();
        try{
            User usuria= usurio.getUser(u1.getId());
            usuria.setEstado(true);
            this.main_model.setLista(usurio.getUser());
            this.model.setContactUser(new User());
          
        }catch(Exception e){}
    }
    
    public void IsOff(User u1) {
        User usurio = model.getCurrentUser();
        try{
            User usuria= usurio.getUser(u1.getId());
            usuria.setEstado(false);
            this.main_model.setLista(usurio.getUser());
            this.model.setContactUser(new User());
          
        }catch(Exception e){}
    }

   

}



















