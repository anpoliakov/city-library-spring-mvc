package by.anpoliakov.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
public class Book {
    private int book_id;

    @NotEmpty(message = "Книга должна иметь наименование!")
    @Size(min = 2, max = 50, message = "Наименование должно быть от 2 до 50 символов!")
    private String title;

    @NotEmpty(message = "У каждой книги есть автор, укажите его!")
    @Size(min = 2, max = 30, message = "Поле автор должно содержать от 2 до 30 символов")
    private String author;

    @Min(value = 1900, message = "Слишком старая книга :) Поищи что то после 1900 годов!")
    @Max(value = 2025, message = "Сейчас на улице только 2023 год! Ты не из будущего! ")
    private int year;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
