package mvc.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Book {
    private int bookId;
    @NotEmpty(message = "Поле название книги не может быть пустым")
    private String bookTitle;
    @NotEmpty(message = "Поле автор не может быть пустым")
    private String author;
    @NotNull(message = "Поле год издания не может быть пустым")
    private int year;
    private Person person;

    public Book(int bookId, String bookTitle, String author, int year, Person person) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.year = year;
        this.person = person;
    }

    public Book() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
