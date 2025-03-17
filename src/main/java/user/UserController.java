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

    public List<User> getAllTesters() {
        return testers = userService.getUsersByRole(UserRoles.TESTER);
    }

    public User getUserById(int id) {
        if(!userService.getAllUsers().isEmpty()){
            return user = userService.getUserById(id);
        }
        else return testers.get(0);
    }
}
