package by.anpoliakov.controllers;

import by.anpoliakov.dao.BookDAO;
import by.anpoliakov.dao.PeopleDAO;
import by.anpoliakov.models.Book;
import by.anpoliakov.models.Person;
import by.anpoliakov.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleDAO peopleDAO;
    private final BookDAO bookDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PeopleDAO peopleDAO, BookDAO bookDAO, PersonValidator personValidator) {
        this.peopleDAO = peopleDAO;
        this.bookDAO = bookDAO;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", peopleDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model){
        model.addAttribute("person", peopleDAO.show(id));
        List<Book> list = peopleDAO.getBooksById(id);

        if(!(list.isEmpty())){
            model.addAttribute("books", peopleDAO.getBooksById(id));
        }
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleDAO.show(id));
        return "people/edit";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping()
    public String addPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){

        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "people/new";
        }

        peopleDAO.create(person);
        return "redirect:/people";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "people/edit";
        }

        peopleDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        peopleDAO.delete(id);
        return "redirect:/people";
    }
}
