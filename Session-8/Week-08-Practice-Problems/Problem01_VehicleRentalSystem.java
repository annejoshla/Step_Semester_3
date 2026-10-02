import java.util.ArrayList;
import java.util.List;

public class Problem01_VehicleRentalSystem {
    static abstract class Vehicle {
        private final String name;
        private boolean available = true;

        protected Vehicle(String name) {
            validateText(name, "Vehicle name");
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public boolean isAvailable() {
            return available;
        }

        private void markRented() {
            available = false;
        }

        private void markAvailable() {
            available = true;
        }

        public abstract double calculateCharge(int days);

        private static void validateText(String value, String field) {
            if (value == null || value.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        field + " cannot be blank.");
            }
        }
    }

    static class Sedan extends Vehicle {
        public Sedan(String name) {
            super(name);
        }

        @Override
        public double calculateCharge(int days) {
            return days * 50.0;
        }
    }

    static class SUV extends Vehicle {
        public SUV(String name) {
            super(name);
        }

        @Override
        public double calculateCharge(int days) {
            return days * 80.0;
        }
    }

    static class Truck extends Vehicle {
        public Truck(String name) {
            super(name);
        }

        @Override
        public double calculateCharge(int days) {
            return days * 120.0;
        }
    }

    static class Customer {
        private final String name;

        public Customer(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Customer name cannot be blank.");
            }

            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Rental {
        private final Vehicle vehicle;
        private final Customer customer;
        private final int days;
        private boolean active;

        public Rental(Vehicle vehicle, Customer customer, int days) {
            if (days <= 0) {
                throw new IllegalArgumentException(
                        "Rental days must be positive.");
            }

            this.vehicle = vehicle;
            this.customer = customer;
            this.days = days;
            this.active = true;
        }

        public double getCharge() {
            return vehicle.calculateCharge(days);
        }

        public void returnVehicle() {
            if (active) {
                active = false;
                vehicle.markAvailable();
            }
        }

        public boolean isActive() {
            return active;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public Customer getCustomer() {
            return customer;
        }
    }

    static class RentalSystem {
        private final List<Rental> rentals = new ArrayList<>();

        public Rental rentVehicle(
                Customer customer, Vehicle vehicle, int days) {

            if (!vehicle.isAvailable()) {
                System.out.println(vehicle.getName()
                        + " is currently unavailable.");
                return null;
            }

            vehicle.markRented();

            Rental rental = new Rental(vehicle, customer, days);
            rentals.add(rental);

            System.out.printf(
                    "%s rented successfully by %s.%n",
                    vehicle.getName(),
                    customer.getName());

            System.out.printf(
                    "Rental charge: $%.2f%n",
                    rental.getCharge());

            return rental;
        }

        public void returnVehicle(Rental rental) {
            if (rental != null && rental.isActive()) {
                rental.returnVehicle();

                System.out.printf(
                        "%s returned by %s.%n",
                        rental.getVehicle().getName(),
                        rental.getCustomer().getName());
            }
        }
    }

    public static void main(String[] args) {
        RentalSystem system = new RentalSystem();

        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");

        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        Rental rental1 =
                system.rentVehicle(customer1, sedanA, 3);

        system.rentVehicle(customer2, sedanA, 2);

        system.returnVehicle(rental1);

        system.rentVehicle(customer3, suvB, 5);
    }
}