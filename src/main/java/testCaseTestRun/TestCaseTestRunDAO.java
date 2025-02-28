package testCaseTestRun;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class TestCaseTestRunDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    public TestCaseTestRunDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public TestCaseTestRunDAO(){}

    public TestCaseTestRun findById(Long id) {
        return entityManager.find(TestCaseTestRun.class, id);
    }

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

    public void delete(Long id) {
        TestCaseTestRun testCaseTestRun = findById(id);
        if(testCaseTestRun != null) {
            entityManager.remove(testCaseTestRun);
        }
    }
}
