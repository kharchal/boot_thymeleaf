package ua.com.hav.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.hav.BookService;
import ua.com.hav.model.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Sunny on 16.10.2017.
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "list";
    }

    @RequestMapping("/save")
    public String save(Book book) {
        System.out.println("book = " + book);
        bookService.save(book);
        return "redirect:/book/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Book book = bookService.find(id);
        model.addAttribute("book", book);
        return "form";
    }

    @RequestMapping("/new")
    public String empty(Model model) {
        model.addAttribute("book", new Book());
        return "form";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        System.out.println("delete " + id);
        bookService.remove(id);
        return "redirect:/book/list";
    }
}
