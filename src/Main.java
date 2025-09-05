import model.*;
import service.OrderService;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        // Create objects with proper relationships
        Category programmingCategory = new Category("CAT001", "Programming", "Programming books and resources");
        Product javaBook = new Product("P101", "Java Book", new BigDecimal("29.99"), 10, programmingCategory);

        Customer alice = new Customer("C202", "Alice", "alice@email.com", new BigDecimal("0.0"));
        Admin admin = new Admin("A001", "Admin User", "admin@market.com", 1);

        Payment samplePayment = new Payment("PAY001", new BigDecimal("32.99"), "Credit Card");
        Order order = new Order("ORD001", "Processing", new Product[] { javaBook }, samplePayment);

        Review javaBookReview = new Review("R001", 5, "Great book on Java!", alice, javaBook);
        OrderService orderService = new OrderService();

        // Link orders to customer
        alice.setOrders(new Order[] { order });

        // Create OnlineMarket instance and populate it
        OnlineMarket onlineMarket = new OnlineMarket();
        onlineMarket.setProducts(new Product[] { javaBook });
        onlineMarket.setCustomers(new Customer[] { alice });
        onlineMarket.setAdmins(new Admin[] { admin });
        onlineMarket.setCategories(new Category[] { programmingCategory });
        onlineMarket.setPayments(new Payment[] { samplePayment });
        onlineMarket.setReviews(new Review[] { javaBookReview });
        onlineMarket.setOrders(new Order[] { order });

        // Demo ordering process
        orderService.placeOrder(alice, javaBook);
        BigDecimal fundsToAdd = new BigDecimal("50.0");
        System.out.println("Adding funds to Alice's account ($" + alice.getBalance() + " + $" + fundsToAdd + ")");
        alice.addBalance(fundsToAdd);
        orderService.placeOrder(alice, javaBook);
    }
}