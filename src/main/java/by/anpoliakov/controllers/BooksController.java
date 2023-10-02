package by.anpoliakov.controllers;

import by.anpoliakov.dao.BookDAO;
import by.anpoliakov.dao.PeopleDAO;
import by.anpoliakov.models.Book;
import by.anpoliakov.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PeopleDAO peopleDAO;

    public BooksController(BookDAO bookDAO, PeopleDAO peopleDAO) {
        this.bookDAO = bookDAO;
        this.peopleDAO = peopleDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){

        model.addAttribute("book", bookDAO.show(id));
        Optional<Person> bookOwner = bookDAO.getBookOwner(id);

        if(bookOwner.isPresent()){
            model.addAttribute("owner", bookOwner.get());
        }else {
            model.addAttribute("people", peopleDAO.index());
        }

        //в представлении person, book и (owner или people)
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute Book book){
        return "books/new";
    }

    @PostMapping()
    public String addBook(@ModelAttribute Book book){
        bookDAO.create(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id")int id, @ModelAttribute("book") Book book){
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person assignPerson){
        bookDAO.assign(id, assignPerson.getPerson_id());
        return "redirect:/books/" + id;
    }
}
