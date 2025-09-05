package model;

/**
 * Represents an administrator user with special privileges
 * Extends User class
 */
public class Admin extends User {

    private int adminLevel;

    public Admin(String id, String name, String email, int adminLevel) {
        super(id, name, email);
        this.adminLevel = adminLevel;
    }

    // Getters and setters
    public int getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(int adminLevel) {
        this.adminLevel = adminLevel;
    }
}