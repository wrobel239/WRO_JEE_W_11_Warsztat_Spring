package pl.coderslab.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.book.Book;
import pl.coderslab.book.BookService;
import pl.coderslab.book.MemoryBookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    // dla wersji bez Interfejsu
//    private MemoryBookService memoryBookService;
    private BookService memoryBookService;

    @Autowired
    public BookController(BookService memoryBookService) {
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

    // rozwiązanie pierwotne
//    @GetMapping ("/{id}")
//    public Book showBook(@PathVariable long id){
//        return memoryBookService.findBook(id);
//    }

    @GetMapping ("/{id}")
    public Book showBook(@PathVariable long id){
        return memoryBookService.get(id).orElseThrow(() ->{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        });
    }

//    rozwiązanie pierwotne
//    @PutMapping("")
//    public void editBook(@RequestBody Book book){
//        memoryBookService.editBook(book);
//    }

    @PutMapping("")
    public void editBook(@RequestBody Book book){
        boolean ifUpdated = memoryBookService.updateBook(book);
        if (!ifUpdated){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not updated: entity not found"
            );
        }
    }

//    rozwiązanie pierwotne
//    @DeleteMapping("/{id}")
//    public void deleteBook(@PathVariable long id){
//        memoryBookService.deleteBook(id);
//    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        boolean ifDeleted = memoryBookService.removeBook(id);
        if (!ifDeleted){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not deleted: entity not found"
            );
        }
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

}
