package utils;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testRun.TestRun;
import testRun.TestRunController;

@FacesConverter(value = "testRunConverter",forClass = TestRun.class)
public class TestRunConverter implements Converter<TestRun> {

    Logger logger = LoggerFactory.getLogger(TestRunConverter.class);

    @Override
    public String getAsString(FacesContext context, UIComponent component, TestRun testRun) {
        if (testRun == null|| (Integer) testRun.getTestRunId() == null) {
            return "";
        }
        return String.valueOf(testRun.getTestRunId());
    }

    @Override
    public TestRun getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.isEmpty()){
            return null;
        }
        try{
            TestRunController testRunController = context.getApplication().evaluateExpressionGet(context, "#{testRunController}", TestRunController.class);
            int testRunId = Integer.parseInt(value);
            return testRunController.getTestRunById(testRunId);
        } catch (NumberFormatException e){
            logger.error("Ungültige TestRun-Id: " + value,e);
            return null;
        }
    }
}
