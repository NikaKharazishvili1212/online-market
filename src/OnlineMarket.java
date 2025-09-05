import model.*;

/**
 * Main hierarchy class representing the online market system
 * Contains all market data (users, products, orders, etc.)
 */
public class OnlineMarket {

    private Admin[] admins;
    private Customer[] customers;
    private Product[] products;
    private Category[] categories;
    private Order[] orders;
    private Payment[] payments;
    private Review[] reviews;

    public OnlineMarket() {
        this.admins = new Admin[0];
        this.customers = new Customer[0];
        this.products = new Product[0];
        this.categories = new Category[0];
        this.orders = new Order[0];
        this.payments = new Payment[0];
        this.reviews = new Review[0];
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

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public Payment[] getPayments() {
        return payments;
    }

    public void setPayments(Payment[] payments) {
        this.payments = payments;
    }

    public Review[] getReviews() {
        return reviews;
    }

    public void setReviews(Review[] reviews) {
        this.reviews = reviews;
    }
}