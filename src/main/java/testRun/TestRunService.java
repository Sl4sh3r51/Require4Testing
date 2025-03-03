package testRun;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import user.User;

import java.util.List;

@ApplicationScoped
public class TestRunService {

    @Inject
    private TestRunDAO testRunDAO;

    Logger logger = LoggerFactory.getLogger(TestRunService.class);

    public TestRunService() {

    }

    public void saveTestRun(TestRun testRun) {
        if(testRun == null) {
            logger.error("Testlauf darf nicht null sein!");
        }
        else {
            testRunDAO.save(testRun);
        }
    }

    public TestRun getTestRunById(int id) {
        TestRun testRun = testRunDAO.findById(id);
        if(testRun == null) {
            logger.error("Es konnte kein Testlauf mit der Id " + id + " gefunden werden!");
        }
        return testRun;
    }

    public List<TestRun> getAllTestRuns() {
        List<TestRun> testRuns = testRunDAO.findAll();
        if(testRuns == null||testRuns.isEmpty()) {
            logger.error("Es konnten keine Testläufe gefunden werden!");
        }
        return testRuns;
    }

    public List<TestRun> getTestRunsByTester(User tester) {
        if(tester == null) {
            throw new IllegalArgumentException("User darf nicht null sein!");
        }
        return testRunDAO.findByTester(tester);
    }

    public void deleteTestRunById(int id) {
        TestRun testRun = testRunDAO.findById(id);
        if(testRun == null) {
            logger.error("Es gibt keinen Testlauf mit der Id " + id + " was gelöscht werden kann!");
        }
        else{
            testRunDAO.delete(testRun);
        }
    }

    public List<TestRun> sortByExecutionDate(boolean ascending) {
        if(ascending) {
            return testRunDAO.sortByExecutionDateAscending();
        }
        else {
            return testRunDAO.sortByExecutionDateDescending();
        }
    }

    public List<TestRun> sortByRunNumber(boolean ascending) {
        if(ascending) {
            return testRunDAO.sortByRunNumberAscending();
        }
        else {
            return testRunDAO.sortByRunNumberDescending();
        }
    }

    public List<TestRun> sortByExecutionTime(boolean ascending) {
        if(ascending) {
            return testRunDAO.sortByExecutionTimeAscending();
        }
        else {
            return testRunDAO.sortByExecutionTimeDescending();
        }
    }
}
