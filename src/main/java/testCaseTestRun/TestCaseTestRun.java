package testCaseTestRun;

import jakarta.persistence.*;
import testCase.TestCase;
import testRun.TestRun;

import java.io.Serializable;

@Entity
public class TestCaseTestRun implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testCaseTestRunId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "testfall")
    private TestCase testCase;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "testrun")
    private TestRun testRun;

    @Column
    private boolean passed;

    public TestCaseTestRun() {
    }

    public TestCaseTestRun(int testCaseTestRunId, TestCase testCase, TestRun testRun, boolean passed) {
        this.testCaseTestRunId = testCaseTestRunId;
        this.testCase = testCase;
        this.testRun = testRun;
        this.passed = passed;
    }

    public int getTestCaseTestRunId() {
        return testCaseTestRunId;
    }

    public void setTestCaseTestRunId(int testCaseTestRunId) {
        this.testCaseTestRunId = testCaseTestRunId;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public TestRun getTestRun() {
        return testRun;
    }

    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }

    public boolean getPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
