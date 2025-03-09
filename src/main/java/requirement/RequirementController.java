package requirement;

import jakarta.annotation.PostConstruct;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named("requirementController")
@ViewScoped
public class RequirementController implements Serializable {

    @Inject
    RequirementService requirementService;

    List<Requirement> requirements = new ArrayList<>();

    List<Requirement> filteredRequirements = new ArrayList<>();

    Requirement requirement = new Requirement();

    @PostConstruct
    public void init() {
        requirements = requirementService.getAllRequirements();
        filteredRequirements = new ArrayList<>(requirements);
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    public List<SelectItem> getRequirementStatus() {
        List<SelectItem> statuses = new ArrayList<>();
        for (RequirementStatus status : RequirementStatus.values()) {
            statuses.add(new SelectItem(status, status.getLabel()));
        }
        return statuses;
    }

    public List<Requirement> getRequirements() {
        return requirements = requirementService.getAllRequirements();
    }

    public List<Requirement> getFilteredRequirements() {
        return filteredRequirements;
    }

    public void setFilteredRequirements(List<Requirement> filteredRequirements) {
        this.filteredRequirements = filteredRequirements;
    }

    public RequirementController() {
    }

    public RequirementController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    public Requirement getRequirementById(int id) {
        if (!requirementService.getAllRequirements().isEmpty()) {
            return requirement = requirementService.getRequirementById(id);
        } else return requirements.get(0);
    }

    public String createRequirement() {
        requirement.setCreationDate(LocalDate.now());
        requirement.setModificationDate(LocalDate.now());

        requirementService.saveRequirement(requirement);
        requirement = new Requirement();
        requirements = requirementService.getAllRequirements();
        return "requirementsEngineer.xhtml?faces-redirect=true";
    }

    public List<Requirement> sortRequirementsByRequirementStatus(RequirementStatus requirementStatus) {
        if (!requirementService.getAllRequirements().isEmpty()) {
            return requirements = requirementService.getRequirementsByStatus(requirementStatus);
        } else return requirements;
    }

    public List<Requirement> sortRequirementsByVersion(boolean ascending) {
        if (!requirementService.getAllRequirements().isEmpty()) {
            return requirements = requirementService.sortByVersion(ascending);
        } else return requirements;
    }

    public List<Requirement> sortRequirementsByCreationDate(boolean ascending) {
        if (!requirementService.getAllRequirements().isEmpty()) {
            return requirements = requirementService.sortByCreationDate(ascending);
        } else return requirements;
    }

    public List<Requirement> sortRequirementsByPriority(boolean ascending) {
        if (!requirementService.getAllRequirements().isEmpty()) {
            return requirements = requirementService.sortByPriority(ascending);
        } else return requirements;
    }

    public List<Requirement> sortRequirementsByModificationDate(boolean ascending) {
        if (!requirementService.getAllRequirements().isEmpty()) {
            return requirements = requirementService.sortByModificationDate(ascending);
        } else return requirements;
    }

    public void removeRequirement(ActionEvent event) {
        Requirement requirementToDelete = (Requirement) event.getComponent().getAttributes().get("selectedRequirement");
        if (!requirementService.getAllRequirements().isEmpty()) {
            requirementService.deleteRequirementById(requirementToDelete.getRequirementId());
            requirements = requirementService.getAllRequirements();
            filteredRequirements = new ArrayList<>(requirements);
        }
    }
}
