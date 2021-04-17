package pl.coderslab.book;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class MemoryBookService implements BookService {
    private List<Book> books;
    private static long nextId = 4L;

    public MemoryBookService() {
        books = new ArrayList<>();
        books.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        books.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        books.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book findBook(long id) {
        Book book = null;
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book bookInsideLoop = iterator.next();
            if (bookInsideLoop.getId() == id) {
                book = bookInsideLoop;
            }
        }
        return book;
    }

    //    wersja z przykładowego rozwiązania z Optional
    public Optional<Book> get(long id) {
        return books.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
    }

    public void editBook(Book book) {
        long id = book.getId();
        int indexOf = -1;
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book bookInsideLoop = iterator.next();
            if (bookInsideLoop.getId() == id) {
                indexOf = books.indexOf(bookInsideLoop);
            }
        }
        if (indexOf >= 0) {
            books.set(indexOf, book);
        }
    }

//    wersja z przykładowego rozwiązania z Optional
    public boolean updateBook(Book book){
        Optional<Book> bookToUpdate = get(book.getId());
        if (bookToUpdate.isPresent()){
            int index = books.indexOf(bookToUpdate.get());
            books.set(index, book);
            return true;
        }
        return false;
    }

    //    wersja z przykładowego rozwiązania z Optional
    public boolean removeBook(long id) {
        Optional<Book> book = get(id);
        if (book.isPresent()) {
            books.remove(book.get());
            return true;
        }
        return false;
    }

    public void deleteBook(long id) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
            }
        }
    }

    public void addBook(Book book) {
        book.setId(nextId);
        nextId++;
        books.add(book);
    }

}
