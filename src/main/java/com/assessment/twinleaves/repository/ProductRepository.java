package com.assessment.twinleaves.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.twinleaves.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByProductNameContainingIgnoreCase(String productName);
	
}
