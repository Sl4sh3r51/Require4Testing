package requirement;


import jakarta.persistence.*;
import testCase.TestCase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Requirement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requirementId;

    @Column
    private String title;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column
    private RequirementStatus requirementStatus;

    @Column
    private double version;

    @Column
    private Date creationDate;

    @Column
    private Date modificationDate;

    @Column
    private int priority;

    @OneToMany(mappedBy = "requirement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestCase> testCases = new ArrayList<>();

    public Requirement() {
    }

    public Requirement(int requirementId, String title, String description, RequirementStatus requirementStatus, double version, Date creationDate, Date modificationDate, int priority, List<TestCase> testCases) {
        this.requirementId = requirementId;
        this.title = title;
        this.description = description;
        this.requirementStatus = requirementStatus;
        this.version = version;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.priority = priority;
        this.testCases = testCases;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public int getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequirementStatus getRequirementStatus() {
        return requirementStatus;
    }

    public void setRequirementStatus(RequirementStatus requirementStatus) {
        this.requirementStatus = requirementStatus;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
