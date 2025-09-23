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
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Customer alice = new Customer("C202", "Alice", "alice@email.com", "555-1234", new Date(),
                new BigDecimal("0.0"));
        Admin admin = new Admin("A001", "Admin User", "admin@market.com", "555-0001", new Date(), 1);

        Category programmingCategory = new Category("CAT001", "Programming", "Programming books and resources");
        Product javaBook = new Product("P101", "Java Book", new BigDecimal("29.99"), 10, "JAVA001");
        
        List<Product> productsList = new ArrayList<>();
        productsList.add(javaBook);
        programmingCategory.setProducts(productsList);

        Review javaBookReview = new Review("R001", 5, "Great book on Java!", alice);
        
        List<Review> reviewsList = new ArrayList<>();
        reviewsList.add(javaBookReview);
        javaBook.setReviews(reviewsList);

        Payment samplePayment = new Payment("PAY001", new BigDecimal("32.99"), "Credit Card");
        
        List<Product> orderProducts = new ArrayList<>();
        orderProducts.add(javaBook);
        Order order = new Order("ORD001", OrderStatus.PENDING, orderProducts, samplePayment);
        
        List<Order> ordersList = new ArrayList<>();
        ordersList.add(order);
        alice.setOrders(ordersList);

        OnlineMarket onlineMarket = new OnlineMarket();
        
        List<Customer> customersList = new ArrayList<>();
        customersList.add(alice);
        onlineMarket.setCustomers(customersList);

        List<Admin> adminsList = new ArrayList<>();
        adminsList.add(admin);
        onlineMarket.setAdmins(adminsList);

        List<Category> categoriesList = new ArrayList<>();
        categoriesList.add(programmingCategory);
        onlineMarket.setCategories(categoriesList);

        Map<String, Product> productMap = new HashMap<>();
        productMap.put(javaBook.getId(), javaBook);
        onlineMarket.setProductCatalog(productMap);

        OrderService orderService = new OrderService();

        SimpleReport salesReport = new SimpleReport("Monthly Sales", "$2999.99");
        SimpleReport inventoryReport = new SimpleReport("Stock Report", "150 items");
        orderService.generateReport(salesReport);
        orderService.generateReport(inventoryReport);

        try (MarketResource resource = new MarketResource("DatabaseConnection")) {
            resource.useResource();
            orderService.validateMarket();
            System.out.println("Market validation passed");
        } catch (MarketException e) {
            System.out.println("Market exception caught: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General exception: " + e.getMessage());
        } finally {
            System.out.println("Market validation process completed");
        }

        System.out.println("Before discount - Java Book price: $" + javaBook.getPrice());

        try {
            orderService.applyDiscount(javaBook, 15.0);
            orderService.validateAndPrintStatus(alice);
            orderService.validateAndPrintStatus(admin);
            orderService.checkAndRestock(javaBook, 8);
        } catch (Exception e) {
            System.out.println("Operation failed: " + e.getMessage());
        }

        System.out.println("\n=== HOMEWORK 6 FEATURES ===");
        
        System.out.println("\n--- Complex Enums Demonstration ---");
        
        OrderStatus status = OrderStatus.PROCESSING;
        System.out.println("Order status: " + status.getDescription());
        System.out.println("Can cancel: " + status.canBeCancelled());
        System.out.println("Status message: " + status.getStatusMessage());
        
        PaymentMethod payment = PaymentMethod.CREDIT_CARD;
        System.out.println("Payment fee: $" + payment.calculateFee(100.0));
        System.out.println("Payment instructions: " + payment.getPaymentInstructions());
        
        UserType userType = UserType.PREMIUM_CUSTOMER;
        System.out.println("User max discount: " + userType.getMaxDiscount() + "%");
        System.out.println("Premium features: " + userType.canAccessPremiumFeatures());
        
        ProductCategory category = ProductCategory.ELECTRONICS;
        System.out.println("Category tax: " + (category.getTaxRate() * 100) + "%");
        System.out.println("Shipping restrictions: " + category.getShippingRestrictions());
        
        DiscountType discount = DiscountType.SEASONAL;
        System.out.println("Discount valid: " + discount.isCurrentlyValid());
        System.out.println("Discounted price: $" + discount.applyDiscount(100.0));
        
        System.out.println("\n--- Record Demonstration ---");
        
        OrderRecord orderRecord = new OrderRecord(
            "REC001",
            "Alice",
            "Java Book",
            new BigDecimal("29.99"),
            2,
            LocalDateTime.now(),
            OrderStatus.PENDING
        );
        
        System.out.println("Record order total: $" + orderRecord.getTotalAmount());
        System.out.println("Can cancel: " + orderRecord.canBeCancelled());
        
        OrderRecord quickOrder = OrderRecord.createQuickOrder("QUICK001", "Bob", "Python Book", 39.99);
        System.out.println("Quick order: " + quickOrder.orderId() + " for " + quickOrder.customerName());
        
        System.out.println("\n--- Lambda Functions Demonstration ---");
        
        order.processWithLambdaFunctions();
        
        orderService.demonstrateLambdaFunctions(javaBook, alice);
        
        Order.OrderValidator validator = o -> o.getProducts() != null && !o.getProducts().isEmpty();
        System.out.println("Order validation: " + order.validateOrder(validator));
        
        Order.OrderProcessor processor = (o, name) -> 
            System.out.println("Order " + o.getId() + " processed by " + name);
        order.processOrder(processor, "System");
        
        Order.OrderCalculator calculator = (o, tax) -> 
            o.getProducts().stream()
                .mapToDouble(p -> p.getPrice().doubleValue() * (1 + tax))
                .sum();
        double total = order.calculateOrder(calculator, 0.1);
        System.out.println("Order total with tax: $" + total);
        
        List<Order> ordersToProcess = Arrays.asList(order);
        orderService.processOrdersWithLambdas(ordersToProcess);
        
        System.out.println("\n--- Custom Lambda Functions in Action ---");
        
        OrderService.DiscountApplier customDiscount = (price, rate) -> 
            price.multiply(BigDecimal.ONE.subtract(BigDecimal.valueOf(rate / 100)));
        
        OrderService.StockChecker stockCheck = (product, qty) -> product.getStock() >= qty;
        
        OrderService.CustomerNotifier notifier = (customer, msg) -> 
            System.out.println("Notification to " + customer.getName() + ": " + msg);
        
        BigDecimal newPrice = customDiscount.applyDiscount(new BigDecimal("100"), 20);
        System.out.println("Custom discount applied: $" + newPrice);
        
        boolean inStock = stockCheck.checkStock(javaBook, 5);
        System.out.println("Product in stock: " + inStock);
        
        notifier.notifyCustomer(alice, "Your order has been processed!");

        System.out.println("\n=== COLLECTION OPERATIONS ===");
        
        System.out.println("Products in category: " + programmingCategory.getProducts().size());
        System.out.println("Is shopping cart empty? " + alice.getShoppingCart().isEmpty());
        
        System.out.println("Products in programming category:");
        for (Product product : programmingCategory.getProducts()) {
            System.out.println(" - " + product.getName());
        }
        
        if (!programmingCategory.getProducts().isEmpty()) {
            Product firstProduct = programmingCategory.getProducts().get(0);
            System.out.println("First product: " + firstProduct.getName());
        }

        Set<Product> wishlist = alice.getWishlist();
        System.out.println("Wishlist size: " + wishlist.size());
        wishlist.add(javaBook);
        System.out.println("After adding to wishlist: " + wishlist.size());
        
        System.out.println("Items in wishlist:");
        for (Product item : wishlist) {
            System.out.println(" - " + item.getName());
        }
        
        if (!wishlist.isEmpty()) {
            Product firstWishlistItem = wishlist.iterator().next();
            System.out.println("First wishlist item: " + firstWishlistItem.getName());
        }

        Map<String, Product> catalog = onlineMarket.getProductCatalog();
        System.out.println("Products in catalog: " + catalog.size());
        
        System.out.println("Product catalog:");
        for (Map.Entry<String, Product> entry : catalog.entrySet()) {
            System.out.println(" - " + entry.getKey() + ": " + entry.getValue().getName());
        }
        
        if (!catalog.isEmpty()) {
            String firstKey = catalog.keySet().iterator().next();
            Product firstProduct = catalog.get(firstKey);
            System.out.println("First catalog product: " + firstProduct.getName());
        }

        GenericContainer<Product> productContainer = new GenericContainer<>("CONTAINER_001", javaBook);
        System.out.println("Generic container: " + productContainer.getItemDescription());
        
        GenericPair<String, Product> productPair = new GenericPair<>("FeaturedProduct", javaBook);
        System.out.println("Generic pair: " + productPair);

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