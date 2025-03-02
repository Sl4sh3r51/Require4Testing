package user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UserDAO(){}

    public void save(User user) {
        if((Integer) user.getUserId() == null){
            entityManager.persist(user);
        }
        else{
            entityManager.merge(user);
        }
    }

    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("select a from User a", User.class);
        return query.getResultList();
    }

    public List<User> getUsersByRole(UserRoles role) {
        TypedQuery<User> query = entityManager.createQuery("select a from User a WHERE a.userRole = :role", User.class);
        query.setParameter("role", role);
        return query.getResultList();

    }

    public void delete(User user) {
        if(user != null){
            entityManager.remove(user);
        }
    }
}
