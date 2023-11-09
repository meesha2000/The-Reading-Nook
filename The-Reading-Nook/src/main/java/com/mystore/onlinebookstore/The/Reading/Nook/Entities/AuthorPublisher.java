package com.mystore.onlinebookstore.The.Reading.Nook.Entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorPublisher extends Person{

	// Additional fields specific to authors/publishers
    private String bio;
    private List<String> publishedBooks;
    
    
}
