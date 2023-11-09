package com.mystore.onlinebookstore.The.Reading.Nook.Entities;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import jakarta.persistence.*;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotEmpty
    private String username;
	
	@Size(min=8, message = "Password must be at least 8 characters long")
    private String password;

	@NotEmpty
    private String firstName;
	@NotEmpty
    private String lastName;
	
    private LocalDate dateOfBirth;
    private String mobile;
    private String address;
    
    @Email
    private String email;
}

