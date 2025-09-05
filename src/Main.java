import model.*;
import service.OrderService;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        // Create basic objects first
        Customer alice = new Customer("C202", "Alice", "alice@email.com", new BigDecimal("0.0"));
        Admin admin = new Admin("A001", "Admin User", "admin@market.com", 1);

        // Create category and product and link product to category
        Category programmingCategory = new Category("CAT001", "Programming", "Programming books and resources");
        Product javaBook = new Product("P101", "Java Book", new BigDecimal("29.99"), 10);
        programmingCategory.setProducts(new Product[] { javaBook });

        // Create review and link to product and customer
        Review javaBookReview = new Review("R001", 5, "Great book on Java!", alice, javaBook);
        javaBook.setReviews(new Review[] { javaBookReview });

        // Create payment and order and link to customer
        Payment samplePayment = new Payment("PAY001", new BigDecimal("32.99"), "Credit Card");
        Order order = new Order("ORD001", "Processing", new Product[] { javaBook }, samplePayment);
        alice.setOrders(new Order[] { order });

        // Create OnlineMarket and populate
        OnlineMarket onlineMarket = new OnlineMarket();
        onlineMarket.setProducts(new Product[] { javaBook });
        onlineMarket.setCustomers(new Customer[] { alice });
        onlineMarket.setAdmins(new Admin[] { admin });
        onlineMarket.setCategories(new Category[] { programmingCategory });

        // Demo ordering process. Attempt order with insufficient balance first then add funds and retry
        OrderService orderService = new OrderService();
        orderService.placeOrder(alice, javaBook);
        alice.addBalanceWithMessage(new BigDecimal("50.0"));
        orderService.placeOrder(alice, javaBook);
    }
}