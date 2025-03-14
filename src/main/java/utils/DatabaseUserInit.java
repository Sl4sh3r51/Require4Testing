package utils;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(DatabaseUserInit.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        if(userDAO.findAll().isEmpty()) {
            System.out.println("DatabaseUserInit: Initialisierung gestartet");
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

            User tester1 = new User();
            tester1.setUsername("Tester1");
            tester1.setPassword(PasswordHasherUtil.hashPassword("Testing_PW")); // Passwort kann verschlüsselt werden
            tester1.setUserRole(UserRoles.TESTER);
            userDAO.save(tester1);

            User tester2 = new User();
            tester2.setUsername("Tester2");
            tester2.setPassword(PasswordHasherUtil.hashPassword("Assurance_Testing_PW"));
            tester2.setUserRole(UserRoles.TESTER);
            userDAO.save(tester2);

            User tester3 = new User();
            tester3.setUsername("Tester3");
            tester3.setPassword(PasswordHasherUtil.hashPassword("Regression_Testing_PW"));
            tester3.setUserRole(UserRoles.TESTER);
            userDAO.save(tester3);

            User tester4 = new User();
            tester4.setUsername("Tester4");
            tester4.setPassword(PasswordHasherUtil.hashPassword("Performance_Testing_PW"));
            tester4.setUserRole(UserRoles.TESTER);
            userDAO.save(tester4);
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
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
