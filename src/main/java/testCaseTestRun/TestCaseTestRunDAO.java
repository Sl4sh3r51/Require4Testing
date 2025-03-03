package testCaseTestRun;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TestCaseTestRunDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public TestCaseTestRunDAO(){}

    public TestCaseTestRun findById(int id) {
        return entityManager.find(TestCaseTestRun.class, id);
    }

    @Transactional
    public void save(TestCaseTestRun testCaseTestRun) {
        if((Integer)testCaseTestRun.getTestCaseTestRunId() == null){
            entityManager.persist(testCaseTestRun);
        }
        else{
            entityManager.merge(testCaseTestRun);
        }
    }

    public List<TestCaseTestRun> findAll() {
        TypedQuery<TestCaseTestRun> query = entityManager.createQuery("select a from TestCaseTestRun a", TestCaseTestRun.class);
        return query.getResultList();
    }

    public List<TestCaseTestRun> findByResultStatus(boolean resultStatus) {
        TypedQuery<TestCaseTestRun> query = entityManager.createQuery("SELECT a from TestCaseTestRun a where a.passed = :resultStatus", TestCaseTestRun.class);
        query.setParameter("resultStatus", resultStatus);
        return query.getResultList();
    }

    @Transactional
    public void delete(TestCaseTestRun testCaseTestRun) {
        if(testCaseTestRun != null) {
            entityManager.remove(testCaseTestRun);
        }
    }
}
