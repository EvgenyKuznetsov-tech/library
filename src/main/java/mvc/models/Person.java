package mvc.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Person {
    private int personId;

    @NotEmpty(message = "Поле ФИО не может быть пустым")
    //@Pattern(regexp = "[A-z]\\w+, [A-z]\\w+, [A-z]\\w+")
    private String name;
    private int yearOfBirth;
    private List<Book> books;
    public Person() {
    }

    public Person(int personId, String name, int yearOfBirth, List<Book> books) {
        this.personId = personId;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.books = books;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
