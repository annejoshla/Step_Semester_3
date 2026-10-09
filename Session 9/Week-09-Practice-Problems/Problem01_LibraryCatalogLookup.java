public class Problem01_LibraryCatalogLookup {
    static class Book {
        private final String isbn;
        private final String title;

        public Book(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }
    }

    public static String findBook(Book[] catalog, String targetIsbn) {
        int left = 0;
        int right = catalog.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int comparison = catalog[middle]
                    .getIsbn()
                    .compareTo(targetIsbn);

            if (comparison == 0) {
                return catalog[middle].getTitle();
            }

            if (comparison < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        Book[] catalog = {
                new Book("0001112223",
                        "Introduction to Algebra"),
                new Book("0002223334",
                        "Beginning Python"),
                new Book("0003334445",
                        "Classic Mythology"),
                new Book("0004445556",
                        "Data and Society"),
                new Book("0005556667",
                        "European History")
        };

        System.out.println(
                findBook(catalog, "0003334445"));

        System.out.println(
                findBook(catalog, "0009998887"));
    }
}