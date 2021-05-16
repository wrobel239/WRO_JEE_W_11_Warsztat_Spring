package pl.coderslab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.book.Book;
import pl.coderslab.book.BookService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/books")
public class ManageBookController {

    private final BookService bookService;

    @Autowired
    public ManageBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public String showBooks (Model model){
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "bookList";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "addBookForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addBookForm";
        }
        bookService.addBook(book);
        return "redirect:/admin/books/all";
    }

    // tutaj mogłoby być przekierowanie na formularz od add, tylko dodać pole hidden
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBookForm(@PathVariable long id, Model model) {
        Optional<Book> book = bookService.get(id);
        if (book.isPresent()){
            model.addAttribute("book", book.get());
            return "editBookForm";
        }
        return "redirect:/admin/books/all";
    }

    // tutaj w sumie mogłoby być przekierowanie na akcję add
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editBook(@ModelAttribute @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editBookForm";
        }
        bookService.editBook(book);
        return "redirect:/admin/books/all";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable long id) {
        Optional<Book> book = bookService.get(id);
        if (book.isPresent()){
            bookService.deleteBook(id);
        }
        return "redirect:/admin/books/all";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showBook(@PathVariable long id, Model model) {
        // tutaj mogłoby być od razu:
//        model.addAttribute("book", bookService.get(id).orElseThrow(EntityNotFoundException::new));
//        return "showBook";
        Optional<Book> book = bookService.get(id);
        if (book.isPresent()){
            model.addAttribute("book", book.get());
            return "showBook";
        } else {
            throw new EntityNotFoundException("entity not found");
        }
    }
}
