package user;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    @Inject
    UserService userService;

    List<User> users;

    User user;

    public UserController() {}

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllUsers() {
        return users = userService.getAllUsers();
    }

    public User getUserById(int id) {
        if(!userService.getAllUsers().isEmpty()){
            return user = userService.getUserById(id);
        }
        else return users.get(0);
    }

    public List<User> getUsersByRole(UserRoles role) {
        if(!userService.getAllUsers().isEmpty()){
            return users = userService.getUsersByRole(role);
        }
        else return users;
    }

    public void createUser(User newUser) {
        user = new User();
        user.setUserId(newUser.getUserId());
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setUserRole(newUser.getUserRole());
        user.setTestRun(newUser.getTestRun());
        userService.saveUser(user);
    }

    public void removeUser(int id) {
        if(!userService.getAllUsers().isEmpty()){
            userService.deleteUserById(id);
        }
    }
}
