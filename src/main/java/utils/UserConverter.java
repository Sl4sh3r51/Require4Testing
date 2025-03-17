package utils;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import user.User;
import user.UserController;

@FacesConverter(value = "userConverter",forClass = User.class)
public class UserConverter implements Converter<User> {

    Logger logger = LoggerFactory.getLogger(TestRunConverter.class);

    @Override
    public String getAsString(FacesContext context, UIComponent component, User user) {
        if (user == null|| (Integer) user.getUserId() == null) {
            return "";
        }
        return String.valueOf(user.getUserId());
    }

    /**
     *
     * @param context {@link FacesContext} for the request being processed
     * @param component {@link UIComponent} with which this model object value is associated
     * @param value String value to be converted (may be <code>null</code>)
     * @return User which will be used in UI
     */
    @Override
    public User getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.isEmpty()){
            return null;
        }
        try{
            UserController userController = context.getApplication().evaluateExpressionGet(context, "#{userController}", UserController.class);
            int userId = Integer.parseInt(value);
            return userController.getUserById(userId);
        } catch (NumberFormatException e){
            logger.error("Ungültige TestRun-Id: " + value,e);
            return null;
        }
    }
}
