package PPEVM.lab3;

public class Book {
    private int id;
    private String name;
    private String authors;
    private String publishing;
    private int yearOfPublishing;
    private int pages;
    private int price;
    private String binding;

    public Book() {
        id = 0;
        name = "Undefined";
        authors = "Undefined";
        publishing = "undefined";
        yearOfPublishing = 0;
        pages = 0;
        price = 0;
        binding = "Undefined";
    }

    public Book(int id, String name, String authors, String publishing, int yearOfPublishing, int pages, int price, String binding) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.publishing = publishing;
        this.yearOfPublishing = yearOfPublishing;
        this.pages = pages;
        this.price = price;
        this.binding = binding;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public int getId() {
        return id;
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

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public int getPages() {
        return pages;
    }

    public int getPrice() {
        return price;
    }

    public String getBinding() {
        return binding;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ",\nНазвание: '" + name + '\'' +
                ",\nАвтор(ы): '" + authors + '\'' +
                ",\nИздательство: '" + publishing + '\'' +
                ",\nГод издания: " + yearOfPublishing +
                ",\nКоличество страниц: " + pages +
                ",\nПереплет: '" + binding + '\'' +
                ",\nЦена: " + price + "\n";
    }

    public boolean checkAuthor(String author) {
        return this.authors.contains(author);
    }

    public boolean checkPublishing(String publishing) {
        return this.publishing.equals(publishing);
    }

    public boolean checkYear(int year) {
        return this.yearOfPublishing >= year;
    }
}
