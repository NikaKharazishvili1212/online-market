package model;

import java.math.BigDecimal;

/**
 * Represents a customer user who can make purchases
 * Extends User class
 */
public class Customer extends User {

    private Product[] shoppingCart;
    private Product[] wishlist;
    private BigDecimal balance;

    public Customer(String id, String name, String email, BigDecimal balance) {
        super(id, name, email);
        this.shoppingCart = new Product[0];
        this.wishlist = new Product[0];
        this.balance = balance;
    }

    // Getters and setters
    public BigDecimal getBalance() { return balance; }
    public void addBalance(BigDecimal amount) { this.balance = this.balance.add(amount); }

    public Product[] getShoppingCart() { return shoppingCart; }
    public void setShoppingCart(Product[] shoppingCart) { this.shoppingCart = shoppingCart; }

    public Product[] getWishlist() { return wishlist; }
    public void setWishlist(Product[] wishlist) { this.wishlist = wishlist; }
}