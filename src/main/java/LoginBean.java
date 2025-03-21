import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import user.LoggedInUser;
import user.User;
import user.UserRoles;
import user.UserService;
import utils.PasswordHasherUtil;

import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    private LoggedInUser loggedInUser;

    Logger logger = LoggerFactory.getLogger(LoginBean.class);

    String username = "";

    String password = "";

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
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        if(session != null) {
            session.invalidate();
        }
        this.username = "";
        this.password = "";
        this.errorMessage = "";
        loggedInUser.setLoggedUser(null);
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
            logger.warn("Invalid username or password");
        }
    }

    /**
     * Check if login is valid from value validLogin
     * Depending on UserRole redirect User to correct page
     * Otherwise redirect to login page and show errorMessage
     */
    public String login() {
        if(validLogin){
            if(user.getUserRole().equals(UserRoles.REQUIREMENT_ENGINEER)){
                this.errorMessage = "";
                loggedInUser.setLoggedUser(user);
                return "requirementsEngineer.xhtml?faces-redirect=true";
            } else if (user.getUserRole().equals(UserRoles.TESTER)) {
                this.errorMessage = "";
                loggedInUser.setLoggedUser(user);
                return "tester.xhtml?faces-redirect=true";
            } else if (user.getUserRole().equals(UserRoles.TESTMANAGER)) {
                this.errorMessage = "";
                loggedInUser.setLoggedUser(user);
                return "testmanager.xhtml?faces-redirect=true";
            } else if (user.getUserRole().equals(UserRoles.TEST_CREATOR)) {
                this.errorMessage = "";
                loggedInUser.setLoggedUser(user);
                return "testCreator.xhtml?faces-redirect=true";
            }
        }
        this.errorMessage = "Passwort und Benutzername nicht erkannt.";
        this.password = "";
        return "login.xhtml?faces-redirect=true";
    }
}
