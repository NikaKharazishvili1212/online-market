import model.user.*;
import model.market.*;
import model.report.*;
import service.OrderService;
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
        Product javaBook = new Product("P101", "Java Book", new BigDecimal("29.99"), 10);
        programmingCategory.setProducts(new Product[] { javaBook });

        // Create reports
        SimpleReport salesReport = new SimpleReport("Monthly Sales", "$2999.99");
        SimpleReport inventoryReport = new SimpleReport("Stock Report", "150 items");

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

        // Polymorphism demo - process different entities and generate reports
        orderService.processEntity(javaBook);
        orderService.processEntity(programmingCategory);
        orderService.generateReport(salesReport);
        orderService.generateReport(inventoryReport);

        // Order process demo
        orderService.placeOrder(alice, javaBook);
        alice.addBalanceWithMessage(new BigDecimal("50.0"));
        orderService.placeOrder(alice, javaBook);

        // User validation demo
        System.out.println("Admin valid: " + admin.validateUser());
        System.out.println("Customer valid: " + alice.validateUser());
    }
}