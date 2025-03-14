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

    List<User> testers;

    User user;

    public UserController() {}

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllTesters() {
        return testers = userService.getUsersByRole(UserRoles.TESTER);
    }

    public User getUserById(int id) {
        if(!userService.getAllUsers().isEmpty()){
            return user = userService.getUserById(id);
        }
        else return testers.get(0);
    }

    public List<User> getUsersByRole(UserRoles role) {
        if(!userService.getAllUsers().isEmpty()){
            return testers = userService.getUsersByRole(role);
        }
        else return testers;
    }

    public void createUser(User newUser) {
        user = new User();
        user.setUserId(newUser.getUserId());
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setUserRole(newUser.getUserRole());
        userService.saveUser(user);
    }

    public void removeUser(int id) {
        if(!userService.getAllUsers().isEmpty()){
            userService.deleteUserById(id);
        }
    }
}
