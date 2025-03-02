package testRun;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import user.User;

import java.io.Serializable;
import java.util.List;

@Named("testRunController")
@SessionScoped
public class TestRunController implements Serializable {

    @Inject
    TestRunService testRunService;

    List<TestRun> testRuns;

    TestRun testRun;

    public TestRunController() {}

    public TestRunController(TestRunService testRunService) {
        this.testRunService = testRunService;
    }

    public List<TestRun> getAllTestRuns() {
        return testRuns = testRunService.getAllTestRuns();
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

    public void createTestRun(TestRun newTestRun) {
        testRun = new TestRun();
        testRun.setTestRunId(newTestRun.getTestRunId());
        testRun.setRunNumber(newTestRun.getRunNumber());
        testRun.setExecutionTime(newTestRun.getExecutionTime());
        testRun.setExecutionDate(newTestRun.getExecutionDate());
        testRun.setTester(newTestRun.getTester());
        testRunService.saveTestRun(testRun);
    }

    public void removeTestRun(int id) {
        if(!testRunService.getAllTestRuns().isEmpty()){
            testRunService.deleteTestRunById(id);
        }
    }


}
