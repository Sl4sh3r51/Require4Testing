package testRun;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class TestRunService {

    @Inject
    private TestRunDAO testRunDAO;

    Logger logger = LoggerFactory.getLogger(TestRunService.class);

    public TestRunService() {}

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
}
