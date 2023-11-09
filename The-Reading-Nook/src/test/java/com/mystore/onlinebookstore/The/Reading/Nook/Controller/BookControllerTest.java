package com.mystore.onlinebookstore.The.Reading.Nook.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mystore.onlinebookstore.The.Reading.Nook.Controllers.BookController;
import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Book;
import com.mystore.onlinebookstore.The.Reading.Nook.Services.BookService;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

	private MockMvc mockMvc;
    private BookController bookController;
    private BookService bookService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    	
        bookService = Mockito.mock(BookService.class); // or use @Mock annotatiions
        
        bookController = new BookController(bookService);
        
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        
        objectMapper = new ObjectMapper();
    }
    
    @Test
    void testListBooks() throws Exception {
    	
        List<Book> books = List.of(
            new Book("Book 1", "Author 1", 10.0),
            new Book("Book 2", "Author 2", 15.0)
        );

        Mockito.when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/v1/books/getAllBooks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Book 1"))
                .andExpect(jsonPath("$[1].title").value("Book 2"));
    }
    
    @Test
    void testGetBookById() throws Exception {
    	
        long bookId = 1L;
        Book book = new Book("Book 1", "Author 1", 10.0);

        Mockito.when(bookService.getBookById(bookId)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/v1/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Book 1"))
                .andExpect(jsonPath("$.author").value("Author 1"));
    }
    
    @Test
    void testGetNonExistentBook() throws Exception {
    	
        long nonExistentBookId = 99L;

        Mockito.when(bookService.getBookById(nonExistentBookId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/books/{id}", nonExistentBookId))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void testCreateBook() throws Exception {
    	
        Book newBook = new Book("New Book", "Author", 20.0);

        Mockito.when(bookService.createBook(Mockito.any(Book.class))).thenReturn(newBook);

        mockMvc.perform(post("/api/v1/books/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("New Book"))
                .andExpect(jsonPath("$.author").value("Author"));
    }
    
    @Test
    void testUpdateBook() throws Exception {
        long bookId = 1L;
        Book updatedBook = new Book("Updated Book", "Updated Author", 30.0);

        Mockito.when(bookService.updateBook(bookId, updatedBook)).thenReturn(Optional.of(updatedBook));

        mockMvc.perform(put("/api/v1/books/updateBook/{id}", bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Updated Book"))
                .andExpect(jsonPath("$.author").value("Updated Author"));
    }
    
    @Test
    void testUpdateNonExistentBook() throws Exception {
        long nonExistentBookId = 99L;
        Book updatedBook = new Book("Updated Book", "Updated Author", 30.0);

        Mockito.when(bookService.updateBook(nonExistentBookId, updatedBook)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/v1/books/updateBook/{id}", nonExistentBookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void testDeleteBook() throws Exception {
        long bookId = 2L;

        Mockito.when(bookService.deleteBook(bookId)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/books/deleteBook/{id}", bookId))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteNonExistentBook() throws Exception {
        long nonExistentBookId = 99L;

        Mockito.when(bookService.deleteBook(nonExistentBookId)).thenReturn(false);

        mockMvc.perform(delete("/api/v1/books/deleteBook/{id}", nonExistentBookId))
                .andExpect(status().isNotFound());
    }
}
