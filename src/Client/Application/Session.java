/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client.Application;

import Client.Logic.Chat;
import chatProtocol.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author DavidTK1198
 */
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Session {
     @XmlTransient
   private static Session theInstance;
  
   private List<Chat> lc;
    public static Session instance(){
        if (theInstance==null){ 
            theInstance=new Session();
        }
        return theInstance;
    }    
      @XmlTransient
    HashMap<String,Object> attributes;

    public Session() {
        attributes = new HashMap();
        lc = new ArrayList<>();
    }
    
    public Object getAttribute(String attribute){
        return attributes.get(attribute);
    }
    
    public void setAttibute(String attribute, Object value){
    attributes.put(attribute, value);
    }
    
    public void removeAttribute(String attribute){
        attributes.remove(attribute);
    }
    public void addChats(Chat c){
        lc.add(c);
    }
    public Chat buscarChat(String s){//Busca chat con la otra persona
        for(Chat c: lc){
            if(c.getIdContacto().equals(s)){
                return c;
            }
        }
        return null;
    }

    public List<Chat> getLC(){
        return this.lc;
    }
    public void setSession(Session s){
        theInstance = s;
    }
     @XmlTransient
    public static String USER_ATTRIBUTE="user";
     
}








