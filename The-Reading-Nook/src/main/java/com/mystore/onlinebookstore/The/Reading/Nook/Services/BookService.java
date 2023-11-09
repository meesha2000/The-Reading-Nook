package com.mystore.onlinebookstore.The.Reading.Nook.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Book;
import com.mystore.onlinebookstore.The.Reading.Nook.Repositories.BookRepository;


@Service
public class BookService {

	private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> getSortedBooks(String sortBy, boolean ascending) {
    	
        // Assuming the actual field name for price is "price"
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return bookRepository.findAll(sort);
    }
    
    public List<Book> searchBooks(String query) {
		
		return bookRepository.findByTitleContainingOrAuthorContaining(query, query);
	}
    
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> updateBook(Long id, Book updatedBook) {
    	
    	Optional<Book> existingBook = getBookById(id);
        
        if (existingBook.isPresent()) {
            Book bookToUpdate = existingBook.get();

         // Update all fields that are present in the user-passed JSON
            if (updatedBook.getTitle() != null) {
                bookToUpdate.setTitle(updatedBook.getTitle());
            }
            if (updatedBook.getAuthor() != null) {
                bookToUpdate.setAuthor(updatedBook.getAuthor());
            }
            if (updatedBook.getPrice() > 0) {
                bookToUpdate.setPrice(updatedBook.getPrice());
            }
            if (updatedBook.getIsbn() != null) {
                bookToUpdate.setIsbn(updatedBook.getIsbn());
            }
            if (updatedBook.getDescription() != null) {
                bookToUpdate.setDescription(updatedBook.getDescription());
            }
            if (updatedBook.getGenre() != null) {
                bookToUpdate.setGenre(updatedBook.getGenre());
            }
            if (updatedBook.getPublisher() != null) {
                bookToUpdate.setPublisher(updatedBook.getPublisher());
            }
            if (updatedBook.getYear() > 0) {
                bookToUpdate.setYear(updatedBook.getYear());
            }
            if (updatedBook.getCoverImage() != null) {
                bookToUpdate.setCoverImage(updatedBook.getCoverImage());
            }

            // Ensure the ID is set
            bookToUpdate.setId(id);

            return Optional.of(bookRepository.save(bookToUpdate));
        }
        
        return Optional.empty(); // Book not found
    }

    public boolean deleteBook(Long id) {
        if (getBookById(id).isPresent()) {
            bookRepository.deleteById(id);
            return true;
        }
        return false; // Book not found
    }

}
