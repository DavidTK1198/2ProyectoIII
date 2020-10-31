package Client.Application;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DavidTK1198
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Client.Presentation.login.Model login_Moddel;
        Client.Presentation.login.View login_View;
        Client.Presentation.login.Controller login_Controller;
    
        login_Moddel =  new Client.Presentation.login.Model();
        login_View = new Client.Presentation.login.View();
        login_Controller = new Client.Presentation.login.Controller(login_View,login_Moddel);
        login_Controller.show();
    }
    
}








