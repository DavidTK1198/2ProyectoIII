/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Presentation.mainWindow;

import chatProtocol.User;

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

    }

    public void preSet() {
        User perfilito = this.Parent.getLoggedUser();
        model.setLista(perfilito.getUser());
        model.commit();
    }

    public void agregarContacto(User c) throws Exception {
        User perfilito = this.Parent.getLoggedUser();
        perfilito.addUser(c);
        model.setLista(perfilito.getUser());
    }

    public void logout() {
        this.Parent.logout();
    }

    public void Update() {

    }

}


