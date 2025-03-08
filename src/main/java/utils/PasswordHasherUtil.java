package utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasherUtil {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(14));
    }

    // Überprüfe, ob das eingegebene Passwort mit dem gespeicherten Hash übereinstimmt
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
