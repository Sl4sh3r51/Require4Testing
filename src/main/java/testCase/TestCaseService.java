package testCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class TestCaseService {

    @Inject
    private TestCaseDAO testCaseDAO;

    Logger logger = LoggerFactory.getLogger(TestCaseService.class);

    public TestCaseService() {}

    public void saveTestCase(TestCase testCase) {
        if(testCase != null && testCase.getRequirement() != null){
            testCaseDAO.save(testCase);
        }
        else {
            logger.error("Der Testfall darf nicht null sein!");
        }
    }

    public List<TestCase> getAllTestCases() {
        List<TestCase> testCases = testCaseDAO.findAll();
        if(testCases == null || testCases.isEmpty()){
            logger.error("Es wurden keine Testfälle gefunden!");
        }
        return testCases;
    }
}
