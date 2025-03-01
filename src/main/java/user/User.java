package user;


import jakarta.persistence.*;
import testRun.TestRun;

import java.io.Serializable;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRoles userRole;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private TestRun testRun;

    public User() {
    }

    public User(int userId, String username,String password, UserRoles userRole, TestRun testRun) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.testRun = testRun;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
    }

    public TestRun getTestRun() {
        return testRun;
    }

    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
