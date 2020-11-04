/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Presentation.mainWindow;

import chatProtocol.User;
import java.util.ArrayList;
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
        this.preSet();
        vista.setVisible(true);

    }

    public void hide() {
        vista.setVisible(false);
    }

    public void buscarPorID(String ced) {
        
        try {
            User p = Parent.getLoggedUser().getUser(ced);
            if (p == null) {
                throw new Exception("El Contacto no existe");
            }
            List<User> lp = new ArrayList<>();
            lp.add(p);
            model.setLista(lp);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        model.setContact(new User());
    }

    public void cargarTodosLosContactos() {
        List<User> lp = Parent.getLoggedUser().getUser();
        model.setLista(lp);
    }

    public void preSet() {
        User perfilito = this.Parent.getLoggedUser();
        model.setLista(perfilito.getUser());
        model.setNombre(perfilito.getId());
        model.commit();
    }

    public void agregarContacto(User c) throws Exception {

        User modelo = Parent.getLoggedUser();

        try {
            if (modelo.getId().equals(c.getId())) {
                throw new Exception("No se permite ingresarse de contacto a si mismo");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
        try {

            if (modelo.getUser(c.getId()) != null) {
                throw new Exception("El contacto ya existe");
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;

        }

        this.Parent.getLoggedUser().addUser(c);
        this.Parent.nuevoUsuarioAnadido(c);
        model.setLista(this.Parent.getLoggedUser().getUser());

    }

    public void logout() {
        this.Parent.logout();

    }

    public void WhoChat(User us) {
        this.Parent.WhoChat(us);
    }

}


