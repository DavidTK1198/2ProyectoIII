/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Presentation.mainWindow;


import chatProtocol.User;
import java.util.List;

/**
 *
 * @author DavidTK1198
 */
public class Controller {

    private View vista;
    private Model model;
   

  

 
    public Controller(View vista, Model model) {
        this.vista = vista;
        this.model = model;
        vista.setControl(this);
        vista.setModel(model);

    }

    public void show() {
        vista.setVisible(true);
    }

    public void hide() {
        vista.setVisible(false);
    }

    public void buscarPorID(String ced) {

    }

    public void cargarTodosLosUsuarios() {
        List<User> lp = null;
        model.setLista(lp);
    }
    
    public void preSet(){
        List<User> list= null;
        model.setUser(new User());        
        model.setLista(list);        
        model.commit();
    }
    
   

}
