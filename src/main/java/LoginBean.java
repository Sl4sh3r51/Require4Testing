import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import user.PasswordHasherUtil;
import user.User;
import user.UserRoles;
import user.UserService;

import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private UserService userService;

    String username;

    String password;

    String errorMessage;

    User user;

    boolean validLogin;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String logout() {
        return "login.xhtml?faces-redirect=true";
    }

    public void ValidateUser(ComponentSystemEvent ev) {
        UIInput temp = (UIInput) ev.getComponent();
        this.username = (String) temp.getValue();
    }

    public void validatePassword(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        String passwordTCheck = (String) value;
        this.user = userService.getUserByUsername(username);
        this.validLogin = PasswordHasherUtil.verifyPassword(passwordTCheck, user.getPassword());
        if(!validLogin){
            throw new ValidatorException(new FacesMessage("Kein Korrekter Login!"));
        }
    }

    public String login() {
        if(validLogin){
            if(user.getUserRole().equals(UserRoles.REQUIREMENT_ENGINEER)){
                return "requirementsEngineer.xhtml?faces-redirect=true";
            } else if (user.getUserRole().equals(UserRoles.TESTER)) {
                return "tester.xhtml?faces-redirect=true";
            } else if (user.getUserRole().equals(UserRoles.TESTMANAGER)) {
                return "testmanager.xhtml?faces-redirect=true";
            } else if (user.getUserRole().equals(UserRoles.TEST_CREATOR)) {
                return "testCreator.xhtml?faces-redirect=true";
            }
        }
        this.errorMessage = "Passwort und Benutzername nicht erkannt.";
        return "";
    }
}
