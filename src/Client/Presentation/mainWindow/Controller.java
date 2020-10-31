/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Presentation.mainWindow;



import Client.Logic.Contact;
import Client.Logic.Profile;
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

    
    public void preSet(){
        Profile perfil = this.Parent.getProfile();
        List<Contact> list= perfil.getContact();
        model.setContact(new Contact());        
        model.setLista(list);        
        model.commit();
    }
    public void agregarContacto(Contact c)throws Exception{
        Profile perfilito = this.Parent.getProfile();
        perfilito.addContact(c);
    }

}
