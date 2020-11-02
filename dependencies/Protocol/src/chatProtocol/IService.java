package chatProtocol;

import java.util.List;

public interface IService {
    public User login(User u) throws Exception;
    public User Registro(User usuario)throws Exception;
    public void logout(User u) throws Exception; 
    public void post(Message message);
    public void update(List<User> us) throws Exception;
}



