package requirement;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class RequirementService {

    private final RequirementDAO requirementDAO;

    Logger logger = LoggerFactory.getLogger(RequirementService.class);

    @Inject
    public RequirementService(RequirementDAO requirementDAO) {
        this.requirementDAO = requirementDAO;
    }

    public void saveRequirement(Requirement requirement) {
        if(requirement != null){
            requirementDAO.save(requirement);
        }
        else logger.error("Das Objekt: " + requirement + " darf nicht leer sein!");
    }

    public Requirement getRequirementById(Long id) {
        Requirement requirement = requirementDAO.findById(id);
        if(requirement == null){
            logger.error("Es gibt kein Objekt mit der Id: " + id + " !");
        }
        return requirement;
    }

    public List<Requirement> getAllRequirements() {
        List<Requirement> requirements = requirementDAO.findAll();
        if(requirements == null){
            logger.error("Es wurden keine Anforderungen gefunden!");
        }
        return requirements;
    }

    public List<Requirement> getRequirementsByStatus(RequirementStatus status) {
        if(status == null){
            throw new IllegalArgumentException("Status darf nicht null sein!");
        }
        return requirementDAO.findByRequirementStatus(status);
    }

    public void deleteRequirementById(Long id) {
        Requirement requirement = requirementDAO.findById(id);
        if(requirement != null){
            requirementDAO.delete(requirement);
        }
        else {
            logger.trace("Es gibt kein Objekt mit der Id: " + id + " was gelöscht werden kann!");
        }
    }
}
