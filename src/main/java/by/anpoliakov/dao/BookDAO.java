package by.anpoliakov.dao;

import by.anpoliakov.models.Book;
import by.anpoliakov.models.Person;
import by.anpoliakov.rowMappers.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        //Работа через самописный row mapper
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public Book show(int id) {
        //работа через уже готовый row mapper (который есть в spring)
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void create(Book book){
        jdbcTemplate.update("INSERT INTO Book (title, author, year) VALUES (?, ?, ?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE book_id=?",
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?",null, id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Person INNER JOIN " +
                "Book ON Book.person_id = Person.person_id WHERE Book.book_id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void assign(int book_id, int person_id) {
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE book_id = ?", person_id, book_id);
    }
}
