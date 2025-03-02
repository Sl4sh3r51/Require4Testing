package requirement;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Typed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class RequirementDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    public RequirementDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public RequirementDAO(){}

    public void save(Requirement requirement) {
        if ((Integer)requirement.getRequirementId() == null) {
            entityManager.persist(requirement);
        } else {
            entityManager.merge(requirement);
        }
    }

    public Requirement findById(int id) {
        return entityManager.find(Requirement.class, id);
    }

    public List<Requirement> findAll() {
        TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a", Requirement.class);
        return query.getResultList();
    }

    public List<Requirement> findByRequirementStatus(RequirementStatus status) {
        TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a WHERE a.requirementStatus = :status", Requirement.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    public List<Requirement> sortByVersionAscending(){
        TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.version asc", Requirement.class);
        return query.getResultList();
    }

    public List<Requirement> sortByVersionDescending(){
        TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.version desc", Requirement.class);
        return query.getResultList();
    }

    public List<Requirement> sortByCreationDateAscending(){
        TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.creationDate asc", Requirement.class);
        return query.getResultList();
    }

    public List<Requirement> sortByCreationDateDescending(){
        TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.creationDate desc", Requirement.class);
        return query.getResultList();
    }

    public List<Requirement> sortByModificationDateAscending(){
        TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.modificationDate asc", Requirement.class);
        return query.getResultList();
    }

    public List<Requirement> sortByModificationDateDescending(){
        TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.modificationDate desc", Requirement.class);
        return query.getResultList();
    }

    public List<Requirement> sortByPriorityAscending(){
        TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.priority asc", Requirement.class);
        return query.getResultList();
    }

    public List<Requirement> sortByPriorityDescending(){
        TypedQuery<Requirement> query = entityManager.createQuery("SELECT a FROM Requirement a ORDER BY a.priority desc", Requirement.class);
        return query.getResultList();
    }

    public void delete(Requirement requirement) {
        if(requirement != null) {
            entityManager.remove(requirement);
        }
    }
}
