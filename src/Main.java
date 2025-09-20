import model.user.*;
import model.market.*;
import model.report.*;
import model.generics.*;
import service.*;
import model.exceptions.MarketException;
import model.exceptions.InsufficientBalanceException;
import model.exceptions.OutOfStockException;
import java.math.BigDecimal;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Create basic objects first with new constructor parameters
        Customer alice = new Customer("C202", "Alice", "alice@email.com", "555-1234", new Date(),
                new BigDecimal("0.0"));
        Admin admin = new Admin("A001", "Admin User", "admin@market.com", "555-0001", new Date(), 1);

        // Create category and product and link product to category
        Category programmingCategory = new Category("CAT001", "Programming", "Programming books and resources");
        Product javaBook = new Product("P101", "Java Book", new BigDecimal("29.99"), 10, "JAVA001");
        
        // Use List methods: add, isEmpty, size
        List<Product> productsList = new ArrayList<>();
        productsList.add(javaBook);
        programmingCategory.setProducts(productsList);

        // Create review and add to product
        Review javaBookReview = new Review("R001", 5, "Great book on Java!", alice);
        
        // Use List methods
        List<Review> reviewsList = new ArrayList<>();
        reviewsList.add(javaBookReview);
        javaBook.setReviews(reviewsList);

        // Create payment and order and link to customer
        Payment samplePayment = new Payment("PAY001", new BigDecimal("32.99"), "Credit Card");
        
        // Use List methods
        List<Product> orderProducts = new ArrayList<>();
        orderProducts.add(javaBook);
        Order order = new Order("ORD001", "Processing", orderProducts, samplePayment);
        
        // Use List methods
        List<Order> ordersList = new ArrayList<>();
        ordersList.add(order);
        alice.setOrders(ordersList);

        // Create OnlineMarket and populate it with data
        OnlineMarket onlineMarket = new OnlineMarket();
        
        // Use List methods: add, isEmpty
        List<Customer> customersList = new ArrayList<>();
        customersList.add(alice);
        onlineMarket.setCustomers(customersList);

        List<Admin> adminsList = new ArrayList<>();
        adminsList.add(admin);
        onlineMarket.setAdmins(adminsList);

        List<Category> categoriesList = new ArrayList<>();
        categoriesList.add(programmingCategory);
        onlineMarket.setCategories(categoriesList);

        // Use Map methods: put, get, containsKey
        Map<String, Product> productMap = new HashMap<>();
        productMap.put(javaBook.getId(), javaBook);
        onlineMarket.setProductCatalog(productMap);

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
        } finally {
            System.out.println("Market validation process completed");
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

        // COLLECTION OPERATIONS DEMONSTRATION
        System.out.println("\n=== COLLECTION OPERATIONS ===");
        
        // 1. List operations
        System.out.println("Products in category: " + programmingCategory.getProducts().size());
        System.out.println("Is shopping cart empty? " + alice.getShoppingCart().isEmpty());
        
        // Iterate through List
        System.out.println("Products in programming category:");
        for (Product product : programmingCategory.getProducts()) {
            System.out.println(" - " + product.getName());
        }
        
        // Get first element from List
        if (!programmingCategory.getProducts().isEmpty()) {
            Product firstProduct = programmingCategory.getProducts().get(0);
            System.out.println("First product: " + firstProduct.getName());
        }

        // 2. Set operations (wishlist)
        Set<Product> wishlist = alice.getWishlist();
        System.out.println("Wishlist size: " + wishlist.size());
        wishlist.add(javaBook); // add method
        System.out.println("After adding to wishlist: " + wishlist.size());
        
        // Iterate through Set
        System.out.println("Items in wishlist:");
        for (Product item : wishlist) {
            System.out.println(" - " + item.getName());
        }
        
        // Get first element from Set (using iterator)
        if (!wishlist.isEmpty()) {
            Product firstWishlistItem = wishlist.iterator().next();
            System.out.println("First wishlist item: " + firstWishlistItem.getName());
        }

        // 3. Map operations
        Map<String, Product> catalog = onlineMarket.getProductCatalog();
        System.out.println("Products in catalog: " + catalog.size());
        
        // Iterate through Map
        System.out.println("Product catalog:");
        for (Map.Entry<String, Product> entry : catalog.entrySet()) {
            System.out.println(" - " + entry.getKey() + ": " + entry.getValue().getName());
        }
        
        // Get first element from Map
        if (!catalog.isEmpty()) {
            String firstKey = catalog.keySet().iterator().next();
            Product firstProduct = catalog.get(firstKey);
            System.out.println("First catalog product: " + firstProduct.getName());
        }

        // 4. Generic classes usage
        GenericContainer<Product> productContainer = new GenericContainer<>("CONTAINER_001", javaBook);
        System.out.println("Generic container: " + productContainer.getItemDescription());
        
        GenericPair<String, Product> productPair = new GenericPair<>("FeaturedProduct", javaBook);
        System.out.println("Generic pair: " + productPair);

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