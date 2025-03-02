package testCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class TestCaseService {

    private final TestCaseDAO testCaseDAO;

    Logger logger = LoggerFactory.getLogger(TestCaseService.class);

    @Inject
    public TestCaseService(TestCaseDAO testCaseDAO) {
        this.testCaseDAO = testCaseDAO;
    }

    public void saveTestCase(TestCase testCase) {
        if(testCase != null){
            testCaseDAO.save(testCase);
        }
        else {
            logger.error("Der Testfall darf nicht null sein!");
        }
    }

    public TestCase getTestCaseById(int id) {
        TestCase testCase = testCaseDAO.findById(id);
        if(testCase == null){
            logger.error("Es wurde kein Testfall mit der Id: " + id + " gefunden!");
        }
        return testCase;
    }

    public List<TestCase> getAllTestCases() {
        List<TestCase> testCases = testCaseDAO.findAll();
        if(testCases == null || testCases.isEmpty()){
            logger.error("Es wurden keine Testfälle gefunden!");
        }
        return testCases;
    }

    public void deleteTestCaseById(int id) {
        TestCase testCase = testCaseDAO.findById(id);
        if(testCase != null){
            testCaseDAO.delete(testCase);
        }
        else {
            logger.trace("Es gibt kein Testfall mit der Id: " + id + " was gelöscht werden kann!");
        }
    }
}
