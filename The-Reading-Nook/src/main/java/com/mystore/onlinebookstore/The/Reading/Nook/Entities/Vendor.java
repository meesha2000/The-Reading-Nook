package com.mystore.onlinebookstore.The.Reading.Nook.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="vendors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendor extends Person{
	
	// Additional fields specific to vendors
    private String companyName;
    private String taxIdentificationNumber;

}
