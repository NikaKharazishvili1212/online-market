package model.user;

import java.util.Date;

/**
 * Represents an administrator user with special privileges.
 * Extends User class.
 */
public class Admin extends User {

    private int adminLevel;

    public Admin(String id, String name, String email, String phone, Date registrationDate, int adminLevel) {
        super(id, name, email, phone, registrationDate);
        this.adminLevel = adminLevel;
    }

    @Override
    public boolean validateUser() {
        return adminLevel > 0 && getEmail().contains("@");
    }

    // Getters and setters
    public int getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(int adminLevel) {
        this.adminLevel = adminLevel;
    }
}