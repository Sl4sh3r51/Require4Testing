package utils;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import requirement.Requirement;
import requirement.RequirementController;

import java.util.List;

@FacesConverter(value = "requirementConverter")
public class RequirementConverter implements Converter<Requirement> {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Requirement requirement) {
        if(requirement == null){
            return "";
        }
        return String.valueOf(requirement.getRequirementId());
    }

    @Override
    public Requirement getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        RequirementController requirementController = context.getApplication().evaluateExpressionGet(context, "#{requirementController}", RequirementController.class);

        List<Requirement> requirementsList = requirementController.getRequirements();
        return requirementsList.stream().filter(requirement -> String.valueOf(requirement.getRequirementId()).equals(value)).findFirst().orElse(null);
    }
}
