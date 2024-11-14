package com.assessment.twinleaves.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assessment.twinleaves.dto.ProductRequest;
import com.assessment.twinleaves.entity.Product;
import com.assessment.twinleaves.exception.ResourceNotFoundException;
import com.assessment.twinleaves.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productrepository) {
		this.productRepository = productrepository;
	}

	public Product saveProduct(ProductRequest productRequest) {
		Product product = new Product();
		product.setProductName(productRequest.getProductName());
		return productRepository.save(product);
	}

	public Product getProductById(Long id) throws ResourceNotFoundException {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product updateProduct(Long id, Product productDetails) throws ResourceNotFoundException {
		Product product = getProductById(id);
		product.setProductName(productDetails.getProductName());
		return productRepository.save(product);
	}

	public void deleteProduct(Long id) throws ResourceNotFoundException {
		Product product = getProductById(id);
		productRepository.delete(product);
	}

	public List<Product> searchProducts(String productName) {
		return productRepository.findByProductNameContainingIgnoreCase(productName);
	}

}
