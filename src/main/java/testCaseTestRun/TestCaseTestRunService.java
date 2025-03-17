package testCaseTestRun;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import user.User;

import java.util.List;

@ApplicationScoped
public class TestCaseTestRunService {

    @Inject
    private TestCaseTestRunDAO testCaseTestRunDAO;

    Logger logger = LoggerFactory.getLogger(TestCaseTestRunService.class);

    public TestCaseTestRunService (){}

    public void saveTestCaseTestRun(TestCaseTestRun testCaseTestRun) {
        if(testCaseTestRun != null) {
            testCaseTestRunDAO.save(testCaseTestRun);
        }
        else logger.error("Das Objekt darf nicht null sein!");
    }

    public void updateTestCaseTestRun(TestCaseTestRun testCaseTestRun) {
        if(testCaseTestRun != null) {
            testCaseTestRunDAO.update(testCaseTestRun);
        }
        else {
            logger.error("Es gibt kein Objekt, was aktualisiert werden kann!");
        }
    }

    public List<TestCaseTestRun> getTestCaseTestRunsForTester(User tester) {
        return testCaseTestRunDAO.findByTester(tester);
    }
}
