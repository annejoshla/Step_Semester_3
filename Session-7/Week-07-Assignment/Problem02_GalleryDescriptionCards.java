public class Problem02_GalleryDescriptionCards {
    static abstract class ArtPiece {
        private static int nextId = 1000;
        private final String pieceId;
        protected final String title;

        protected ArtPiece(String title) {
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Title cannot be blank.");
            }

            this.title = title;
            nextId++;
            this.pieceId = "ART-" + nextId;
        }

        public abstract String describe();

        public String getPieceId() {
            return pieceId;
        }
    }

    static class Painting extends ArtPiece {
        public Painting(String title) {
            super(title);
        }

        @Override
        public String describe() {
            return "Painting: " + title
                    + ", framed on canvas";
        }
    }

    static class Sculpture extends ArtPiece {
        public Sculpture(String title) {
            super(title);
        }

        @Override
        public String describe() {
            return "Sculpture: " + title
                    + ", carved from stone";
        }
    }

    public static void main(String[] args) {
        Painting painting = new Painting("Sunset Fields");
        Sculpture sculpture = new Sculpture("The Thinker II");

        System.out.println(painting.describe());
        System.out.println(sculpture.describe());
        System.out.println(painting.getPieceId());
        System.out.println(sculpture.getPieceId());
    }
}