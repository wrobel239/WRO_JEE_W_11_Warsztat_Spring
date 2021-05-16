package pl.coderslab.book;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.coderslab.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JpaBookService implements BookService {

    private final BookRepository bookRepository;

    public JpaBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBook(long id) {
        if (bookRepository.findById(id).isPresent()){
            return bookRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void editBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Optional<Book> get(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public boolean removeBook(long id) {
        bookRepository.deleteById(id);
        return !(get(id).isPresent());
    }

    @Override
    public boolean updateBook(Book book) {
        bookRepository.save(book);
        if(get(book.getId()).isPresent()){
            return true;
        }
        return false;
    }
}
