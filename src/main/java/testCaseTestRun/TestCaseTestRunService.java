package testCaseTestRun;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class TestCaseTestRunService {

    private final TestCaseTestRunDAO testCaseTestRunDAO;

    Logger logger = LoggerFactory.getLogger(TestCaseTestRunService.class);

    @Inject
    public TestCaseTestRunService(TestCaseTestRunDAO testCaseTestRunDAO) {
        this.testCaseTestRunDAO = testCaseTestRunDAO;
    }

    public void saveTestCaseTestRun(TestCaseTestRun testCaseTestRun) {
        if(testCaseTestRun != null) {
            testCaseTestRunDAO.save(testCaseTestRun);
        }
        else logger.error("Das Objekt darf nicht null sein!");
    }

    public TestCaseTestRun getTestCaseTestRunById(Long id) {
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

    public void deleteTestCaseTestRunById(Long id) {
        TestCaseTestRun testCaseTestRun = testCaseTestRunDAO.findById(id);
        if(testCaseTestRun != null) {
            testCaseTestRunDAO.delete(testCaseTestRun);
        }
        else {
            logger.trace("Es gibt kein Objekt mit der Id: " + id + " was gelöscht werden kann!");
        }
    }
}
