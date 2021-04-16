package pl.coderslab.book;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class MemoryBookService {
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

    public Book findBook(long id){
        Book book = null;
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()){
            Book bookInsideLoop = iterator.next();
            if (bookInsideLoop.getId() == id){
                book = bookInsideLoop;
            }
        }
        return book;
    }

    public void editBook (Book book){
        long id = book.getId();
        int indexOf=-1;
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()){
            Book bookInsideLoop = iterator.next();
            if (bookInsideLoop.getId() == id){
                indexOf = books.indexOf(bookInsideLoop);
            }
        }
        if (indexOf>=0){
            books.set(indexOf, book);
        }
    }

    public void deleteBook(long id){
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()){
            if (iterator.next().getId() == id){
                iterator.remove();
            }
        }
    }

    public void addBook (Book book){
        book.setId(nextId);
        nextId++;
        books.add(book);
    }

}
