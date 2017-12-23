package ua.com.hav.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    static private Map<Long, Book> books;
    static private Long cnt = 0L;

    static {
        books = new HashMap<>();
        books.put(++cnt, new Book(cnt, "Book 1"));
        books.put(++cnt, new Book(cnt, "Book 2"));
        books.put(++cnt, new Book(cnt, "Book 3"));
        books.put(++cnt, new Book(cnt, "Book 4"));

    }

    private List<Book> bookList() {
        return  books.values().stream().collect(Collectors.toList());
    }


    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("books", bookList());
        return "list";
    }

    @RequestMapping("/save")
    public String save(Book book) {
        System.out.println("book = " + book);
        if (book.getId() == null) {
            book.setId(++cnt);
            books.put(cnt, book);
        } else {
            books.put(book.getId(), book);
        }
        return "redirect:/book/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Book book = bookList().stream().filter(b -> b.getId() == id).findAny().get();
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
        books.remove(id);
        return "redirect:/book/list";
    }
}
