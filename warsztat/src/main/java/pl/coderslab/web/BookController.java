package pl.coderslab.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.book.Book;
import pl.coderslab.book.MemoryBookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private MemoryBookService memoryBookService;

    @Autowired
    public BookController(MemoryBookService memoryBookService) {
        this.memoryBookService = memoryBookService;
    }

    @GetMapping ("")
    public List<Book> showBooks(){
        return memoryBookService.getBooks();
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book){
        memoryBookService.addBook(book);
    }

    @GetMapping ("/{id}")
    public Book showBook(@PathVariable long id){
        return memoryBookService.findBook(id);
    }

    @PutMapping("")
    public void editBook(@RequestBody Book book){
        memoryBookService.editBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        memoryBookService.deleteBook(id);
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

}
