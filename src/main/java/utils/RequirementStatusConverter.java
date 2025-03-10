package utils;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import requirement.RequirementStatus;

@FacesConverter(value = "requirementConverter")
public class RequirementStatusConverter implements Converter<RequirementStatus> {


    @Override
    public String getAsString(FacesContext context, UIComponent component, RequirementStatus requirementStatus) {
        if(requirementStatus != null) {
            return requirementStatus.toString();
        }
        else {
            return "";
        }
    }

    @Override
    public RequirementStatus getAsObject(FacesContext context, UIComponent component, String value) {
        return RequirementStatus.valueOf(value);
    }
}
