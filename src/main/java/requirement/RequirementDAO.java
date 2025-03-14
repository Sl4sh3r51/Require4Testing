package requirement;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RequirementDAO {

    @PersistenceContext
    private EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(RequirementDAO.class);

    public RequirementDAO() {
        try {
            entityManager = Persistence.createEntityManagerFactory("require4Testing").createEntityManager();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw exception;
        }
    }

    public void save(Requirement requirement) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if ((Integer) requirement.getRequirementId() == null) {
                entityManager.persist(requirement);
            } else {
                entityManager.merge(requirement);
            }
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }
    }

    public Requirement findById(int id) {
        try {
            return entityManager.find(Requirement.class, id);
        } catch (Exception exception) {
            logger.error("Es gibt kein Objekt mit der Id: " + id + " !");
            return null;
        }
    }

    public List<Requirement> findAll() {
        try {
            TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a", Requirement.class);
            return query.getResultList();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    public void update(Requirement requirement) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if(requirement != null) {
                entityManager.merge(requirement);
                transaction.commit();
            }
            else {
                logger.error("Es wurde kein Requirement gefunden, was aktualisiert werden kann!");
            }
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }
    }

    public void delete(Requirement requirement) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (requirement != null) {
                entityManager.remove(requirement);
                transaction.commit();
            }
            else {
                logger.error("Es wurde kein Requirement gefunden, was gelöscht werden kann!");
            }
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }
    }
}
