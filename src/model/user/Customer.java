package model.user;

import model.market.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Represents a customer user who can make purchases.
 * Extends User class.
 */
public class Customer extends User {

    private List<Product> shoppingCart;
    private Set<Product> wishlist;
    private List<Order> orders;
    private BigDecimal balance;

    public Customer(String id, String name, String email, String phone, Date registrationDate, BigDecimal balance) {
        super(id, name, email, phone, registrationDate);
        this.shoppingCart = new ArrayList<>();
        this.wishlist = new HashSet<>();
        this.orders = new ArrayList<>();
        this.balance = balance;
    }

    @Override
    public boolean validateUser() {
        return getEmail().contains("@");
    }

    public void addBalanceWithMessage(BigDecimal amount) {
        System.out.println("Adding funds to " + getName() + "'s account ($" + balance + " + $" + amount + " = " + "$"
                + (balance.add(amount)) + ")");
        this.balance = this.balance.add(amount);
    }

    // Getters and setters
    public BigDecimal getBalance() {
        return balance;
    }

    public void addBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Set<Product> getWishlist() {
        return wishlist;
    }

    public void setWishlist(Set<Product> wishlist) {
        this.wishlist = wishlist;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}