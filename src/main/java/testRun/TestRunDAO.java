package testRun;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TestRunDAO {

    @PersistenceContext
    private EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(TestRunDAO.class);

    public TestRunDAO(){
        try {
            entityManager = Persistence.createEntityManagerFactory("require4Testing").createEntityManager();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public void save(TestRun testRun) {
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            if((Integer)testRun.getTestRunId() == null) {
                entityManager.persist(testRun);
            }
            else {
                entityManager.merge(testRun);
            }
            transaction.commit();
        } catch (Exception exception) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }
    }

    public TestRun findById(int id) {
        try{
            return entityManager.find(TestRun.class, id);
        } catch (Exception exception) {
            return null;
        }
    }

    public List<TestRun> findAll() {
        try{
            TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a", TestRun.class);
            return query.getResultList();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }
}
