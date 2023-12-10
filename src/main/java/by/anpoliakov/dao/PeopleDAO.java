package by.anpoliakov.dao;

import by.anpoliakov.models.Book;
import by.anpoliakov.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PeopleDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PeopleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Optional<Person> showByFullName(String full_name){
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?",
                new Object[]{full_name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny(); //возвращает обёртку Optional
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void create(Person person){
        jdbcTemplate.update("INSERT INTO Person (full_name, birth_year) VALUES (?,?)",
            person.getFull_name(),
            person.getBirth_year());

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id = ?", id);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET full_name = ?, birth_year = ? WHERE person_id = ?",
                person.getFull_name(),
                person.getBirth_year(),
                id);
    }

    public List<Book> getBooksById(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
