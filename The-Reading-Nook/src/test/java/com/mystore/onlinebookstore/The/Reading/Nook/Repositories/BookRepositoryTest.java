package com.mystore.onlinebookstore.The.Reading.Nook.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Book;

@DataJpaTest
public class BookRepositoryTest {

//		    @Autowired
//		    private BookRepository bookRepository;
//	
//		    @Test
//		    public void testFindAllBooks() {
//		        List<Book> books = bookRepository.findAll();
//		        assertEquals(3, books.size()); // Assuming there are 3 books in the database
//		    }
//	
//		    @Test
//		    public void testFindBookById() {
//		        Long id = 1L; // Assuming there is a book with ID 1 in the database
//		        Optional<Book> book = bookRepository.findById(id);
//		        assertTrue(book.isPresent());
//		        assertEquals("Book 1", book.get().getTitle());
//		    }
//	
//		    @Test
//		    public void testFindNonExistentBook() {
//		        Long id = 99L; // Assuming there is no book with ID 99 in the database
//		        Optional<Book> book = bookRepository.findById(id);
//		        assertFalse(book.isPresent());
//		    }
//	
//		    @Test
//		    public void testSaveBook() {
//		        Book newBook = new Book("New Book", "Author", 20.0);
//		        Book savedBook = bookRepository.save(newBook);
//		        assertNotNull(savedBook.getId());
//		    }
//	
//		    @Test
//		    public void testUpdateBook() {
//		        Long id = 1L; // Assuming there is a book with ID 1 in the database
//		        Book book = bookRepository.findById(id).orElseThrow();
//		        book.setTitle("Updated Book");
//		        Book updatedBook = bookRepository.save(book);
//		        assertEquals("Updated Book", updatedBook.getTitle());
//		    }
//	
//		    @Test
//		    public void testDeleteBook() {
//		        Long id = 2L; // Assuming there is a book with ID 2 in the database
//		        bookRepository.deleteById(id);
//		        Optional<Book> deletedBook = bookRepository.findById(id);
//		        assertFalse(deletedBook.isPresent());
//		    }
}
