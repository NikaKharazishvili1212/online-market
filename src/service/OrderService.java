package service;

import model.exceptions.*;
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

    // Handle checked exception
    public void validateMarket() throws MarketException {
        if (System.currentTimeMillis() % 2 == 0) {
            throw new MarketException("Market validation failed: System maintenance");
        }
    }

    public void placeOrder(Customer customer, Product product) {
        BigDecimal totalCost = Product.getPriceWithTax(product.getPrice());

        System.out.println(customer.getName() + " is attempting to order " + product.getName()
                + " (Total cost with tax: $" + totalCost + ")");

        // Use unchecked exceptions
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

    // POLYMORPHIC METHOD: Accepts any Pricable interface type
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
    
    // POLYMORPHIC METHOD: Accepts any Validatable interface type
    public void validateAndPrintStatus(Validatable validatable) {
        if (!validatable.validateUser()) {
            throw new InvalidUserException("User validation failed for: " + validatable.toString());
        }
        System.out.println("Validation status: VALID");
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