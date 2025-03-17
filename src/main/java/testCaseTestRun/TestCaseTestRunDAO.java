package testCaseTestRun;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import user.User;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TestCaseTestRunDAO {

    @PersistenceContext
    private EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(TestCaseTestRun.class);

    public TestCaseTestRunDAO(){
        try {
            entityManager = Persistence.createEntityManagerFactory("require4Testing").createEntityManager();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
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

    public void update(TestCaseTestRun testCaseTestRun) {
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            if(testCaseTestRun != null) {
                entityManager.merge(testCaseTestRun);
            }
            else {
                logger.error("Es wurde kein Objekt gefunden, was aktualisiert werden kann!");
            }
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<TestCaseTestRun> findByTester(User tester) {
        try {
            TypedQuery<TestCaseTestRun> query = entityManager.createQuery("SELECT t FROM TestCaseTestRun t WHERE t.testRun.tester = :tester", TestCaseTestRun.class);
            query.setParameter("tester", tester);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
