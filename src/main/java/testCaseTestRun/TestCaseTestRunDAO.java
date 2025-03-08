package testCaseTestRun;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TestCaseTestRunDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public TestCaseTestRunDAO(){
        try {
            entityManager = Persistence.createEntityManagerFactory("require4Testing").createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public TestCaseTestRun findById(int id) {
        try {
            return entityManager.find(TestCaseTestRun.class, id);
        } catch (Exception e) {
            return null;
        }
    }


    public void save(TestCaseTestRun testCaseTestRun) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if((Integer)testCaseTestRun.getTestCaseTestRunId() == null){
                entityManager.persist(testCaseTestRun);
            }
            else{
                entityManager.merge(testCaseTestRun);
            }
            transaction.commit();
        } catch (Exception exception) {
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw exception;
        }
    }

    public List<TestCaseTestRun> findAll() {
        try {
            TypedQuery<TestCaseTestRun> query = entityManager.createQuery("select a from TestCaseTestRun a", TestCaseTestRun.class);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<TestCaseTestRun> findByResultStatus(boolean resultStatus) {
        try {
            TypedQuery<TestCaseTestRun> query = entityManager.createQuery("SELECT a from TestCaseTestRun a where a.passed = :resultStatus", TestCaseTestRun.class);
            query.setParameter("resultStatus", resultStatus);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    public void delete(TestCaseTestRun testCaseTestRun) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if(testCaseTestRun != null) {
                entityManager.remove(testCaseTestRun);
            }
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw e;
        }
    }
}
