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


    public RequirementService() {

    }

    public void saveRequirement(Requirement requirement) {
        if(requirement != null){
            requirementDAO.save(requirement);
        }
        else logger.error("Das Objekt: " + requirement + " darf nicht leer sein!");
    }

    public Requirement getRequirementById(int id) {
        Requirement requirement = requirementDAO.findById(id);
        if(requirement == null){
            logger.error("Es gibt kein Objekt mit der Id: " + id + " !");
        }
        return requirement;
    }

    public List<Requirement> getAllRequirements() {
        List<Requirement> requirements = requirementDAO.findAll();
        if(requirements == null || requirements.isEmpty()){
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

    public List<Requirement> sortByVersion(boolean ascending) {
        if(ascending){
            List<Requirement> requirementsAscended = requirementDAO.sortByVersionAscending();
            if(requirementsAscended == null || requirementsAscended.isEmpty()){
                logger.error("Es wurden keine Anforderungen gefunden, die sortiert werden können!");
            }
            return requirementsAscended;
        }
        else {
            List<Requirement> requirementsDescended = requirementDAO.sortByVersionDescending();
            if(requirementsDescended == null || requirementsDescended.isEmpty()){
                logger.error("Es wurden keine Anforderungen gefunden, die sortiert werden können!");
            }
            return requirementsDescended;
        }
    }

    public List<Requirement> sortByCreationDate(boolean ascending) {
        if(ascending){
            List<Requirement> requirementsAscended = requirementDAO.sortByCreationDateAscending();
            if(requirementsAscended == null || requirementsAscended.isEmpty()){
                logger.error("Es wurden keine Anforderungen gefunden, die sortiert werden können!");
            }
            return requirementsAscended;
        }
        else {
            List<Requirement> requirementsDescended = requirementDAO.sortByCreationDateDescending();
            if(requirementsDescended == null || requirementsDescended.isEmpty()){
                logger.error("Es wurden keine Anforderungen gefunden, die sortiert werden können!");
            }
            return requirementsDescended;
        }
    }

    public List<Requirement> sortByModificationDate(boolean ascending) {
        if(ascending){
            List<Requirement> requirementsAscended = requirementDAO.sortByModificationDateAscending();
            if(requirementsAscended == null || requirementsAscended.isEmpty()){
                logger.error("Es wurden keine Anforderungen gefunden, die sortiert werden können!");
            }
            return requirementsAscended;
        }
        else {
            List<Requirement> requirementsDescended = requirementDAO.sortByModificationDateDescending();
            if(requirementsDescended == null || requirementsDescended.isEmpty()){
                logger.error("Es wurden keine Anforderungen gefunden, die sortiert werden können!");
            }
            return requirementsDescended;
        }
    }

    public List<Requirement> sortByPriority(boolean ascending) {
        if(ascending){
            List<Requirement> requirementsAscended = requirementDAO.sortByPriorityAscending();
            if(requirementsAscended == null || requirementsAscended.isEmpty()){
                logger.error("Es wurden keine Anforderungen gefunden, die sortiert werden können!");
            }
            return requirementsAscended;
        }
        else {
            List<Requirement> requirementsDescended = requirementDAO.sortByPriorityDescending();
            if(requirementsDescended == null || requirementsDescended.isEmpty()){
                logger.error("Es wurden keine Anforderungen gefunden, die sortiert werden können!");
            }
            return requirementsDescended;
        }
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
