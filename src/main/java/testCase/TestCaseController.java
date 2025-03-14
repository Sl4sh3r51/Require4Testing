package testCase;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import requirement.Requirement;
import requirement.RequirementService;
import requirement.RequirementStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named("testCaseController")
@ViewScoped
public class TestCaseController implements Serializable {

    @Inject
    TestCaseService testCaseService;

    @Inject
    RequirementService requirementService;

    TestCase testCase = new TestCase();

    List<TestCase> testCaseList = new ArrayList<>();

    @PostConstruct
    public void init() {
        testCaseList = testCaseService.getAllTestCases();
    }

    public TestCaseController() {}

    public TestCaseController(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
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

    public String createTestCase() {
        if(testCase.getRequirement() != null){
            List<TestCase> testCasesForRequirement = new ArrayList<>();
            testCasesForRequirement.add(testCase);
            Requirement requirement = testCase.getRequirement();
            requirement.getTestCases().addAll(testCasesForRequirement);
            requirement.setRequirementStatus(RequirementStatus.WAITING_FOR_TESTCASE);
            requirement.setModificationDate(LocalDate.now());
            requirementService.updateRequirement(requirement);
        }
        else{
            testCaseService.saveTestCase(testCase);
        }

        testCase = new TestCase();
        testCaseList = testCaseService.getAllTestCases();
        return "testCreator.xhtml?faces-redirect=true";
    }

    public void removeTestCase(int id) {
        if(!testCaseService.getAllTestCases().isEmpty()){
            testCaseService.deleteTestCaseById(id);
        }
    }
}
