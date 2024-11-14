package com.assessment.twinleaves.controller;

import com.assessment.twinleaves.dto.BatchRequest;
import com.assessment.twinleaves.entity.Batch;
import com.assessment.twinleaves.exception.ResourceNotFoundException;
import com.assessment.twinleaves.service.BatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

	/*
	 *  Question 1 : POST api: to create some couple of rows in all tables
	 */
    @Operation(summary = "Create a new Batch", description = "Creates a new Batch and links it with a product and GTINs.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Batch created successfully"),
        @ApiResponse(responseCode = "404", description = "Product or GTIN not found")
    })
    @PostMapping
    public ResponseEntity<Batch> createBatch(@RequestBody BatchRequest batchRequest) throws ResourceNotFoundException {
        Batch createdBatch = batchService.saveBatch(batchRequest);
        return new ResponseEntity<>(createdBatch, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Batch by ID", description = "Fetches a batch by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Batch found"),
        @ApiResponse(responseCode = "404", description = "Batch not found with provided ID")
    })
    @GetMapping("/{batchId}")
    public ResponseEntity<Batch> getBatchById(@PathVariable Long batchId) throws ResourceNotFoundException {
        Batch batch = batchService.getBatchById(batchId);
        return new ResponseEntity<>(batch, HttpStatus.OK);
    }

    @Operation(summary = "Get all batches", description = "Fetches all batches.")
    @ApiResponse(responseCode = "200", description = "List of all batches")
    @GetMapping
    public ResponseEntity<List<Batch>> getAllBatches() {
        List<Batch> batches = batchService.getAllBatches();
        return new ResponseEntity<>(batches, HttpStatus.OK);
    }

    @Operation(summary = "Get Batches by GTIN", description = "Fetches batches associated with a GTIN number.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of batches for the GTIN"),
        @ApiResponse(responseCode = "404", description = "GTIN not found")
    })
    @GetMapping("/gtin/{gtinNumber}")
    public ResponseEntity<List<Batch>> getBatchesByGtinNumber(@PathVariable String gtinNumber) {
        List<Batch> batches = batchService.getBatchesByGtinNumber(gtinNumber);
        return new ResponseEntity<>(batches, HttpStatus.OK);
    }

    @Operation(summary = "Get Batches with positive quantity", description = "Fetches batches with positive quantity.")
    @ApiResponse(responseCode = "200", description = "List of batches with positive quantity")
    @GetMapping("/positive_quantity")
    public ResponseEntity<List<Batch>> getBatchesWithPositiveQuantity() {
        List<Batch> batches = batchService.getBatchesWithPositiveQuantity();
        return new ResponseEntity<>(batches, HttpStatus.OK);
    }

    @Operation(summary = "Get Batches with negative or zero quantity", 
    		description = "Fetches batches with negative or zero quantity.")
    @ApiResponse(responseCode = "200", description = "List of batches with negative or zero quantity")
    @GetMapping("/negative_or_zero_quantity")
    public ResponseEntity<List<Batch>> getBatchesWithNegativeOrZeroQuantity() {
        List<Batch> batches = batchService.getBatchesWithNegativeOrZeroQuantity();
        return new ResponseEntity<>(batches, HttpStatus.OK);
    }
    

	/*
	 *  Question 3 -> Section b : For batches containing negative or zero available
	 *  quantity, I want just latest batch (based on inwardedOn filter)
	 */
    @Operation(summary = "Get the latest batch with zero or negative quantity", 
    		description = "Fetches the latest batch with zero or negative quantity.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Latest batch with zero or negative quantity"),
        @ApiResponse(responseCode = "404", description = "No batches found with zero or negative quantity")
    })
    @GetMapping("/latest_negative_or_zero_quantity")
    public ResponseEntity<Batch> getLatestBatchWithZeroOrNegativeQuantity() {
        Batch batch = batchService.getLatestBatchWithZeroOrNegativeQuantity();
        return new ResponseEntity<>(batch, HttpStatus.OK);
    }
}
