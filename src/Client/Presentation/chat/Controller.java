/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client.Presentation.chat;

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

    
    
}
