package com.mystore.onlinebookstore.The.Reading.Nook.Repositories;

import java.util.List;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	
	List<Book> findByTitleContainingOrAuthorContaining(String title, String author);
	

}
