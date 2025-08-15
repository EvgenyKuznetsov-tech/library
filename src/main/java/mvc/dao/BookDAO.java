package mvc.dao;

import mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import mvc.models.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper(Book.class));
    }

    public void save(Book book) {
        Integer id = jdbcTemplate.queryForObject(
                "INSERT INTO Book (bookTitle, author, year) VALUES(?, ?, ?) RETURNING bookId",
                Integer.class,
                book.getBookTitle(),
                book.getAuthor(),
                book.getYear()
        );
        book.setBookId(id);
    }

    public void update(int bookId, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET bookTitle=?, author=?, year=? WHERE bookId=?", updatedBook.getBookTitle(), updatedBook.getAuthor(), updatedBook.getYear(), bookId);
    }

    public Book show(int bookId) {
        return jdbcTemplate.query(
                "SELECT b.*, p.personId, p.name, p.yearOfBirth " +
                        "FROM Book b LEFT JOIN Person p ON b.personId = p.personId " +
                        "WHERE b.bookId = ?",
                new Object[]{bookId},
                rs -> {
                    if (!rs.next()) return null;

                    Book book = new Book();
                    book.setBookId(rs.getInt("bookId"));
                    book.setBookTitle(rs.getString("bookTitle"));
                    book.setAuthor(rs.getString("author"));
                    book.setYear(rs.getInt("year"));

                    if (rs.getObject("personId") != null) {
                        Person person = new Person();
                        person.setPersonId(rs.getInt("personId"));
                        person.setName(rs.getString("name"));
                        person.setYearOfBirth(rs.getInt("yearOfBirth"));
                        book.setPerson(person);
                    }

                    return book;
                }
        );
    }

    public void delete(int bookId) {
        jdbcTemplate.update("DELETE FROM Book WHERE bookId=?", bookId);
    }

    public void assign(int bookId, int personId) {
        jdbcTemplate.update("UPDATE book SET personId = ? WHERE bookId = ?",
                personId, bookId);
    }

    public void release(int bookId) {
        jdbcTemplate.update("UPDATE Book SET personId = NULL WHERE bookId = ?", bookId);
    }

    public List<Book> getBooksByPersonId(int personId) {
        return jdbcTemplate.query(
                "SELECT * FROM Book WHERE personId = ?",
                new BeanPropertyRowMapper<>(Book.class),
                personId
        );
    }
}
