package testRun;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import user.User;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TestRunDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public TestRunDAO(){
        try {
            entityManager = Persistence.createEntityManagerFactory("require4Testing").createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
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

    public List<TestRun> sortByRunNumberAscending() {
        try {
            TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a ORDER BY a.runNumber ASC ", TestRun.class);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<TestRun> sortByExecutionTimeAscending() {
        try {
            TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a ORDER BY a.executionTime ASC ", TestRun.class);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<TestRun> sortByExecutionDateAscending() {
        try {
            TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a ORDER BY a.executionDate ASC ", TestRun.class);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<TestRun> sortByRunNumberDescending() {
        try {
            TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a ORDER BY a.runNumber desc ", TestRun.class);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<TestRun> sortByExecutionTimeDescending() {
        try {
            TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a ORDER BY a.executionTime desc ", TestRun.class);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<TestRun> sortByExecutionDateDescending() {
        try {
            TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a ORDER BY a.executionDate desc ", TestRun.class);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<TestRun> findByTester(User tester) {
        try {
            TypedQuery<TestRun> query = entityManager.createQuery("select a from TestRun a where a.tester = :tester ", TestRun.class);
            query.setParameter("tester", tester);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    public void delete(TestRun testRun) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if(testRun != null) {
                entityManager.remove(testRun);
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
