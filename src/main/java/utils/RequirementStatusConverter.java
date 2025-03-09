package utils;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import requirement.RequirementStatus;

@FacesConverter(value = "requirementConverter")
public class RequirementStatusConverter implements Converter {


    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value instanceof RequirementStatus) {
            return value.toString();
        }
        else {
            return "";
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return RequirementStatus.valueOf(value);
    }
}
