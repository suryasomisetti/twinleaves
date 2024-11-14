package com.assessment.twinleaves.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.twinleaves.dto.GtinRequest;
import com.assessment.twinleaves.entity.Gtin;
import com.assessment.twinleaves.exception.ResourceNotFoundException;
import com.assessment.twinleaves.service.GtinService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/gtins")
public class GtinController {

    private final GtinService gtinService;

    public GtinController(GtinService gtinService) {
        this.gtinService = gtinService;
    }

    
    /*
	 *  Question 1 : POST api: to create some couple of rows in all tables
	 */
    @Operation(summary = "Create a new GTIN", 
    		description = "Creates a new GTIN and links it with a product and Also links optional batches if provided.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created GTIN"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PostMapping
    public ResponseEntity<Gtin> createGtin(@RequestBody GtinRequest gtinRequest) throws ResourceNotFoundException {
    	Gtin createdGtin = gtinService.saveGtin(gtinRequest);
        return new ResponseEntity<>(createdGtin, HttpStatus.CREATED);
    }
    
    
    @Operation(summary = "Get all GTINs", description = "Retrieve a list of all GTINs.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all GTINs")
    @GetMapping
    public ResponseEntity<List<Gtin>> getAllGtins() {
        List<Gtin> gtins = gtinService.getAllGtins();
        return new ResponseEntity<>(gtins, HttpStatus.OK);
    }
    
	/*
	 * 	Question 2 : GET apis: to query based on gtins
	 */
    @Operation(summary = "Get GTIN by ID", description = "Fetch a GTIN by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved GTIN"),
        @ApiResponse(responseCode = "404", description = "GTIN not found")
    })
    @GetMapping("/{gtinId}")
    public ResponseEntity<Gtin> getGtinById(@PathVariable Long gtinId) throws ResourceNotFoundException {
    	Gtin gtin = gtinService.getGtinById(gtinId);
        return new ResponseEntity<>(gtin, HttpStatus.OK);
    }

    @Operation(summary = "Get GTIN by GTIN value", description = "Retrieve a GTIN by its unique GTIN value.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved GTIN"),
        @ApiResponse(responseCode = "404", description = "GTIN not found")
    })
    @GetMapping("/gtin/{gtin}")
    public ResponseEntity<Gtin> getGtinByGtin(@PathVariable String gtin) throws ResourceNotFoundException {
    	Gtin gtinEntity = gtinService.getGtinByGtin(gtin);
        return new ResponseEntity<>(gtinEntity, HttpStatus.OK);
    }

    @Operation(summary = "Get GTIN by Product ID", 
    		description = "Retrieve a GTIN associated with a specific product ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved GTIN by product ID"),
        @ApiResponse(responseCode = "404", description = "Product not found or GTIN not found")
    })
    @GetMapping("/product/{productId}")
    public ResponseEntity<Gtin> getGtinByProductId(@PathVariable Long productId) throws ResourceNotFoundException {
    	Gtin gtin = gtinService.getGtinByProductId(productId);
        return new ResponseEntity<>(gtin, HttpStatus.OK);
    }

    @Operation(summary = "Search GTINs by GTIN containing a substring", 
    		description = "Search for GTINs that contain the provided substring.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved matching GTINs")
    @GetMapping("/search/contains")
    public ResponseEntity<List<Gtin>> getGtinsByGtinContainingIgnoreCase(@RequestParam String gtin) {
        List<Gtin> gtins = gtinService.getGtinsByGtinContainingIgnoreCase(gtin);
        return new ResponseEntity<>(gtins, HttpStatus.OK);
    }

    @Operation(summary = "Search GTINs by GTIN prefix", 
    		description = "Search for GTINs that start with the provided prefix.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved matching GTINs")
    @GetMapping("/search/start")
    public ResponseEntity<List<Gtin>> getGtinsByGtinStartingWith(@RequestParam String gtinPrefix) {
        List<Gtin> gtins = gtinService.getGtinsByGtinStartingWith(gtinPrefix);
        return new ResponseEntity<>(gtins, HttpStatus.OK);
    }

    @Operation(summary = "Search GTINs by GTIN suffix", 
    		description = "Search for GTINs that end with the provided suffix.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved matching GTINs")
    @GetMapping("/search/end")
    public ResponseEntity<List<Gtin>> getGtinsByGtinEndingWith(@RequestParam String gtinSuffix) {
        List<Gtin> gtins = gtinService.getGtinsByGtinEndingWith(gtinSuffix);
        return new ResponseEntity<>(gtins, HttpStatus.OK);
    }
	/* Question 2 Ends */
    

    @Operation(summary = "Get GTINs with negative or zero quantity", 
    		description = "Retrieve a list of GTINs that have a negative or zero quantity.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved GTINs with negative or zero quantity")
    @GetMapping("/quantity/negative_or_zero")
    public ResponseEntity<List<Gtin>> getGtinsWithNegativeOrZeroQuantity() {
        List<Gtin> gtins = gtinService.getGtinsWithNegativeOrZeroQuantity();
        return new ResponseEntity<>(gtins, HttpStatus.OK);
    }
    

	/*
	 * 	Question 3 -> Section a: To get all the gtins containing batches with positive available quantity
	 */
    @Operation(summary = "Get GTINs with positive quantity", description = "Retrieve a list of GTINs that have a positive quantity.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved GTINs with positive quantity")
    @GetMapping("/quantity/positive")
    public ResponseEntity<List<Gtin>> getGtinsWithPositiveQuantity() {
        List<Gtin> gtins = gtinService.getGtinsWithPositiveQuantity();
        return new ResponseEntity<>(gtins, HttpStatus.OK);
    }
}
