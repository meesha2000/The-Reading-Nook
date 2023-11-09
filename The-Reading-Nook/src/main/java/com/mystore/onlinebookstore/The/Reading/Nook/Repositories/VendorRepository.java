package com.mystore.onlinebookstore.The.Reading.Nook.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Customer;
import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>{

	Optional<Vendor> findByUsername(String username);
}
