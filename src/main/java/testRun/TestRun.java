package testRun;

import jakarta.persistence.*;
import user.User;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

@Entity
public class TestRun implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int testRunId;

    @Column
    private int runNumber;

    @Column
    private Duration executionTime;

    @Column
    private Date executionDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User tester;

    public TestRun() {
    }

    public TestRun(int testRunId, int runNumber, Duration executionTime, Date executionDate, User tester) {
        this.testRunId = testRunId;
        this.runNumber = runNumber;
        this.executionTime = executionTime;
        this.executionDate = executionDate;
        this.tester = tester;
    }

    public int getTestRunId() {
        return testRunId;
    }

    public void setTestRunId(int testRunId) {
        this.testRunId = testRunId;
    }

    public int getRunNumber() {
        return runNumber;
    }

    public void setRunNumber(int runNumber) {
        this.runNumber = runNumber;
    }

    public Duration getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Duration executionTime) {
        this.executionTime = executionTime;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public User getTester() {
        return tester;
    }

    public void setTester(User tester) {
        this.tester = tester;
    }
}
