package testCaseTestRun;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import testCase.TestCase;
import testRun.TestRun;
import user.LoggedInUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("testCaseTestRunController")
@SessionScoped
public class TestCaseTestRunController implements Serializable {

    @Inject
    TestCaseTestRunService testCaseTestRunService;

    @Inject
    LoggedInUser loggedUser;

    List<TestCaseTestRun> assignedTestCasesTestRuns = new ArrayList<>();

    TestCaseTestRun testCaseTestRun = new TestCaseTestRun();

    TestCase testCase = new TestCase();

    TestRun testRun = new TestRun();

    @PostConstruct
    public void init() {
        assignedTestCasesTestRuns = testCaseTestRunService.getTestCaseTestRunsForTester(loggedUser.getLoggedUser());
    }

    public TestCaseTestRunController() {}

    public TestCaseTestRunController(TestCaseTestRunService testCaseTestRunService) {
        this.testCaseTestRunService = testCaseTestRunService;
    }

    public List<TestCaseTestRun> getAssignedTestCasesTestRuns() {
        return assignedTestCasesTestRuns = testCaseTestRunService.getTestCaseTestRunsForTester(loggedUser.getLoggedUser());
    }

    public void saveResult(TestCaseTestRun testCaseTestRun) {
        testCaseTestRunService.updateTestCaseTestRun(testCaseTestRun);
    }

    public TestCaseTestRun getTestCaseTestRunById(int testCaseTestRunId) {
        if(!testCaseTestRunService.getAllTestCaseTestRuns().isEmpty()){
            return testCaseTestRun = testCaseTestRunService.getTestCaseTestRunById(testCaseTestRunId);
        }
        else return assignedTestCasesTestRuns.get(0);
    }

    public void createTestCaseTestRun() {
        testCaseTestRun.setTestCase(this.testCase);
        testCaseTestRun.setTestRun(this.testRun);
        testCaseTestRunService.saveTestCaseTestRun(testCaseTestRun);


        testCaseTestRun = new TestCaseTestRun();
    }

    public List<TestCaseTestRun> getTestCaseTestRunsByResultStatus(boolean status) {
        if(!testCaseTestRunService.getAllTestCaseTestRuns().isEmpty()){
            return assignedTestCasesTestRuns = testCaseTestRunService.getTestCaseTestRunsByResultStatus(status);
        }
        else return assignedTestCasesTestRuns;
    }

    public void removeTestCaseTestRun(int testCaseTestRunId) {
        if(!testCaseTestRunService.getAllTestCaseTestRuns().isEmpty()){
            testCaseTestRunService.deleteTestCaseTestRunById(testCaseTestRunId);
        }
    }
}
