public class Problem01_PlacementRecord {
    static class PlacementRecord {
        String studentName;
        String company;
        double packageLpa;

        PlacementRecord(String studentName, String company, double packageLpa) {
            this.studentName = studentName;
            this.company = company;
            this.packageLpa = packageLpa;
        }

        void printRecord() {
            System.out.printf("%s -> %s @ %.1f LPA%n",
                    studentName, company, packageLpa);
        }
    }

    public static void main(String[] args) {
        PlacementRecord[] records = {
                new PlacementRecord("Ravi", "TCS", 4.5),
                new PlacementRecord("Anitha", "Zoho", 6.2),
                new PlacementRecord("Karthik", "Infosys", 4.0)
        };

        for (PlacementRecord record : records) {
            record.printRecord();
        }
    }
}