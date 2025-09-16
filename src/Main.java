import model.user.*;
import model.market.*;
import model.report.*;
import service.OrderService;
import service.MarketResource;
import model.exceptions.MarketException;
import model.exceptions.InsufficientBalanceException;
import model.exceptions.OutOfStockException;
import java.math.BigDecimal;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        // Create basic objects first with new constructor parameters
        Customer alice = new Customer("C202", "Alice", "alice@email.com", "555-1234", new Date(),
                new BigDecimal("0.0"));
        Admin admin = new Admin("A001", "Admin User", "admin@market.com", "555-0001", new Date(), 1);

        // Create category and product and link product to category
        Category programmingCategory = new Category("CAT001", "Programming", "Programming books and resources");
        Product javaBook = new Product("P101", "Java Book", new BigDecimal("29.99"), 10, "JAVA001");
        programmingCategory.setProducts(new Product[] { javaBook });

        // Create review and add to product
        Review javaBookReview = new Review("R001", 5, "Great book on Java!", alice);
        javaBook.setReviews(new Review[] { javaBookReview });

        // Create payment and order and link to customer
        Payment samplePayment = new Payment("PAY001", new BigDecimal("32.99"), "Credit Card");
        Order order = new Order("ORD001", "Processing", new Product[] { javaBook }, samplePayment);
        alice.setOrders(new Order[] { order });

        // Create OnlineMarket and populate it with data
        OnlineMarket onlineMarket = new OnlineMarket();
        onlineMarket.setCustomers(new Customer[] { alice });
        onlineMarket.setAdmins(new Admin[] { admin });
        onlineMarket.setCategories(new Category[] { programmingCategory });

        // Demo ordering process
        OrderService orderService = new OrderService();

        // Generate reports
        SimpleReport salesReport = new SimpleReport("Monthly Sales", "$2999.99");
        SimpleReport inventoryReport = new SimpleReport("Stock Report", "150 items");
        orderService.generateReport(salesReport);
        orderService.generateReport(inventoryReport);
        
        // Try-with-resources for AutoCloseable
        try (MarketResource resource = new MarketResource("DatabaseConnection")) {
            resource.useResource();
            orderService.validateMarket(); // Checked exception
            System.out.println("Market validation passed");
        } catch (MarketException e) {
            System.out.println("Market exception caught: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General exception: " + e.getMessage());
        }

        // POLYMORPHISM DEMO with exception handling
        System.out.println("Before discount - Java Book price: $" + javaBook.getPrice());
        
        try {
            orderService.applyDiscount(javaBook, 15.0);
            orderService.validateAndPrintStatus(alice);
            orderService.validateAndPrintStatus(admin);
            orderService.checkAndRestock(javaBook, 8);
        } catch (Exception e) {
            System.out.println("Operation failed: " + e.getMessage());
        }

        // Order process with exception handling
        try {
            orderService.placeOrder(alice, javaBook);
        } catch (InsufficientBalanceException | OutOfStockException e) {
            System.out.println("Order failed: " + e.getMessage());
        }

        alice.addBalanceWithMessage(new BigDecimal("50.0"));
        
        try {
            orderService.placeOrder(alice, javaBook);
        } catch (InsufficientBalanceException | OutOfStockException e) {
            System.out.println("Order failed: " + e.getMessage());
        }
    }
}