package requirement;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("requirementController")
@SessionScoped
public class RequirementController implements Serializable {

    @Inject
    RequirementService requirementService;

    List<Requirement> requirements;

    Requirement requirement;

    public RequirementController() {}

    public RequirementController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    public List<Requirement> getRequirements() {
        return requirements = requirementService.getAllRequirements();
    }

    public Requirement getRequirementById(int id) {
        if(!requirementService.getAllRequirements().isEmpty()){
            return requirement = requirementService.getRequirementById(id);
        }
        else return requirements.get(0);
    }

    public void createRequirement(Requirement newRequirement) {
        requirement = new Requirement();
        requirement.setRequirementId(newRequirement.getRequirementId());
        requirement.setTitle(newRequirement.getTitle());
        requirement.setDescription(newRequirement.getDescription());
        requirement.setRequirementStatus(newRequirement.getRequirementStatus());
        requirement.setVersion(newRequirement.getVersion());
        requirement.setCreationDate(newRequirement.getCreationDate());
        requirement.setModificationDate(newRequirement.getModificationDate());
        requirement.setPriority(newRequirement.getPriority());
        requirement.setTestCases(newRequirement.getTestCases());
        requirementService.saveRequirement(requirement);
    }

    public List<Requirement> sortRequirementsByRequirementStatus(RequirementStatus requirementStatus) {
        if(!requirementService.getAllRequirements().isEmpty()){
            return requirements = requirementService.getRequirementsByStatus(requirementStatus);
        }
        else return requirements;
    }

    public List<Requirement> sortRequirementsByVersion(boolean ascending) {
        if(!requirementService.getAllRequirements().isEmpty()){
            return requirements = requirementService.sortByVersion(ascending);
        }
        else return requirements;
    }

    public List<Requirement> sortRequirementsByCreationDate(boolean ascending) {
        if(!requirementService.getAllRequirements().isEmpty()){
            return requirements = requirementService.sortByCreationDate(ascending);
        }
        else return requirements;
    }

    public List<Requirement> sortRequirementsByPriority(boolean ascending) {
        if(!requirementService.getAllRequirements().isEmpty()){
            return requirements = requirementService.sortByPriority(ascending);
        }
        else return requirements;
    }

    public List<Requirement> sortRequirementsByModificationDate(boolean ascending) {
        if(!requirementService.getAllRequirements().isEmpty()){
            return requirements = requirementService.sortByModificationDate(ascending);
        }
        else return requirements;
    }

    public void removeRequirement(Requirement requirement) {
        if(!requirementService.getAllRequirements().isEmpty()){
            requirementService.deleteRequirementById(requirement.getRequirementId());
        }
    }


}
