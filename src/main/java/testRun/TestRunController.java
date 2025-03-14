package testRun;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import testCase.TestCase;
import user.User;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named("testRunController")
@SessionScoped
public class TestRunController implements Serializable {

    @Inject
    TestRunService testRunService;

    List<TestRun> testRuns = new ArrayList<>();

    List<TestCase> selectedTestCases = new ArrayList<>();

    TestRun testRun = new TestRun();

    int hours;

    int minutes;

    public TestRunController() {}

    public TestRunController(TestRunService testRunService) {
        this.testRunService = testRunService;
    }

    public List<TestRun> getAllTestRuns() {
        return testRuns = testRunService.getAllTestRuns();
    }

    public TestRun getTestRun() {
        return testRun;
    }

    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public List<TestCase> getSelectedTestCases() {
        return selectedTestCases;
    }

    public void setSelectedTestCases(List<TestCase> selectedTestCases) {
        this.selectedTestCases = selectedTestCases;
    }

    public TestRun getTestRunById(int id) {
        if(!testRunService.getAllTestRuns().isEmpty()){
            return testRun = testRunService.getTestRunById(id);
        }
        else return testRuns.get(0);
    }

    public List<TestRun> getTestRunsByTester(User tester) {
        if(!testRunService.getAllTestRuns().isEmpty()){
            return testRuns = testRunService.getTestRunsByTester(tester);
        }
        else return testRuns;
    }

    public List<TestRun> sortTestRunsByExecutionDate(boolean ascending) {
        if(!testRunService.getAllTestRuns().isEmpty()){
            return testRunService.sortByExecutionDate(ascending);
        }
        else return testRuns;
    }

    public List<TestRun> sortTestRunsByExecutionTime(boolean ascending) {
        if(!testRunService.getAllTestRuns().isEmpty()){
            return testRunService.sortByExecutionTime(ascending);
        }
        else return testRuns;
    }

    public List<TestRun> sortTestRunsByRunNumber(boolean ascending) {
        if(!testRunService.getAllTestRuns().isEmpty()){
            return testRunService.sortByRunNumber(ascending);
        }
        else return testRuns;
    }

    public void createTestRun() {
        testRun.setExecutionDate(LocalDate.now());
        testRun.setExecutionTime(Duration.ZERO);

        testRunService.saveTestRun(testRun);
    }

    public void removeTestRun(int id) {
        if(!testRunService.getAllTestRuns().isEmpty()){
            testRunService.deleteTestRunById(id);
        }
    }


}
