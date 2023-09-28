package by.anpoliakov.controllers;

import by.anpoliakov.dao.BookDAO;
import by.anpoliakov.dao.PeopleDAO;
import by.anpoliakov.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleDAO peopleDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PeopleController(PeopleDAO peopleDAO, BookDAO bookDAO) {
        this.peopleDAO = peopleDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", peopleDAO.index());
        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping()
    public String addPerson(@ModelAttribute("person") Person person){
        peopleDAO.create(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model){
        model.addAttribute("person", peopleDAO.show(id));
        model.addAttribute("books", peopleDAO.getBooksById(id));
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model){
        model.addAttribute("person", peopleDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute("person") Person person){
        peopleDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        peopleDAO.delete(id);
        return "redirect:/people";
    }
}
