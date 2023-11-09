package com.mystore.onlinebookstore.The.Reading.Nook.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Book;
import com.mystore.onlinebookstore.The.Reading.Nook.Repositories.BookRepository;

@SpringBootTest
public class BookServiceTest {

	@InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(new Book("Book 1", "Author 1", 10.0),
                new Book("Book 2", "Author 2", 15.0)));

        List<Book> books = bookService.getAllBooks();
        assertEquals(2, books.size());
    }

    @Test
    public void testGetBookById() {
    	
        when(bookRepository.findById(1L)).thenReturn(Optional.of(new Book("Book 1", "Author 1", 10.0)));

        Optional<Book> book = bookService.getBookById(1L);
        assertTrue(book.isPresent());
        assertEquals("Book 1", book.get().getTitle());
    }

    @Test
    public void testGetNonExistentBook() {
    	
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Book> book = bookService.getBookById(99L);
        assertFalse(book.isPresent());
    }

    @Test
    public void testCreateBook() {
    	
    	Book newBook = Book.builder()
                .title("New Book")
                .author("Author")
                .price(20.0)
                .build();

    	// When saving the book, return a book with an ID
        Book bookWithId = Book.builder()
                .id(1L)  // Set a sample ID (you should adapt this according to your database setup)
                .title("New Book")
                .author("Author")
                .price(20.0)
                .build();
        
        when(bookRepository.save(any())).thenReturn(bookWithId);

        Book createdBook = bookService.createBook(newBook);
        
        assertNotNull(createdBook.getId());
    }

    @Test
    public void testUpdateBook() {
    	
        Book book = new Book("Book 1", "Author 1", 10.0);
        
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any())).thenReturn(book);

        Optional<Book> updatedBook = bookService.updateBook(1L, book);
        assertTrue(updatedBook.isPresent());
        assertEquals("Book 1", updatedBook.get().getTitle());
    }

    @Test
    public void testUpdateNonExistentBook() {
    	
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Book> updatedBook = bookService.updateBook(99L, new Book("New Book", "Author", 20.0));
        assertFalse(updatedBook.isPresent());
    }

    @Test
    public void testDeleteBook() {
    	
//    	// Simulate that the book with ID 2L exists
//        when(bookRepository.existsById(2L)).thenReturn(true);
//
//        // Simulate the deletion of the book with ID 2L
//        // This would be called by your actual service implementation
//        bookRepository.deleteById(2L);
//
//        boolean isDeleted = bookService.deleteBook(2L);
//
//        assertTrue(isDeleted);
    }

    @Test
    public void testDeleteNonExistentBook() {
    	
        when(bookRepository.existsById(99L)).thenReturn(false);

        boolean isDeleted = bookService.deleteBook(99L);
        assertFalse(isDeleted);
    }
}
