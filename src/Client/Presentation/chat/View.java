/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Presentation.chat;

import chatProtocol.Message;
import java.awt.TextArea;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextArea;

/**
 *
 * @author DavidTK1198
 */
public class View extends javax.swing.JDialog implements Observer  {

    /**
     * Creates new form View
     */
    
    private Controller control;
    private Model model;

    public View(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
  public void setControl(Controller control) {
        this.control = control;
    }

    public void setModel(Model model) {
        
        this.model = model;
        model.addObserver(this);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AreaTexto = new javax.swing.JTextArea();
        mensaje = new javax.swing.JTextField();
        enviar = new javax.swing.JButton();
        NombreC = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        AreaTexto.setEditable(false);
        AreaTexto.setColumns(20);
        AreaTexto.setRows(5);
        jScrollPane1.setViewportView(AreaTexto);

        mensaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mensajeMouseClicked(evt);
            }
        });

        enviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Icons/icons8-email-send-30.png"))); // NOI18N
        enviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enviarMouseClicked(evt);
            }
        });

        NombreC.setText("Nombre del contacto");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/Icons/icono_chat.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(NombreC))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(enviar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel2)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NombreC)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enviar, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel2)))
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mensajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mensajeMouseClicked
       
        
        
    }//GEN-LAST:event_mensajeMouseClicked

    private void enviarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enviarMouseClicked
        String s = this.mensaje.getText();
        String dest = this.model.getNombrePersona();
        String remi = this.model.getRemitente();
        Message men = new Message(dest,remi,s);
        this.model.getNuevoChat().addMsj(men);
        this.mensaje.setText("");
        model.commit();
       
         
    }//GEN-LAST:event_enviarMouseClicked

    @Override
    public void update(Observable o, Object arg) {
        this.NombreC.setText(model.nombrePersona);
        model.setMessages(model.getNuevoChat().getMensajes());
        this.setTextos();
    }

    /**
     * @param args the command line arguments
     */
    public void setTextos(){
        List<Message> lm = model.getMessages();
        if(lm.isEmpty()){
            return;
        }else{
            String kk = "";
            for(Message lmm : lm){
               kk += lmm.getMensaje() + "\n";
            }
            this.AreaTexto.setText(kk);
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreaTexto;
    private javax.swing.JLabel NombreC;
    private javax.swing.JButton enviar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mensaje;
    // End of variables declaration//GEN-END:variables
}

