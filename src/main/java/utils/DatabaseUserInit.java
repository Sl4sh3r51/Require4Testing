package utils;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import user.User;
import user.UserDAO;
import user.UserRoles;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class DatabaseUserInit implements ServletContextListener {

    @Inject
    private UserDAO userDAO;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("DatabaseUserInit: Initialisierung gestartet");
        if(userDAO.findAll().isEmpty()) {
            User requirementEngineer = new User();
            requirementEngineer.setUsername("RE");
            requirementEngineer.setPassword(PasswordHasherUtil.hashPassword("RE_Password"));
            requirementEngineer.setUserRole(UserRoles.REQUIREMENT_ENGINEER);
            userDAO.save(requirementEngineer);

            User testManager = new User();
            testManager.setUsername("Manager");
            testManager.setPassword(PasswordHasherUtil.hashPassword("TestManager_Password"));
            testManager.setUserRole(UserRoles.TESTMANAGER);
            userDAO.save(testManager);

            User testCreator = new User();
            testCreator.setUsername("Creator");
            testCreator.setPassword(PasswordHasherUtil.hashPassword("Creator_Password"));
            testCreator.setUserRole(UserRoles.TEST_CREATOR);
            userDAO.save(testCreator);

            User tester = new User();
            tester.setUsername("Tester");
            tester.setPassword(PasswordHasherUtil.hashPassword("Tester_Password"));
            tester.setUserRole(UserRoles.TESTER);
            userDAO.save(tester);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getName().equals("com.mysql.cj.jdbc.Driver")) {
                try {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
