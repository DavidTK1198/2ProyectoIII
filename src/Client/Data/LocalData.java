/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client.Data;

import Client.Logic.Profile;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * 
 * @author DavidTK1198
 */
public class LocalData {
    
    private List<Profile> listaPerfiles;

    public LocalData(List<Profile> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }
    public LocalData(){
        this.listaPerfiles = new ArrayList<>();
    }
    
    public void agregarPerfiles(Profile p)throws Exception{
        try{
            if(this.buscarPerfil(p.getId())!= null){
                this.listaPerfiles.add(p);
            }
            else{
                throw new Exception();
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al agregar perfil, ya existe");
            return;
        }
        
    }
    public Profile buscarPerfil(String id){
        Profile perfilito = null;
        for(int i=0; i<listaPerfiles.size();i++){
            perfilito = listaPerfiles.get(i);
            if(perfilito.getId().equals(id)){
                return perfilito;
            }
        }
        return perfilito;
    }
   
  
}

