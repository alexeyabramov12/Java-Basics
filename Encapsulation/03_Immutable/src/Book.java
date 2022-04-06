public class Book {

    private final String name;
    private final String author;
    private final int amountOfPages;
    private final int numberIsbn;

    public Book(String name, String author, int amountOfPages, int numberIsbn) {
        this.name = name;
        this.author = author;
        this.amountOfPages = amountOfPages;
        this.numberIsbn = numberIsbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getAmountOfPages() {
        return amountOfPages;
    }

    public int getNumberIsbn() {
        return numberIsbn;
    }

}
