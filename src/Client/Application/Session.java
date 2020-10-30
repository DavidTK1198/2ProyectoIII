/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Application;

import java.util.HashMap;

/**
 * 
 * @author DavidTK1198
 */
public class Session {
   private static Session theInstance;
    public static Session instance(){
        if (theInstance==null){ 
            theInstance=new Session();
        }
        return theInstance;
    }    
    
    HashMap<String,Object> attributes;

    public Session() {
        attributes = new HashMap();
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
    
    public static String USER_ATTRIBUTE="user";
}
