package service;

import model.market.*;
import model.user.*;
import model.report.Report;
import java.math.BigDecimal;

/**
 * Service class handling order placement business logic.
 * Implements IReportGenerator interface.
 */
public class OrderService implements IReportGenerator {

    @Override
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

    // POLYMORPHIC METHOD: Accepts any Pricable interface type
    public void applyDiscount(Pricable item, double discountPercentage) {
        BigDecimal originalPrice = item.getPrice();
        BigDecimal discount = originalPrice.multiply(BigDecimal.valueOf(discountPercentage / 100));
        BigDecimal newPrice = originalPrice.subtract(discount);
        item.setPrice(newPrice);
        
        System.out.println("Applied " + discountPercentage + "% discount: $" + 
                          originalPrice + " → $" + newPrice);
    }
    
    // POLYMORPHIC METHOD: Accepts any Validatable interface type
    public void validateAndPrintStatus(Validatable validatable) {
        boolean isValid = validatable.validateUser();
        System.out.println("Validation status: " + (isValid ? "VALID" : "INVALID"));
    }
    
    // POLYMORPHIC METHOD: Accepts any Stockable interface type
    public void checkAndRestock(Stockable item, int minimumStock) {
        if (item.getStock() < minimumStock) {
            int restockAmount = minimumStock - item.getStock();
            item.restock(restockAmount);
            System.out.println("Restocked " + restockAmount + " items. New stock: " + item.getStock());
        } else {
            System.out.println("Stock sufficient: " + item.getStock() + " items available");
        }
    }
}