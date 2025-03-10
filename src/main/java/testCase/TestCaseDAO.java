package testCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TestCaseDAO {

    @PersistenceContext
    private EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(TestCaseDAO.class);

    public TestCaseDAO(){
        try{
            entityManager = Persistence.createEntityManagerFactory("require4Testing").createEntityManager();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public void save(TestCase testCase) {
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            if((Integer) testCase.getTestCaseId() == null){
                entityManager.persist(testCase);
            }
            else{
                entityManager.merge(testCase);
            }
            transaction.commit();
        } catch (Exception exception) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }

    }

    public TestCase findById(int testCaseId) {
        try{
            return entityManager.find(TestCase.class, testCaseId);
        } catch (Exception exception) {
            return null;
        }
    }

    public List<TestCase> findAll() {
        try{
            TypedQuery<TestCase> query = entityManager.createQuery("SELECT a FROM TestCase a", TestCase.class);
            return query.getResultList();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }


    public void delete(TestCase testCase) {
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            if(testCase != null) {
                entityManager.remove(testCase);
            }
            else {
                logger.error("Es wurde kein TestCase gefunden, der gelöscht werden kann!");
            }
            transaction.commit();
        } catch (Exception exception) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }
    }
}
