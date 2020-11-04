package chatProtocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)

public class User implements Serializable {
    @XmlID
    String id;
    String clave;
    private boolean estado;
    private Map<String, User> contacts;
    private String Nombre;

    public User() {
        this.clave = "";
        this.id = "";
        estado = false;
        this.contacts=new HashMap<>();
        this.Nombre="";
    }

    public User(String Nombre, String id, String clave) {
        this.id = id;
        this.clave = clave;
        estado = false;
        this.contacts=new HashMap<>();
    }

    
    public User(String id, String clave) {
        this.id = id;
        this.clave = clave;
        estado = false;
        this.contacts=new HashMap<>();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<User> getUser() {
        List<User> ls = new ArrayList<>();
        for (Map.Entry<String, User> entry : contacts.entrySet()) {
            User ayudante = entry.getValue();
            ls.add(ayudante);
        }
        return ls;
    }

    public void addUser(User p) throws Exception {
        User c = contacts.get(p.getId());
        if (c == null) {
            this.contacts.put(p.getId(), p);

        } else {
            throw new Exception("El contacto ya existe");
        }
    }

    public User getUser(String ps)  {
        User p = this.contacts.get(ps);
        if (p == null) {
            return null;
        }
        return p;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

}






