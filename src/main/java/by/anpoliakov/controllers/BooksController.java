package by.anpoliakov.controllers;

import by.anpoliakov.dao.BookDAO;
import by.anpoliakov.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;

    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
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
}
