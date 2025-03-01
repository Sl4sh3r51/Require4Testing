package testRun;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import user.User;

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

    public List<TestRun> sortByRunNumberAscending() {
        TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a ORDER BY a.runNumber ASC ", TestRun.class);
        return query.getResultList();
    }

    public List<TestRun> sortByExecutionTimeAscending() {
        TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a ORDER BY a.executionTime ASC ", TestRun.class);
        return query.getResultList();
    }

    public List<TestRun> sortByExecutionDateAscending() {
        TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a ORDER BY a.executionDate ASC ", TestRun.class);
        return query.getResultList();
    }

    public List<TestRun> sortByRunNumberDescending() {
        TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a ORDER BY a.runNumber desc ", TestRun.class);
        return query.getResultList();
    }

    public List<TestRun> sortByExecutionTimeDescending() {
        TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a ORDER BY a.executionTime desc ", TestRun.class);
        return query.getResultList();
    }

    public List<TestRun> sortByExecutionDateDescending() {
        TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a ORDER BY a.executionDate desc ", TestRun.class);
        return query.getResultList();
    }

    public List<TestRun> findByTester(User tester) {
        TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a where a.tester = :tester ", TestRun.class);
        query.setParameter("tester", tester);
        return query.getResultList();
    }

    public void delete(TestRun testRun) {
        if(testRun != null) {
            entityManager.remove(testRun);
        }
    }
}
