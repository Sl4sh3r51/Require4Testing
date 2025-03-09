package requirement;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RequirementDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public RequirementDAO() {
        try {
            entityManager = Persistence.createEntityManagerFactory("require4Testing").createEntityManager();
        } catch (Exception exception) {
            exception.printStackTrace();
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

    public List<Requirement> findByRequirementStatus(RequirementStatus status) {
        try {
            TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a WHERE a.requirementStatus = :status", Requirement.class);
            query.setParameter("status", status);
            return query.getResultList();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    public List<Requirement> sortByVersionAscending() {
        try {
            TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.version asc", Requirement.class);
            return query.getResultList();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    public List<Requirement> sortByVersionDescending() {
        try {
            TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.version desc", Requirement.class);
            return query.getResultList();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    public List<Requirement> sortByCreationDateAscending() {
        try {
            TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.creationDate asc", Requirement.class);
            return query.getResultList();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    public List<Requirement> sortByCreationDateDescending() {
        try {
            TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.creationDate desc", Requirement.class);
            return query.getResultList();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    public List<Requirement> sortByModificationDateAscending() {
        try {
            TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.modificationDate asc", Requirement.class);
            return query.getResultList();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    public List<Requirement> sortByModificationDateDescending() {
        try {
            TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.modificationDate desc", Requirement.class);
            return query.getResultList();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    public List<Requirement> sortByPriorityAscending() {
        try {
            TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.priority asc", Requirement.class);
            return query.getResultList();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    public List<Requirement> sortByPriorityDescending() {
        try {
            TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.priority desc", Requirement.class);
            return query.getResultList();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    public void delete(Requirement requirement) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (requirement != null) {
                entityManager.remove(requirement);
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
