package testCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class TestCaseDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    public TestCaseDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public TestCaseDAO(){}

    public void save(TestCase testCase) {
        if((Integer) testCase.getTestCaseId() == null){
            entityManager.persist(testCase);
        }
        else{
            entityManager.merge(testCase);
        }
    }

    public TestCase findById(Long testCaseId) {
        return entityManager.find(TestCase.class, testCaseId);
    }

    public List<TestCase> findAll() {
        TypedQuery<TestCase> query = entityManager.createQuery("SELECT a FROM TestCase a", TestCase.class);
        return query.getResultList();
    }

    public void delete(Long id) {
        TestCase testCase = findById(id);
        if(testCase != null) {
            entityManager.remove(testCase);
        }
    }
}
