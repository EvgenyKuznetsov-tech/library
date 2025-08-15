package mvc.dao;

import mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int personId) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE personId=?", new Object[]{personId}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public void save(Person person) {
        Integer id = jdbcTemplate.queryForObject(
                "INSERT INTO Person(name, yearOfBirth) VALUES(?, ?) RETURNING personId",
                Integer.class,
                person.getName(),
                person.getYearOfBirth()
        );
        person.setPersonId(id);
    }

    public void update(int personId, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, yearOfBirth=? WHERE personId=?", updatedPerson.getName(),
                updatedPerson.getYearOfBirth(), personId);
    }

    public void delete(int personId) {
        jdbcTemplate.update("DELETE FROM Person WHERE personId=?", personId);
    }
}
