/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Presentation.login;

import Client.Logic.Profile;
import chatProtocol.Message;
import chatProtocol.User;
import java.util.ArrayList;

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
    Client.Presentation.chat.Model Chat_model;
    Client.Presentation.chat.View Chat_Vista;
    Client.Presentation.chat.Controller Chat_control;
    ServiceProxy localService;
    
    public Controller(View vista, Model model) {
        this.vista = vista;
        this.model = model;
        vista.setControl(this);
        vista.setModel(model);
        localService = (ServiceProxy) ServiceProxy.instance();
        localService.setController(this);
        
        initOptions();
        
    }
    
    public Profile getProfile() {
        return model.getPerfil();
    }
    
    public void initOptions() {
        this.main_model = new Client.Presentation.mainWindow.Model();
        this.main_Vista = new Client.Presentation.mainWindow.View(this.vista, true);
        this.main_control = new Client.Presentation.mainWindow.Controller(this.main_Vista, this.main_model);
        this.Chat_model = new Client.Presentation.chat.Model();
        this.Chat_Vista = new Client.Presentation.chat.View(this.vista, true);
        this.Chat_control = new Client.Presentation.chat.Controller(this.Chat_Vista, this.Chat_model);
        this.Chat_control.setParent(this);
        this.main_control.setParent(this);
        
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
    
    public void login(Profile profi) throws Exception {
        
        User nuevoUsuario = new User();
        nuevoUsuario.setClave(profi.getClave());
        nuevoUsuario.setId(profi.getId());
        User logged = ServiceProxy.instance().login(nuevoUsuario);
        model.setCurrentUser(logged);
        model.setPerfil(profi);
        this.Hide();
        this.MainShow();
        
        model.commit();
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
        model.setMessages(new ArrayList<Message>());
        model.commit();
    }
    
    public void deliver(Message message) {
        model.getMessages().add(message);
        model.commit();
    }
    
    public void exit() {
        System.exit(0);
    }
}

