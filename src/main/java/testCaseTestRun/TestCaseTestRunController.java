package testCaseTestRun;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import requirement.RequirementStatus;
import testCase.TestCase;
import testRun.TestRunController;
import user.LoggedInUser;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("testCaseTestRunController")
@SessionScoped
public class TestCaseTestRunController implements Serializable {

    @Inject
    TestCaseTestRunService testCaseTestRunService;

    @Inject
    LoggedInUser loggedUser;

    List<TestCaseTestRun> assignedTestCasesTestRuns = new ArrayList<>();

    List<TestCase> selectedTestCases = new ArrayList<>();

    @Inject
    TestRunController testRunController;

    TestCaseTestRun testCaseTestRun = new TestCaseTestRun();

    private Map<Long, Integer> tempHours = new HashMap<>();
    private Map<Long, Integer> tempMinutes = new HashMap<>();
    private Map<Long, Integer> tempSeconds = new HashMap<>();

    @PostConstruct
    public void init() {
        assignedTestCasesTestRuns = testCaseTestRunService.getTestCaseTestRunsForTester(loggedUser.getLoggedUser());
    }

    public TestCaseTestRunController() {}

    public List<TestCaseTestRun> getAssignedTestCasesTestRuns() {
        return assignedTestCasesTestRuns = testCaseTestRunService.getTestCaseTestRunsForTester(loggedUser.getLoggedUser());
    }

    public List<TestCase> getSelectedTestCases() {
        return selectedTestCases;
    }

    public void setSelectedTestCases(List<TestCase> selectedTestCases) {
        this.selectedTestCases = selectedTestCases;
    }

    public Map<Long, Integer> getTempHours() {
        return tempHours;
    }

    public void setTempHours(Map<Long, Integer> tempHours) {
        this.tempHours = tempHours;
    }

    public Map<Long, Integer> getTempMinutes() {
        return tempMinutes;
    }

    public void setTempMinutes(Map<Long, Integer> tempMinutes) {
        this.tempMinutes = tempMinutes;
    }

    public Map<Long, Integer> getTempSeconds() {
        return tempSeconds;
    }

    public void setTempSeconds(Map<Long, Integer> tempSeconds) {
        this.tempSeconds = tempSeconds;
    }

    public void saveResult(TestCaseTestRun testCaseTestRun) {
        int testCaseId = testCaseTestRun.getTestCase().getTestCaseId();
        int hours = parseIntOrDefault(tempHours.get(testCaseId), 0);
        int minutes = parseIntOrDefault(tempMinutes.get(testCaseId), 0);
        int seconds = parseIntOrDefault(tempSeconds.get(testCaseId), 0);

        testCaseTestRun.getTestRun().setExecutionDate(LocalDate.now());
        testCaseTestRun.getTestRun().setExecutionTime(Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds));
        if(testCaseTestRun.getPassed()){
            testCaseTestRun.getTestCase().getRequirement().setRequirementStatus(RequirementStatus.DONE);
            testCaseTestRun.getTestCase().getRequirement().setModificationDate(LocalDate.now());
        }
        else{
            testCaseTestRun.getTestCase().getRequirement().setRequirementStatus(RequirementStatus.WAITING_FOR_TESTCASE);
            testCaseTestRun.getTestCase().getRequirement().setModificationDate(LocalDate.now());
        }
        testCaseTestRunService.updateTestCaseTestRun(testCaseTestRun);
        // Werte nach dem Speichern zurücksetzen
        tempHours.remove(testCaseId);
        tempMinutes.remove(testCaseId);
        tempSeconds.remove(testCaseId);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Zugewiesener Testfall wurde aktualisiert."));
    }

    public void createTestCaseTestRun() {
        if (selectedTestCases == null || selectedTestCases.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Warnung", "Keine Testfälle ausgewählt!"));
            return;
        }
        for(TestCase testCaseToAdd: selectedTestCases){
            testCaseTestRun = new TestCaseTestRun();

            testCaseTestRun.setTestCase(testCaseToAdd);
            testCaseTestRun.setTestRun(testRunController.getTestRun());
            testCaseTestRunService.saveTestCaseTestRun(testCaseTestRun);
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Testfälle wurden erfolgreich zugewiesen."));
        selectedTestCases.clear();
    }

    private int parseIntOrDefault(Object value, int defaultValue) {
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }
}
