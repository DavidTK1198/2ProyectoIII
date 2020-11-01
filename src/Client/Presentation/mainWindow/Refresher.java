/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client.Presentation.mainWindow;

import javax.swing.SwingUtilities;

/**
 * 
 * @author DavidTK1198
 */
public class Refresher {
    
     Controller controller;
  

    public Refresher(Controller controller) {
        this.controller = controller;
    }

    private Thread hilo;
    private boolean condition = false;

    public void start() {
        Runnable tarea = new Runnable() {
            public void run() {
                while (condition) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                    }
                    update();
                }
            }
        };
        hilo = new Thread(tarea);
        condition = true;
        hilo.start();
    }

    public void stop() {
        condition = false;
    }

    private void update() {
        SwingUtilities.invokeLater(
                new Runnable() {
            public void run() {
                controller.Update();
            }
        }
        );
    }


}
