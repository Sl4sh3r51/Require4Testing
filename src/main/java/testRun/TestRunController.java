package testRun;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

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

    TestRun testRun = new TestRun();

    public TestRunController() {}

    public List<TestRun> getAllTestRuns() {
        return testRuns = testRunService.getAllTestRuns();
    }

    public TestRun getTestRun() {
        return testRun;
    }

    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }

    public TestRun getTestRunById(int id) {
        if(!testRunService.getAllTestRuns().isEmpty()){
            return testRun = testRunService.getTestRunById(id);
        }
        else return testRuns.get(0);
    }

    public void createTestRun() {
        testRun.setExecutionDate(LocalDate.now());
        testRun.setExecutionTime(Duration.ZERO);

        testRunService.saveTestRun(testRun);
    }

}
