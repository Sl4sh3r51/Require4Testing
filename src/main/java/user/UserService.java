package user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class UserService {

    private final UserDAO userDAO;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Inject
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void saveUser(User user) {
        if(user != null){
            userDAO.save(user);
        }
        else {
            logger.error("User ist null!");
        }
    }

    public User getUserById(Long id) {
        User user = userDAO.findById(id);
        if(user == null){
            logger.error("User mit der Id " + id + " wurde nicht gefunden!");
        }
        return user;
    }

    public List<User> getAllUsers(){
        List<User> users = userDAO.findAll();
        if(users == null){
            logger.error("Es wurden keine User gefunden!");
        }
        return users;
    }

    public List<User> getUsersByRole(UserRoles role) {
        if(role == null){
            throw new IllegalArgumentException("Die Rolle eines Users darf nicht null sein!");
        }
        return userDAO.getUsersByRole(role);
    }

    public void deleteUserById(Long id) {
        User user = userDAO.findById(id);
        if(user == null){
            logger.trace("Es gibt kein Objekt mit der Id: " + id + " was gelöscht werden kann!");
        }
        else {
            userDAO.delete(user);
        }
    }
}
