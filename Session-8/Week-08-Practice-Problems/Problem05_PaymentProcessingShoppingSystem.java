import java.util.ArrayList;
import java.util.List;

public class Problem05_PaymentProcessingShoppingSystem {
    static class Product {
        private final String name;
        private final double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }
    }

    static class OrderItem {
        private final Product product;
        private final int quantity;

        public OrderItem(Product product, int quantity) {
            if (quantity <= 0) {
                throw new IllegalArgumentException(
                        "Quantity must be positive.");
            }

            this.product = product;
            this.quantity = quantity;
        }

        public double total() {
            return product.getPrice() * quantity;
        }
    }

    interface PaymentMethod {
        boolean processPayment(double amount);
    }

    static class CreditCardPayment implements PaymentMethod {
        @Override
        public boolean processPayment(double amount) {
            return true;
        }
    }

    static class PayPalPayment implements PaymentMethod {
        @Override
        public boolean processPayment(double amount) {
            return false;
        }
    }

    static class BankTransferPayment
            implements PaymentMethod {

        @Override
        public boolean processPayment(double amount) {
            return true;
        }
    }

    enum OrderStatus {
        PENDING,
        PAID
    }

    static class Order {
        private final String id;
        private final List<OrderItem> items =
                new ArrayList<>();
        private OrderStatus status = OrderStatus.PENDING;

        public Order(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public OrderStatus getStatus() {
            return status;
        }

        public void addProduct(Product product, int quantity) {
            items.add(new OrderItem(product, quantity));
        }

        public double total() {
            double total = 0;

            for (OrderItem item : items) {
                total += item.total();
            }

            return total;
        }

        public void pay(PaymentMethod paymentMethod) {
            if (items.isEmpty()) {
                System.out.println(
                        "Cannot process payment for an empty order.");
                return;
            }

            if (status == OrderStatus.PAID) {
                System.out.println(
                        "Order " + id + " is already paid.");
                return;
            }

            System.out.println(
                    "Payment initiated for Order " + id + ".");

            boolean successful =
                    paymentMethod.processPayment(total());

            if (successful) {
                status = OrderStatus.PAID;
                System.out.println(
                        "Payment successful. Order status: " + status + ".");
            } else {
                status = OrderStatus.PENDING;
                System.out.println(
                        "Payment failed. Order status: " + status + ".");
            }
        }
    }

    public static void main(String[] args) {
        Product productA = new Product("Product A", 20.0);
        Product productB = new Product("Product B", 30.0);

        Order orderX = new Order("X");
        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);
        orderX.pay(new CreditCardPayment());
        System.out.println("Order " + orderX.getId() + " status: " +
                orderX.getStatus());

        Order orderY = new Order("Y");
        orderY.pay(new CreditCardPayment());
        System.out.println("Order " + orderY.getId() + " status: " +
                orderY.getStatus());

        Order orderZ = new Order("Z");
        orderZ.addProduct(productA, 1);
        orderZ.pay(new PayPalPayment());
        System.out.println("Order " + orderZ.getId() + " status: " +
                orderZ.getStatus());
    }
}