import model.*;
import service.OrderService;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        // Create sample data
        Product javaBook = new Product("P101", "Java Book", new BigDecimal("29.99"), 10);
        Customer alice = new Customer("C202", "Alice", "alice@email.com", new BigDecimal("0.0"));
        Review javaBookReview = new Review("R001", 5, "Great book on Java!");
        OrderService orderService = new OrderService();

        // Create OnlineMarket instance and populate it with sample data
        OnlineMarket onlineMarket = new OnlineMarket();
        onlineMarket.setProducts(new Product[] { javaBook });
        onlineMarket.setCustomers(new Customer[] { alice });

        // Create other objects
        Admin admin = new Admin("A001", "Admin User", "admin@market.com", 1);
        Category programmingCategory = new Category("CAT001", "Programming", "Programming books and resources");
        Payment samplePayment = new Payment("PAY001", new BigDecimal("32.99"), "Credit Card");
        Order order = new Order("ORD001", "Processing", new Product[] { javaBook });

        // Add more objects to OnlineMarket
        onlineMarket.setAdmins(new Admin[] { admin });
        onlineMarket.setCategories(new Category[] { programmingCategory });
        onlineMarket.setPayments(new Payment[] { samplePayment });
        onlineMarket.setReviews(new Review[] { javaBookReview });
        onlineMarket.setOrders(new Order[] { order });

        // Try buying the product with insufficient funds, then add funds and try again
        orderService.placeOrder(alice, javaBook);
        BigDecimal fundsToAdd = new BigDecimal("50.0");
        System.out.println("Adding funds to Alice's account ($" + alice.getBalance() + " + $" + fundsToAdd + ")");
        alice.addBalance(fundsToAdd);
        orderService.placeOrder(alice, javaBook);
    }
}