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


    public TestCaseTestRunService (){

    }

    public void saveTestCaseTestRun(TestCaseTestRun testCaseTestRun) {
        if(testCaseTestRun != null) {
            testCaseTestRunDAO.save(testCaseTestRun);
        }
        else logger.error("Das Objekt darf nicht null sein!");
    }

    public TestCaseTestRun getTestCaseTestRunById(int id) {
        TestCaseTestRun testCaseTestRun = testCaseTestRunDAO.findById(id);
        if(testCaseTestRun == null) {
            logger.error("Es wurde kein Objekt mit der Id: " + id + " gefunden!");
        }
        return testCaseTestRun;
    }

    public List<TestCaseTestRun> getAllTestCaseTestRuns() {
        List<TestCaseTestRun> testCaseTestRuns = testCaseTestRunDAO.findAll();
        if(testCaseTestRuns == null || testCaseTestRuns.isEmpty()) {
            logger.error("Es wurden keine Objekte gefunden!");
        }
        return testCaseTestRuns;
    }

    public List<TestCaseTestRun> getTestCaseTestRunsByResultStatus(boolean status) {
        List<TestCaseTestRun> testCaseTestRuns = testCaseTestRunDAO.findByResultStatus(status);
        if(testCaseTestRuns == null || testCaseTestRuns.isEmpty()) {
            logger.error("Es wurde keine Objekte mit dem Status" + status + " gefunden!");
        }
        return testCaseTestRuns;
    }

    public void deleteTestCaseTestRunById(int id) {
        TestCaseTestRun testCaseTestRun = testCaseTestRunDAO.findById(id);
        if(testCaseTestRun != null) {
            testCaseTestRunDAO.delete(testCaseTestRun);
        }
        else {
            logger.trace("Es gibt kein Objekt mit der Id: " + id + " was gelöscht werden kann!");
        }
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
