package PPEVM.lab3;

public class Program {
    class Book {
        int id, year, pages, cost;
        String name, authors, publishing, wrap;
        Book () {
            this(
                0,
                "Undefined",
                "Undefined",
                "Undefined",
                0,
                0,
                0,
                "Undefined"
            );
        };
        Book (int id, String name, String authors, String publishing, int year, int pages, int cost, String wrap) {
            this.authors = authors;
            this.cost = cost;
            this.id = id;
            this.name = name;
            this.pages = pages;
            this.publishing = publishing;
            this.wrap = wrap;
            this.year = year;
        }

        public int getId() {
            return id;
        }

        public int getYear() {
            return year;
        }

        public int getPages() {
            return pages;
        }

        public int getCost() {
            return cost;
        }

        public String getName() {
            return name;
        }

        public String getAuthors() {
            return authors;
        }

        public String getPublishing() {
            return publishing;
        }

        public String getWrap() {
            return wrap;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAuthors(String authors) {
            this.authors = authors;
        }

        public void setPublishing(String publishing) {
            this.publishing = publishing;
        }

        public void setWrap(String wrap) {
            this.wrap = wrap;
        }
    }

    public static void main(String[] args) {

    }
}
