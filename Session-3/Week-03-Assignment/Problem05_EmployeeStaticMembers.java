public class Problem05_EmployeeStaticMembers {
    static class Employee {
        String empName;
        double salary;

        static String companyName =
                "Bright Horizon Technologies";

        static int employeeCount = 0;

        Employee(String empName, double salary) {
            this.empName = empName;
            this.salary = salary;
            employeeCount++;
        }

        static void printCompanyInfo() {
            System.out.println(companyName);
            System.out.println("Employees on record: " + employeeCount);
        }
    }

    public static void main(String[] args) {
        Employee firstEmployee =
                new Employee("Divya", 65000);

        Employee secondEmployee =
                new Employee("Arjun", 0);

        Employee thirdEmployee =
                new Employee("Karthik", 55000);

        System.out.println("3 Employee objects created");

        Employee.printCompanyInfo();
    }
}