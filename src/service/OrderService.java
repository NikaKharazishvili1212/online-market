package service;

import model.exceptions.*;
import model.market.*;
import model.user.*;
import model.report.Report;
import java.math.BigDecimal;
import java.util.function.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class handling order placement business logic.
 * Implements IReportGenerator interface.
 */
public class OrderService implements IReportGenerator {
    
    @FunctionalInterface
    public interface DiscountApplier {
        BigDecimal applyDiscount(BigDecimal originalPrice, double discountRate);
    }
    
    @FunctionalInterface
    public interface StockChecker {
        boolean checkStock(Product product, int requiredQuantity);
    }
    
    @FunctionalInterface
    public interface CustomerNotifier {
        void notifyCustomer(Customer customer, String message);
    }

    @Override
    public void generateReport(Report report) {
        System.out.println("Generating report: " + report.generateReport());
    }

    public void validateMarket() throws MarketException {
        if (System.currentTimeMillis() % 2 == 0) {
            throw new MarketException("Market validation failed: System maintenance");
        }
    }

    public void placeOrder(Customer customer, Product product) {
        BigDecimal totalCost = Product.getPriceWithTax(product.getPrice());

        System.out.println(customer.getName() + " is attempting to order " + product.getName()
                + " (Total cost with tax: $" + totalCost + ")");

        if (customer.getBalance().compareTo(totalCost) < 0) {
            throw new InsufficientBalanceException(
                "Customer " + customer.getName() + " has insufficient balance for order"
            );
        }
        
        if (product.getStock() <= 0) {
            throw new OutOfStockException(
                "Product " + product.getName() + " is out of stock"
            );
        }

        if (customer.getBalance().compareTo(totalCost) >= 0) {
            customer.addBalance(totalCost.negate());
            product.setStock(product.getStock() - 1);
            System.out.println("Order successful for " + customer.getName() + " (Current balance: $"
                    + customer.getBalance() + ")");
            System.out.println("Sending email confirmation to " + customer.getEmail());
        } else {
            System.out.println("Order failed for " + customer.getName() + ". Insufficient funds (Current balance: $"
                    + customer.getBalance() + ")");
        }
    }

    public void applyDiscount(Pricable item, double discountPercentage) {
        if (discountPercentage > 50) {
            throw new DiscountExceededException(
                "Discount " + discountPercentage + "% exceeds maximum allowed 50%"
            );
        }
        
        BigDecimal originalPrice = item.getPrice();
        BigDecimal discount = originalPrice.multiply(BigDecimal.valueOf(discountPercentage / 100));
        BigDecimal newPrice = originalPrice.subtract(discount);
        item.setPrice(newPrice);
        
        System.out.println("Applied " + discountPercentage + "% discount: $" + 
                          originalPrice + " → $" + newPrice);
    }
    
    public void validateAndPrintStatus(Validatable validatable) {
        if (!validatable.validateUser()) {
            throw new InvalidUserException("User validation failed for: " + validatable.toString());
        }
        System.out.println("Validation status: VALID");
    }
    
    public void checkAndRestock(Stockable item, int minimumStock) {
        if (item.getStock() < minimumStock) {
            int restockAmount = minimumStock - item.getStock();
            item.restock(restockAmount);
            System.out.println("Restocked " + restockAmount + " items. New stock: " + item.getStock());
        } else {
            System.out.println("Stock sufficient: " + item.getStock() + " items available");
        }
    }
    
    public void demonstrateLambdaFunctions(Product sampleProduct, Customer sampleCustomer) {
        DiscountApplier discountApplier = (price, rate) -> 
            price.multiply(BigDecimal.ONE.subtract(BigDecimal.valueOf(rate / 100)));
        
        StockChecker stockChecker = (product, quantity) -> 
            product.getStock() >= quantity;
        
        CustomerNotifier customerNotifier = (customer, message) -> 
            System.out.println("Notifying " + customer.getName() + ": " + message);
        
        Predicate<Product> isExpensive = product -> 
            product.getPrice().compareTo(new BigDecimal("100")) > 0;
        
        Function<Product, String> productMapper = product -> 
            product.getName() + " - $" + product.getPrice();
        
        Consumer<String> logger = message -> 
            System.out.println("[LOG] " + message);
        
        Supplier<OrderStatus> defaultStatusSupplier = () -> OrderStatus.PENDING;
        
        BiFunction<Customer, Product, BigDecimal> totalPriceCalculator = (customer, product) -> 
            product.getPrice().multiply(BigDecimal.valueOf(1.1));
        
        BigDecimal discountedPrice = discountApplier.applyDiscount(new BigDecimal("200"), 15.0);
        System.out.println("Discounted price: $" + discountedPrice);
        
        boolean stockResult = stockChecker.checkStock(sampleProduct, 2);
        System.out.println("Stock check result: " + stockResult);
        
        customerNotifier.notifyCustomer(sampleCustomer, "Test notification");
        
        boolean expensiveResult = isExpensive.test(sampleProduct);
        System.out.println("Is expensive: " + expensiveResult);
        
        String mappedProduct = productMapper.apply(sampleProduct);
        System.out.println("Mapped product: " + mappedProduct);
        
        OrderStatus status = defaultStatusSupplier.get();
        System.out.println("Default status: " + status);
        
        BigDecimal total = totalPriceCalculator.apply(sampleCustomer, sampleProduct);
        System.out.println("Total price: " + total);
        
        logger.accept("Lambda functions demonstration completed");
    }
    
    public void processOrdersWithLambdas(List<Order> orders) {
        Predicate<Order> isProcessable = order -> 
            order.getStatus() == OrderStatus.PENDING || 
            order.getStatus() == OrderStatus.PROCESSING;
        
        Consumer<Order> processOrder = order -> {
            order.setStatus(OrderStatus.PROCESSING);
            System.out.println("Processing order: " + order.getId());
        };
        
        List<Order> processableOrders = orders.stream()
            .filter(isProcessable)
            .collect(Collectors.toList());
            
        processableOrders.forEach(processOrder);
    }
}