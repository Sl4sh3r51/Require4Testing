package testCaseTestRun;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("testCaseTestRunController")
@SessionScoped
public class TestCaseTestRunController implements Serializable {

    @Inject
    TestCaseTestRunService testCaseTestRunService;

    List<TestCaseTestRun> testCaseTestRuns;

    TestCaseTestRun testCaseTestRun;

    public TestCaseTestRunController() {}

    public TestCaseTestRunController(TestCaseTestRunService testCaseTestRunService) {
        this.testCaseTestRunService = testCaseTestRunService;
    }

    public List<TestCaseTestRun> getTestCaseTestRuns() {
        return testCaseTestRuns = testCaseTestRunService.getAllTestCaseTestRuns();
    }

    public TestCaseTestRun getTestCaseTestRunById(int testCaseTestRunId) {
        if(!testCaseTestRunService.getAllTestCaseTestRuns().isEmpty()){
            return testCaseTestRun = testCaseTestRunService.getTestCaseTestRunById(testCaseTestRunId);
        }
        else return testCaseTestRuns.get(0);
    }

    public void createTestCaseTestRun(TestCaseTestRun newTestCaseTestRun) {
        testCaseTestRun = new TestCaseTestRun();
        testCaseTestRun.setTestCaseTestRunId(newTestCaseTestRun.getTestCaseTestRunId());
        testCaseTestRun.setTestCase(newTestCaseTestRun.getTestCase());
        testCaseTestRun.setTestRun(newTestCaseTestRun.getTestRun());
        testCaseTestRun.setPassed(newTestCaseTestRun.getPassed());
        testCaseTestRunService.saveTestCaseTestRun(testCaseTestRun);
    }

    public List<TestCaseTestRun> getTestCaseTestRunsByResultStatus(boolean status) {
        if(!testCaseTestRunService.getAllTestCaseTestRuns().isEmpty()){
            return testCaseTestRuns = testCaseTestRunService.getTestCaseTestRunsByResultStatus(status);
        }
        else return testCaseTestRuns;
    }

    public void removeTestCaseTestRun(int testCaseTestRunId) {
        if(!testCaseTestRunService.getAllTestCaseTestRuns().isEmpty()){
            testCaseTestRunService.deleteTestCaseTestRunById(testCaseTestRunId);
        }
    }
}
