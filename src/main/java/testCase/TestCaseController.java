package testCase;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("testCaseController")
@SessionScoped
public class TestCaseController implements Serializable {

    @Inject
    TestCaseService testCaseService;

    TestCase testCase;

    List<TestCase> testCaseList;

    public TestCaseController() {}

    public TestCaseController(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    public List<TestCase> getAllTestCases() {
        return testCaseList = testCaseService.getAllTestCases();
    }

    public TestCase getTestCaseById(int id) {
        if(!testCaseService.getAllTestCases().isEmpty()){
            return testCase = testCaseService.getTestCaseById(id);
        }
        else return testCaseList.get(0);
    }

    public void createTestCase(TestCase newTestCase) {
        testCase = new TestCase();
        testCase.setTestCaseId(newTestCase.getTestCaseId());
        testCase.setDescription(newTestCase.getDescription());
        testCase.setRequirement(newTestCase.getRequirement());
        testCaseService.saveTestCase(testCase);
    }

    public void removeTestCase(int id) {
        if(!testCaseService.getAllTestCases().isEmpty()){
            testCaseService.deleteTestCaseById(id);
        }
    }
}
