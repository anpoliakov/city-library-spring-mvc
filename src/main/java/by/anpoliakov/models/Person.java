package by.anpoliakov.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
public class Person {
    private int person_id;

    @NotEmpty(message = "ФИО не должно быть пустым!")
    @Size(min = 2, max = 30, message = "ФИО должно состоять от 2 до 30 символов!")
    private String full_name;

    @Min(value = 1900, message = "Не ври:) Нельзя выбрать год рождения раньше 1900!")
    private int birth_year;

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }
}
