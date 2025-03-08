package testCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TestCaseDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public TestCaseDAO(){
        try{
            entityManager = Persistence.createEntityManagerFactory("require4Testing").createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
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
            transaction.commit();
        } catch (Exception exception) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }
    }
}
