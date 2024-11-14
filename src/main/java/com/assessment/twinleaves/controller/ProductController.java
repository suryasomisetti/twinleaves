package com.assessment.twinleaves.controller;

import com.assessment.twinleaves.dto.ProductRequest;
import com.assessment.twinleaves.entity.Product;
import com.assessment.twinleaves.exception.ResourceNotFoundException;
import com.assessment.twinleaves.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    
    /*
	 *  Question 1 : POST api: to create some couple of rows in all tables
	 */
    @Operation(summary = "Create a new Product", description = "Creates a new product.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Product created successfully")
    })
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest product) {
        Product createdProduct = productService.saveProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Product by ID", description = "Fetches a product by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product found"),
        @ApiResponse(responseCode = "404", description = "Product not found with provided ID")
    })
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) throws ResourceNotFoundException {
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Get all Products", description = "Fetches all products.")
    @ApiResponse(responseCode = "200", description = "List of all products")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Update Product", description = "Updates an existing product.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product updated successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found with provided ID")
    })
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product productDetails)
            throws ResourceNotFoundException {
        Product updatedProduct = productService.updateProduct(productId, productDetails);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @Operation(summary = "Delete Product", description = "Deletes a product.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found with provided ID")
    })
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) throws ResourceNotFoundException {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
