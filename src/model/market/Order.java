package model.market;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Represents a customer order with products and status.
 */
public class Order {

    private String id;
    private OrderStatus status;
    private List<Product> products;
    private Payment payment;
    
    @FunctionalInterface
    public interface OrderValidator {
        boolean validate(Order order);
    }
    
    @FunctionalInterface
    public interface OrderProcessor {
        void process(Order order, String processorName);
    }
    
    @FunctionalInterface
    public interface OrderCalculator {
        double calculate(Order order, double taxRate);
    }

    public Order(String id, OrderStatus status, List<Product> products, Payment payment) {
        this.id = id;
        this.status = status;
        this.products = products;
        this.payment = payment;
    }

    public void processWithLambdaFunctions() {
        Predicate<Order> isValidOrder = order -> 
            order != null && 
            order.products != null && 
            !order.products.isEmpty() &&
            order.status != null;
        
        Function<Order, Double> totalCalculator = order -> 
            order.products.stream()
                .mapToDouble(p -> p.getPrice().doubleValue())
                .sum();
        
        Consumer<Order> statusUpdater = order -> {
            if (order.status == OrderStatus.PENDING) {
                order.status = OrderStatus.PROCESSING;
            }
        };
        
        Supplier<String> orderSummarySupplier = () -> 
            "Order " + id + " contains " + products.size() + " products";
        
        java.util.function.BiFunction<Order, Double, String> taxCalculator = (order, taxRate) -> {
            double total = totalCalculator.apply(order);
            double tax = total * taxRate;
            return String.format("Total: $%.2f, Tax: $%.2f", total, tax);
        };
        
        if (isValidOrder.test(this)) {
            System.out.println("Order validation passed");
            double total = totalCalculator.apply(this);
            System.out.println("Order total: $" + total);
            statusUpdater.accept(this);
            System.out.println(orderSummarySupplier.get());
            System.out.println(taxCalculator.apply(this, 0.1));
        }
    }
    
    public boolean validateOrder(OrderValidator validator) {
        return validator.validate(this);
    }
    
    public void processOrder(OrderProcessor processor, String processorName) {
        processor.process(this, processorName);
    }
    
    public double calculateOrder(OrderCalculator calculator, double taxRate) {
        return calculator.calculate(this, taxRate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}