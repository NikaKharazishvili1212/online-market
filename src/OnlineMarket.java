import model.*;

/**
 * Main hierarchy class representing the online market system
 */
public class OnlineMarket {

    private Admin[] admins;
    private Customer[] customers;
    private Category[] categories;

    public OnlineMarket() {
        this.admins = new Admin[0];
        this.customers = new Customer[0];
        this.categories = new Category[0];
    }

    // Getters and setters
    public Admin[] getAdmins() {
        return admins;
    }

    public void setAdmins(Admin[] admins) {
        this.admins = admins;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }
}