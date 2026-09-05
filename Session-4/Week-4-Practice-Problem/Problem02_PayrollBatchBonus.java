public class Problem02_PayrollBatchBonus {
    static class Employee {
        String employeeId;
        double salary;

        public Employee(String employeeId, double salary) {
            this.employeeId = employeeId;
            this.salary = salary;
        }

        public void raiseSalary(double salary) {
            this.salary += salary;
        }

        public void printSalary() {
            System.out.println(employeeId
                    + " | Final Salary: Rs " + salary);
        }
    }

    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("E-101", 40000),
                new Employee("E-102", 55000),
                new Employee("E-103", 62000),
                new Employee("E-104", 48000)
        };

        for (Employee employee : employees) {
            employee.raiseSalary(5000);
            employee.printSalary();
        }
    }
}