package model.user;

import java.util.Date;

/**
 * Base class for all users (Admin, Customer) in the online market system.
 * Implements Validatable interface.
 */
public abstract class User implements Validatable {

    private String id;
    private String name;
    private String email;
    private String phone;
    private Date registrationDate;

    public User(String id, String name, String email, String phone, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.registrationDate = registrationDate;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    protected void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    protected void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}