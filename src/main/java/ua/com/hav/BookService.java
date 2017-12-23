package ua.com.hav;

import org.springframework.stereotype.Service;
import ua.com.hav.model.Book;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class BookService {

    private Map<Long, Book> bookMap = new HashMap<>();
    private Long idCnt = 1L;

    @PostConstruct
    public void init() {
        File file = new File("list.txt");
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write(123);
//            fileOutputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            Scanner scanner = new Scanner(new FileReader(file));
            while (scanner.hasNext()) {
                Book book = new Book(idCnt, scanner.nextLine());
                bookMap.put(idCnt++, book);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bookMap.values().stream().forEach(System.out::println);
    }

    public List<Book> find(String search) {
        return bookMap.values().stream().filter(b -> b.getName().toLowerCase().contains(search)).collect(toList());
    }

    public List<Book> findAll() {
        return bookMap.values().stream().collect(toList());
    }

    public Book find(Long id) {
        Optional<Book> any = bookMap.values().stream().filter(b -> b.getId() == id).findAny();
        return any.isPresent() ? any.get() : null;
    }

    public void remove(Long id) {
        bookMap.remove(id);
    }

    public void save(Book book) {
        Long id = book.getId();
        if (id == null || id == 0) {
            id = idCnt++;
            book.setId(id);
        }
        bookMap.put(id, book);
    }
}
