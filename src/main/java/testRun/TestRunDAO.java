package testRun;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class TestRunDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    public TestRunDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public TestRunDAO(){}

    public void save(TestRun testRun) {
        if((Integer)testRun.getTestRunId() == null) {
            entityManager.persist(testRun);
        }
        else {
            entityManager.merge(testRun);
        }
    }

    public TestRun findById(Long id) {
        return entityManager.find(TestRun.class, id);
    }

    public List<TestRun> findAll() {
        TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a", TestRun.class);
        return query.getResultList();
    }

    public void delete(Long testRunId) {
        TestRun testRun = findById(testRunId);
        if(testRun != null) {
            entityManager.remove(testRun);
        }
    }
}
