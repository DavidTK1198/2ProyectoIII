/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Logic;

import chatProtocol.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DavidTK1198
 */
public class Profile extends User {

    private Map<String, Contact> contacts;
    private Map<String, Chat> chats;

    public Profile() {
        contacts = new HashMap<>();
        chats = new HashMap<>();
    }

    public Profile(String id, String clave) {
        super(id, clave);
        contacts = new HashMap<>();
        chats = new HashMap<>();
    }


    @Override
    public void setClave(String clave) {
        super.setClave(clave);
    }

    @Override
    public String getClave() {
        return super.getClave();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public String getId() {
        return super.getId();
    }

    public List<Chat> getChat() {
        List<Chat> ls = new ArrayList<>();
        for (Map.Entry<String, Chat> entry : chats.entrySet()) {
            Chat ayudante = entry.getValue();
            ls.add(ayudante);
        }
        return ls;
    }

    public void addChat(Chat p) throws Exception {
        Chat c = chats.get(p.getIdContacto());
        if (c == null) {
            chats.put(p.getIdContacto(), p);

        } else {
            throw new Exception("El chat ya existe");
        }
    }

    public List<Contact> getContact() {
        List<Contact> ls = new ArrayList<>();
        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            Contact ayudante = entry.getValue();
            ls.add(ayudante);
        }
        return ls;
    }

    public void addContact(Contact p) throws Exception {
        Contact c = contacts.get(p.getIdC());
        if (c == null) {
            this.contacts.put(p.getIdC(), p);

        } else {
            throw new Exception("El contacto ya existe");
        }
    }

}

