package ua.com.hav.web;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.hav.BookService;
import ua.com.hav.model.Book;

import java.util.List;

/**
 * Created by Sunny on 16.10.2017.
 */
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    private BookService bookService;
    private Gson gson = new Gson();

    @RequestMapping("/{search}")
    @ResponseBody
    public String search(@PathVariable String search) {
        List<Book> list = bookService.find(search);
        return gson.toJson(list);
    }
}
