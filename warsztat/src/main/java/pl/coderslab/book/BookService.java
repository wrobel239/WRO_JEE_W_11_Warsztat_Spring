package pl.coderslab.book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getBooks();
    Book findBook(long id);
    void editBook(Book book);
    void deleteBook(long id);
    void addBook(Book book);
    Optional<Book> get(long id);
    boolean removeBook(long id);
    boolean updateBook(Book book);
}
