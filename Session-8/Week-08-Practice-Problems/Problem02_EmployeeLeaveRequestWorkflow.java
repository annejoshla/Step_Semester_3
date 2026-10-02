import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Problem02_EmployeeLeaveRequestWorkflow {
    enum LeaveStatus {
        PENDING,
        APPROVED,
        REJECTED
    }

    static abstract class Employee {
        private final String name;

        protected Employee(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Employee name cannot be blank.");
            }

            this.name = name;
        }

        public String getName() {
            return name;
        }

        public abstract boolean isLeaveAllowed(long days);
    }

    static class FullTimeEmployee extends Employee {
        public FullTimeEmployee(String name) {
            super(name);
        }

        @Override
        public boolean isLeaveAllowed(long days) {
            return days <= 30;
        }
    }

    static class PartTimeEmployee extends Employee {
        public PartTimeEmployee(String name) {
            super(name);
        }

        @Override
        public boolean isLeaveAllowed(long days) {
            return days <= 10;
        }
    }

    static class Contractor extends Employee {
        public Contractor(String name) {
            super(name);
        }

        @Override
        public boolean isLeaveAllowed(long days) {
            return days <= 5;
        }
    }

    static class LeaveRequest {
        private final Employee employee;
        private final LocalDate startDate;
        private final LocalDate endDate;
        private LeaveStatus status = LeaveStatus.PENDING;

        public LeaveRequest(
                Employee employee,
                LocalDate startDate,
                LocalDate endDate) {

            if (employee == null
                    || startDate == null
                    || endDate == null) {
                throw new IllegalArgumentException(
                        "Leave details cannot be null.");
            }

            if (endDate.isBefore(startDate)) {
                throw new IllegalArgumentException(
                        "End date cannot be before start date.");
            }

            long days = ChronoUnit.DAYS.between(
                    startDate, endDate) + 1;

            if (!employee.isLeaveAllowed(days)) {
                throw new IllegalArgumentException(
                        "Leave exceeds employee policy.");
            }

            this.employee = employee;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public void approve() {
            if (status != LeaveStatus.PENDING) {
                System.out.println(
                        "Cannot approve a finalized request.");
                return;
            }

            status = LeaveStatus.APPROVED;

            System.out.printf(
                    "%s's leave request (%s to %s) approved.%n",
                    employee.getName(),
                    startDate,
                    endDate);
        }

        public void reject() {
            if (status != LeaveStatus.PENDING) {
                System.out.println(
                        "Cannot reject a finalized request.");
                return;
            }

            status = LeaveStatus.REJECTED;

            System.out.printf(
                    "%s's leave request (%s to %s) rejected.%n",
                    employee.getName(),
                    startDate,
                    endDate);
        }

        public void changeToPending() {
            if (status != LeaveStatus.PENDING) {
                System.out.printf(
                        "Cannot change leave request status from %s "
                                + "to Pending.%n",
                        status);
            }
        }

        public LeaveStatus getStatus() {
            return status;
        }
    }

    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest johnRequest = new LeaveRequest(
                john,
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 5));

        System.out.println(
                "Leave request submitted for John. Status: "
                        + johnRequest.getStatus());

        johnRequest.approve();

        LeaveRequest janeRequest = new LeaveRequest(
                jane,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 11));

        System.out.println(
                "Leave request submitted for Jane. Status: "
                        + janeRequest.getStatus());

        janeRequest.reject();
        johnRequest.changeToPending();
    }
}