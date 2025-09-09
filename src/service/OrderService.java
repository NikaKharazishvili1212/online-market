package service;

import model.market.*;
import model.user.*;
import model.report.Report;
import java.math.BigDecimal;

/**
 * Service class handling order placement business logic.
 */
public class OrderService {

    public void processEntity(Entity entity) {
        System.out.println("Processing: " + entity.getId());
    }

    public void generateReport(Report report) {
        System.out.println("Generating report: " + report.generateReport());
    }

    public void placeOrder(Customer customer, Product product) {
        BigDecimal totalCost = Product.getPriceWithTax(product.getPrice());

        System.out.println(customer.getName() + " is attempting to order " + product.getName()
                + " (Total cost with tax: $" + totalCost + ")");

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
}