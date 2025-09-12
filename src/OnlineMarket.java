import model.user.*;
import model.market.*;

/**
 * Main hierarchy class representing the online market system.
 * Contains all market data (users, products, orders, etc.).
 */
public class OnlineMarket {

    public static final String MARKET_NAME = "JavaMarket";
    private static int totalUsers;
    
    private Admin[] admins;
    private Customer[] customers;
    private Category[] categories;

    static {
        totalUsers = 0;
        System.out.println("OnlineMarket system initialized");
    }

    public OnlineMarket() {
        this.admins = new Admin[0];
        this.customers = new Customer[0];
        this.categories = new Category[0];
    }

    // Getters and setters
    public static int getTotalUsers() {
        return totalUsers;
    }

    public static void incrementTotalUsers() {
        totalUsers++;
    }

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