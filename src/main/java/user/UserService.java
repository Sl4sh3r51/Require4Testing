package user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    private UserDAO userDAO;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService() {}

    public User getUserById(int id) {
        User user = userDAO.findById(id);
        if (user == null) {
            logger.error("User mit der Id " + id + " wurde nicht gefunden!");
        }
        return user;
    }

    public User getUserByUsername(String username) {
        if (username == null) {
            logger.error("Benutzername darf nicht leer sein!");
            return null;
        } else {
            User foundUser = userDAO.findByUsername(username);
            if (foundUser == null) {
                logger.error("Es wurde kein Benutzer mit dem Name " + username + " gefunden!");
                return null;
            }
            return foundUser;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = userDAO.findAll();
        if (users == null || users.isEmpty()) {
            logger.error("Es wurden keine User gefunden!");
        }
        return users;
    }

    public List<User> getUsersByRole(UserRoles role) {
        List<User> usersByRole = userDAO.getUsersByRole(role);
        if (usersByRole == null || usersByRole.isEmpty()) {
            throw new IllegalArgumentException("Die Rolle eines Users darf nicht null sein!");
        }
        return usersByRole;
    }
}
