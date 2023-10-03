package by.anpoliakov.util;

import by.anpoliakov.dao.PeopleDAO;
import by.anpoliakov.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PeopleDAO peopleDAO;

    @Autowired
    public PersonValidator(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if(peopleDAO.showByFullName(person.getFull_name()).isPresent()){
            errors.rejectValue("full_name", "", "Это ФИО уже существует!");
        }
    }
}
