package model.market;

import model.user.*;
import java.util.*;

/**
 * Main hierarchy class representing the online market system.
 * Contains all market data (users, products, orders, etc.).
 */
public class OnlineMarket {

    public static final String MARKET_NAME = "JavaMarket";
    private static int totalUsers;
    
    private List<Admin> admins;
    private List<Customer> customers;
    private List<Category> categories;
    private Map<String, Product> productCatalog;

    static {
        totalUsers = 0;
        System.out.println("OnlineMarket system initialized");
    }

    public OnlineMarket() {
        this.admins = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.productCatalog = new HashMap<>();
    }

    // Getters and setters
    public static int getTotalUsers() {
        return totalUsers;
    }

    public static void incrementTotalUsers() {
        totalUsers++;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Map<String, Product> getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(Map<String, Product> productCatalog) {
        this.productCatalog = productCatalog;
    }
}