package requirement;


public enum RequirementStatus {

    TODO("To Do"),
    IN_PROGRESS("In Progress"),
    WAITING_FOR_TESTCASE("Waiting For Testcase"),
    OUT_OF_SCOPE("Out of Scope"), DONE("Done"),
    REJECTED("Rejected");

    private String label;

    RequirementStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
