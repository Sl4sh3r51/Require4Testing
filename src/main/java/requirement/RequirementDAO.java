package requirement;

import jakarta.enterprise.context.ApplicationScoped;
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

    public Requirement findById(Long id) {
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

    public void delete(Long id) {
        Requirement requirement = findById(id);
        if(requirement != null) {
            entityManager.remove(requirement);
        }
    }
}
