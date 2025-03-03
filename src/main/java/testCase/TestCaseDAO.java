package testCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TestCaseDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public TestCaseDAO(){}

    @Transactional
    public void save(TestCase testCase) {
        if((Integer) testCase.getTestCaseId() == null){
            entityManager.persist(testCase);
        }
        else{
            entityManager.merge(testCase);
        }
    }

    public TestCase findById(int testCaseId) {
        return entityManager.find(TestCase.class, testCaseId);
    }

    public List<TestCase> findAll() {
        TypedQuery<TestCase> query = entityManager.createQuery("SELECT a FROM TestCase a", TestCase.class);
        return query.getResultList();
    }

    @Transactional
    public void delete(TestCase testCase) {
        if(testCase != null) {
            entityManager.remove(testCase);
        }
    }
}
