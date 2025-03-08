package user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDAO() {
        try {
            entityManager = Persistence.createEntityManagerFactory("require4Testing").createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public void save(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if ((Integer) user.getUserId() == null) {
                entityManager.persist(user);
            } else {
                entityManager.merge(user);
            }
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }


    }

    public User findById(int id) {
        try {
            return entityManager.find(User.class, id);

        } catch (Exception e) {
            return null;
        }
    }

    public List<User> findAll() {
        try {
            TypedQuery<User> query = entityManager.createQuery("select a from User a", User.class);
            return query.getResultList();

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<User> getUsersByRole(UserRoles role) {
        try {
            TypedQuery<User> query = entityManager.createQuery("select a from User a WHERE a.userRole = :role", User.class);
            query.setParameter("role", role);
            return query.getResultList();

        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

    public User findByUsername(String username) {
        try {
            TypedQuery<User> query = entityManager.createQuery("select a from User a WHERE a.username = :username", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    public void delete(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (user != null) {
                entityManager.remove(user);
            }
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }

    }
}
