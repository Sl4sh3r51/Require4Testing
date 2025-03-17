package requirement;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class RequirementService {

    @Inject
    private RequirementDAO requirementDAO;

    Logger logger = LoggerFactory.getLogger(RequirementService.class);

    public RequirementService() {}

    public void saveRequirement(Requirement requirement) {
        if(requirement != null){
            requirementDAO.save(requirement);
        }
        else logger.error("Das Objekt darf nicht leer sein!");
    }

    public List<Requirement> getAllRequirements() {
        List<Requirement> requirements = requirementDAO.findAll();
        if(requirements == null || requirements.isEmpty()){
            logger.error("Es wurden keine Anforderungen gefunden!");
        }
        return requirements;
    }

    public void updateRequirement(Requirement requirement) {
        if(requirement != null){
            requirementDAO.update(requirement);
        }
        else logger.error("Es gibt kein Requirement, was aktualisiert werden kann!");
    }

    public void deleteRequirementById(int id) {
        Requirement requirement = requirementDAO.findById(id);
        if(requirement != null){
            requirementDAO.delete(requirement);
        }
        else {
            logger.trace("Es gibt kein Objekt mit der Id: " + id + " was gelöscht werden kann!");
        }
    }
}
