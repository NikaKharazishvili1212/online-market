package model.market;

/**
 * Complex enum representing different payment methods.
 */
public enum PaymentMethod {

    CREDIT_CARD("CC", "Credit Card", 0.02) {
        @Override
        public boolean isSecure() {
            return true;
        }

        @Override
        public String getPaymentInstructions() {
            return "Enter your card details for secure payment";
        }
    },

    PAYPAL("PP", "PayPal", 0.01) {
        @Override
        public boolean isSecure() {
            return true;
        }

        @Override
        public String getPaymentInstructions() {
            return "Redirect to PayPal for authentication";
        }
    },

    BANK_TRANSFER("BT", "Bank Transfer", 0.0) {
        @Override
        public boolean isSecure() {
            return true;
        }

        @Override
        public String getPaymentInstructions() {
            return "Use your online banking for transfer";
        }
    },

    CRYPTO("CR", "Cryptocurrency", 0.05) {
        @Override
        public boolean isSecure() {
            return true;
        }

        @Override
        public String getPaymentInstructions() {
            return "Send cryptocurrency to our wallet address";
        }
    },

    CASH_ON_DELIVERY("COD", "Cash on Delivery", 0.1) {
        @Override
        public boolean isSecure() {
            return false;
        }

        @Override
        public String getPaymentInstructions() {
            return "Pay with cash when your order arrives";
        }
    };

    private final String code;
    private final String description;
    private final double feePercentage;

    PaymentMethod(String code, String description, double feePercentage) {
        this.code = code;
        this.description = description;
        this.feePercentage = feePercentage;
    }

    public abstract boolean isSecure();

    public abstract String getPaymentInstructions();

    public double calculateFee(double amount) {
        return amount * feePercentage;
    }

    public double getTotalWithFee(double amount) {
        return amount + calculateFee(amount);
    }

    public static PaymentMethod getMostPopular() {
        return CREDIT_CARD;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public double getFeePercentage() {
        return feePercentage;
    }
}